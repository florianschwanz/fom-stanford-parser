package berlin.florianschwanz

import edu.stanford.nlp.parser.nndep.DependencyParser
import edu.stanford.nlp.process.DocumentPreprocessor
import edu.stanford.nlp.tagger.maxent.MaxentTagger
import edu.stanford.nlp.util.logging.Redwood
import java.io.StringReader

/**
 * Demonstrates how to first use the tagger, then use the NN dependency
 * parser. Note that the parser will not work on untagged text.
 *
 * @author Jon Gauthier
 */
object App {

    private val log = Redwood.channels(App::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        var modelPath = DependencyParser.DEFAULT_MODEL
        var taggerPath = PosTagger.ENGLISH_LEFT3_WORDS_DISTSIM

        // Set example sentence
        var text = "I can almost always tell when movies use fake dinosaurs."
        // var text = "Dieses Beispiel zeigt die Struktur eines Satzes nach dem Prinzip der Dependenz."

        var argIndex = 0

        args.forEach { a ->
            println(a)
        }

        while (argIndex < args.size) {
            when (args[argIndex]) {
                "-tagger" -> {
                    taggerPath = args[argIndex + 1]
                    argIndex += 2
                }
                "-model" -> {
                    modelPath = args[argIndex + 1]
                    argIndex += 2
                }
                "-text" -> {
                    text = args[argIndex + 1]
                    argIndex += 2
                }
                else -> throw RuntimeException("Unknown argument " + args[argIndex])
            }
        }

        val tagger = MaxentTagger(taggerPath)
        val parser = DependencyParser.loadFromModelFile(modelPath)

        val tokenizer = DocumentPreprocessor(StringReader(text))
        for (sentence in tokenizer) {
            val tagged = tagger.tagSentence(sentence)
            val gs = parser.predict(tagged)

            // Print grammar
            log.info(gs.toString())
        }
    }
}
