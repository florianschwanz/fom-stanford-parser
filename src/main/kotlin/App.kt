package berlin.florianschwanz

import berlin.florianschwanz.config.Annotator
import berlin.florianschwanz.config.Model
import berlin.florianschwanz.config.PosTagger
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations
import edu.stanford.nlp.trees.TreeCoreAnnotations
import edu.stanford.nlp.util.PropertiesUtils
import java.util.*
import kotlin.system.exitProcess


/**
 * Application that
 */
object App {

    var type = ""
    var text = ""

    val LANG_GERMAN = "german"
    val LANG_ENGLISH = "english"

    val TYPE_CONSTITUENCY = "constituency"
    val TYPE_DEPENDENCY = "dependency"

    /**
     * Main function
     */
    @JvmStatic
    fun main(args: Array<String>) {

        // Build properties
        val props = evaluateArguments(args)

        // Initialize document
        val document = Annotation(text)

        // Annotate
        val pipeline = StanfordCoreNLP(props)
        pipeline.annotate(document)

        // Iterate over sentences in text
        document.get(CoreAnnotations.SentencesAnnotation::class.java).forEach { sentence ->
            when (type) {
                TYPE_CONSTITUENCY -> {
                    val parse = sentence.get(TreeCoreAnnotations.TreeAnnotation::class.java)
                    println(parse)
                }

                TYPE_DEPENDENCY -> {
                    val parse = sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation::class.java)
                    println(parse.toList())
                }
            }
        }
    }

    /**
     * Evaluates command line arguments and builds a properties object based on them
     *
     * @param args arguments
     * @return properties
     */
    private fun evaluateArguments(args: Array<String>): Properties {
        var language = ""
        var parseAnnotator = ""
        var taggerPath = ""
        var modelProperty = ""
        var modelPath = ""

        var argIndex = 0

        while (argIndex < args.size) {
            when (args[argIndex]) {
                "--language" -> {
                    language = args[argIndex + 1]
                    argIndex += 2
                }
                "--type" -> {
                    type = args[argIndex + 1]
                    argIndex += 2
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
                "--help" -> {
                    println("USAGE: ./gradlew run --args\"[options...]\"")
                    println("options:")
                    println("--language {german|english}      Language")
                    println("--type {constituency|dependency} Type of parser")
                    println("[--tagger <tagger>]              Path to tagger (optional)")
                    println("[--model <model>]                Path to model (optional)")
                    println("--text <text>                    Text to be parsed")
                    exitProcess(0)
                }
                else -> throw RuntimeException("Unknown argument " + args[argIndex])
            }
        }

        when (language) {
            LANG_GERMAN -> {
                when (type) {
                    TYPE_CONSTITUENCY -> {
                        parseAnnotator = Annotator.PARSE
                        taggerPath = PosTagger.GERMAN_UD_TAGGER
                        modelProperty = "parse.model"
                        modelPath = Model.GERMAN_PCFG_MODEL
                    }
                    TYPE_DEPENDENCY -> {
                        parseAnnotator = Annotator.DEP_PARSE
                        taggerPath = PosTagger.GERMAN_UD_TAGGER
                        modelProperty = "depparse.model"
                        modelPath = Model.GERMAN_UD_MODEL
                    }
                }
            }

            LANG_ENGLISH -> {
                when (type) {
                    TYPE_CONSTITUENCY -> {
                        parseAnnotator = Annotator.PARSE
                        taggerPath = PosTagger.ENGLISH_LEFT3_WORDS_DISTSIM
                        modelProperty = "parse.model"
                        modelPath = Model.ENGLISH_PCFG_MODEL
                    }
                    TYPE_DEPENDENCY -> {
                        parseAnnotator = Annotator.DEP_PARSE
                        taggerPath = PosTagger.ENGLISH_LEFT3_WORDS_DISTSIM
                        modelProperty = "depparse.model"
                        modelPath = Model.ENGLISH_UD_MODEL
                    }
                }
            }
        }

        if (text.isBlank()) {
            throw RuntimeException("Missing text. Please specify sentence to be analyzed using --text flag")
        }

        println("--- Configuration")
        println("language $language")
        println("type $type")
        println("parseAnnotator $parseAnnotator")
        println("taggerPath $taggerPath")
        println("modelPath $modelPath")
        println("text $text")

        // Set properties
        return PropertiesUtils.asProperties(
            "annotators", "tokenize,ssplit,pos,lemma,$parseAnnotator",
            "pos.model", taggerPath,
            modelProperty, modelPath
        )
    }
}
