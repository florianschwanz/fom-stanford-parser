# Stanford Parser Demo

This demo shows the capabilities of Standford Parser using different models.

## Getting started

### Run basic example

You can run a basic example that uses _english-left3words-distsim_ by simly calling

```
./gradlew run
```

which is equal to

```
./gradlew run --args="-tagger edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger -text 'I can almost always tell when movies use fake dinosaurs.'"
```

Based on the sample text _"I can almost always tell when movies use fake dinosaurs."_ the output should look something like that

```
[main] INFO berlin.florianschwanz.App - ROOT-0{CharacterOffsetBeginAnnotation=-1, CharacterOffsetEndAnnotation=-1, PartOfSpeechAnnotation=null, TextAnnotation=ROOT}Typed Dependencies:
[nsubj(tell-5, I-1), aux(tell-5, can-2), advmod(always-4, almost-3), advmod(tell-5, always-4), root(ROOT-0, tell-5), advmod(use-8, when-6), nsubj(use-8, movies-7), advcl(tell-5, use-8), amod(dinosaurs-10, fake-9), dobj(use-8, dinosaurs-10), punct(tell-5, .-11)]
```

```
[
    nsubj(tell-5, I-1), 
    aux(tell-5, can-2), 
    advmod(always-4, almost-3), 
    advmod(tell-5, always-4), 
    root(ROOT-0, tell-5), 
    advmod(use-8, when-6), 
    nsubj(use-8, movies-7), 
    advcl(tell-5, use-8), 
    amod(dinosaurs-10, fake-9), 
    dobj(use-8, dinosaurs-10), 
    punct(tell-5, .-11)
]
```


### German example

You can pass a different parser and a German sentence by calling

```
./gradlew run --args="-tagger edu/stanford/nlp/models/pos-tagger/german/german-ud.tagger -text 'Dieses Beispiel zeigt die Struktur eines Satzes nach dem Prinzip der Depend
enz.'"
```

Based on the sample text the output should look something like that

```
[main] INFO berlin.florianschwanz.App - ROOT-0{CharacterOffsetBeginAnnotation=-1, CharacterOffsetEndAnnotation=-1, PartOfSpeechAnnotation=null, TextAnnotation=ROOT}Typed Dependencies:
[dep(Dependenz-12, Dieses-1), dep(Dependenz-12, Beispiel-2), dep(Dependenz-12, zeigt-3), dep(Dependenz-12, die-4), compound(Dependenz-12, Struktur-5), compound(Dependenz-12, eines-6), compound(Dependenz-12, Sat
zes-7), compound(Dependenz-12, nach-8), compound(Dependenz-12, dem-9), compound(Dependenz-12, Prinzip-10), compound(Dependenz-12, der-11), root(ROOT-0, Dependenz-12), punct(Dependenz-12, .-13)]
```

```
[
    dep(Dependenz-12, Dieses-1), 
    dep(Dependenz-12, Beispiel-2), 
    dep(Dependenz-12, zeigt-3), 
    dep(Dependenz-12, die-4), 
    compound(Dependenz-12, Struktur-5), 
    compound(Dependenz-12, eines-6), 
    compound(Dependenz-12, Satzes-7), 
    compound(Dependenz-12, nach-8), compound(Dependenz-12, dem-9), 
    compound(Dependenz-12, Prinzip-10), 
    compound(Dependenz-12, der-11), 
    root(ROOT-0, Dependenz-12), 
    punct(Dependenz-12, .-13)
]

```