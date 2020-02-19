package berlin.florianschwanz.config

/**
 * Object containing models
 */
object Model {

    private val basePath = "edu/stanford/nlp/models/"

    private val lexModelPath = basePath + "lexparser/"
    private val nnDepPath = basePath + "parser/nndep/"

    // PCFG = Probabilistic context-free grammar
    val ENGLISH_PCFG_MODEL = lexModelPath + "englishPCFG.ser.gz"
    val GERMAN_PCFG_MODEL = lexModelPath + "germanPCFG.ser.gz"
    val GERMAN_FACTORED_MODEL = lexModelPath + "germanFactored.ser.gz"

    val ENGLISH_UD_MODEL = nnDepPath + "english_UD.gz"
    val GERMAN_UD_MODEL = nnDepPath + "UD_German.gz"

}
