package berlin.florianschwanz.config

/**
 * Object containing taggers
 */
object PosTagger {

    private val basePath = "edu/stanford/nlp/models/pos-tagger/"

    val ENGLISH_LEFT3_WORDS_DISTSIM = basePath + "english-left3words/english-left3words-distsim.tagger"
    val GERMAN_FAST_TAGGER = basePath + "german/german-fast.tagger"
    val GERMAN_HGC_TAGGER = basePath + "german/german-hgc.tagger"
    val GERMAN_UD_TAGGER = basePath + "german/german-ud.tagger"
}