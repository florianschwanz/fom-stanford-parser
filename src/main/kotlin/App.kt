package berlin.florianschwanz

import edu.stanford.nlp.parser.nndep.DependencyParser
import edu.stanford.nlp.process.DocumentPreprocessor
import edu.stanford.nlp.tagger.maxent.MaxentTagger
import java.io.StringReader



/**
 * Application that
 */
object App {

    /**
     * Main function
     */
    @JvmStatic
    fun main(args: Array<String>) {
        var taggerPath = ""
        var modelPath = ""
        var text = ""

        var argIndex = 0

        while (argIndex < args.size) {
            when (args[argIndex]) {
                "--english" -> {
                    // Preconfigure English parser
                    taggerPath = PosTagger.ENGLISH_LEFT3_WORDS_DISTSIM
                    modelPath = Model.ENGLISH_UD_MODEL
                    argIndex += 1
                }
                "--german" -> {
                    // Preconfigure German parser
                    taggerPath = PosTagger.GERMAN_UD_TAGGER
                    modelPath = Model.GERMAN_UD_MODEL
                    argIndex += 1
                }
                "--tagger" -> {
                    taggerPath = args[argIndex + 1]
                    argIndex += 2
                }
                "--model" -> {
                    modelPath = args[argIndex + 1]
                    argIndex += 2
                }
                "--text" -> {
                    text = args[argIndex + 1]
                    argIndex += 2
                }
                else -> throw RuntimeException("Unknown argument " + args[argIndex])
            }
        }

        if (taggerPath.isBlank()) {
            throw RuntimeException("Missing tagger. Please specify path to tagger using --tagger flag")
        }

        if (modelPath.isBlank()) {
            throw RuntimeException("Missing model. Please specify path to model using --model flag")
        }

        if (text.isBlank()) {
            throw RuntimeException("Missing text. Please specify sentence to be analyzed using --text flag")
        }

        val tagger = MaxentTagger(taggerPath)
        val parser = DependencyParser.loadFromModelFile(modelPath)
        val tokenizer = DocumentPreprocessor(StringReader(text))

        for (sentence in tokenizer) {
            val tagged = tagger.tagSentence(sentence)
            val gs = parser.predict(tagged)

            println(gs.toString())
        }
    }
}
