# Stanford Parser Demo

This demo shows the capabilities of Standford Parser using different models.
It is based on [DependencyParserDemo.java](https://github.com/stanfordnlp/CoreNLP/blob/master/src/edu/stanford/nlp/parser/nndep/demo/DependencyParserDemo.java) from stanfordnln/CoreNLP repo and was extended by
* Kotlin code instead of Java
* German models
* possibility to pass sentences via the command line

## Getting started

### Run constituency parser English

You can run a basic example by calling

```
./gradlew run --args="--type constituency --language english --text 'I can almost always tell when movies use fake dinosaurs.'"
```

Based on the sample text _"I can almost always tell when movies use fake dinosaurs."_ the output should look something like that

```
(ROOT (S (NP (PRP I)) (VP (MD can) (ADVP (RB almost) (RB always)) (VP (VB tell) (SBAR (WHADVP (WRB when)) (S (NP (NNS movies)) (VP (VBP use) (NP (JJ fake) (NNS dinosaurs))))))) (. .)))
```

### Run dependency parser English

You can run a basic example by calling

```
./gradlew run --args="--type dependency --language english --text 'I can almost always tell when movies use fake dinosaurs.'"
```

Based on the sample text _"I can almost always tell when movies use fake dinosaurs."_ the output should look something like that

```
root(ROOT-0, tell-5)
nsubj(tell-5, I-1)
aux(tell-5, can-2)
advmod(always-4, almost-3)
advmod(tell-5, always-4)
advmod(use-8, when-6)
nsubj(use-8, movies-7)
advcl(tell-5, use-8)
amod(dinosaurs-10, fake-9)
dobj(use-8, dinosaurs-10)
punct(tell-5, .-11)
```

### Run constituency parser German

You can run a basic example by calling

```
./gradlew run --args="--type constituency --language german --text 'Ich kann meistens feststellen, wenn in Filmen unechte Dinosaurier verwendet werden.'"
```

Based on the sample text _"Ich kann meistens feststellen, wenn in Filmen unechte Dinosaurier verwendet werden."_ the output should look something like that

```
(ROOT (S (PPER Ich) (VMFIN kann) (VP (ADV meistens) (VVINF feststellen) ($, ,) (S (KOUS wenn) (PP (APPR in) (NN Filmen)) (NP (ADJA unechte) (NN Dinosaurier)) (VP (VVPP verwendet)) (VAINF werden))) ($. .)))
```

### Run dependency parser German

You can run a basic example by calling

```
./gradlew run --args="--type dependency --language german --text 'Ich kann meistens feststellen, wenn in Filmen unechte Dinosaurier verwendet werden.'"
```

Based on the sample text _"Ich kann meistens feststellen, wenn in Filmen unechte Dinosaurier verwendet werden."_ the output should look something like that

```
root(ROOT-0, feststellen-4)
nsubj(feststellen-4, Ich-1)
aux(feststellen-4, kann-2)
advmod(feststellen-4, meistens-3)
punct(feststellen-4, ,-5)
mark(verwendet-11, wenn-6)
case(Filmen-8, in-7)
nmod(verwendet-11, Filmen-8)
amod(Dinosaurier-10, unechte-9)
det(Filmen-8, Dinosaurier-10)
advcl(feststellen-4, verwendet-11)
auxpass(verwendet-11, werden-12)
punct(feststellen-4, .-13)
```