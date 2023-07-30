# Cosine similarity text analyzer with Frobenius normalization

* Frobenius normalization is used to minimize impact of term stuffing.
* The implementation of Frobenius normalization is based on the research of Edel Garcia, Ph.D.
* The code is optimized for high volume queries against a corpus.
* This implementation provides optional Porter-stemming.
* This implementation provides optional stop word removal, based on Rainbow stop words.
* This implementation provides optional punctuation cleaning.
* Note that the use of Porter-stemming and stop word removal are only appropriate for use with an English corpus.

## References

* [Cosine Similarity](https://en.wikipedia.org/wiki/Cosine_similarity)
* [Edel Garcia, Ph.D.](http://minerazzi.com/)
* [Frobenius norm](https://mathworld.wolfram.com/FrobeniusNorm.html)
* [Porter Stemming](https://tartarus.org/martin/PorterStemmer/)
* [Rainbow](http://www.cs.cmu.edu/~mccallum/bow/rainbow/)

## Example Usage

```
// set optional global Document params
// note that Document preserves the original text prior to cleaning, stemming, and stopword removal
// the original text is accessible via doc.getOriginalText()
Document.setCleanPunctuation(true);
Document.setUseStemming(true);
Document.setRemoveStopWords(true);
// create the analyzer
CoSimAnalyzer analyzer=new CoSimAnalyzer();
// create/add docs to corpus
analyzer.addDocument(new Document("LSI tutorials and fast tracks."));
analyzer.addDocument(new Document("Books on #semantic analysis."));
analyzer.addDocument(new Document("Learning latent #semantic indexing.");
analyzer.addDocument(new Document("Advances in structures and advances in indexing."));
analyzer.addDocument(new Document("Analysis of latent structures."));
// create the query...note that the query is just another document 
analyzer.setQuery(new Document("latent #semantic indexing"));
// post CoSimAnalyzer.analyze(), getDocuments() will return the documents in ranked order
// and getSortedWords() will return the corpus word list sorted by weight
analyzer.analyze();
// print Documents and Words
analyzer.getDocuments().stream().forEach(d -> System.out.println(d));
System.out.println(analyzer.getQuery());
analyzer.getSortedWords().stream().forEach(w -> System.out.println(w));
```

## Example Output
```
oText: 'Learning latent #semantic indexing.' pText: 'learn latent semant index' coSim: 0.7021397624027602 froNorm: 0.981645464751505 TWF: 4.0
oText: 'Analysis of latent structures.' pText: 'analysi latent structur' coSim: 0.33333333333333337 froNorm: 0.6892523133843688 TWF: 3.0
oText: 'Books on #semantic analysis.' pText: 'book semant analysi' coSim: 0.25602701623551843 froNorm: 0.8973692483951614 TWF: 3.0
oText: 'Advances in structures and advances in indexing.' pText: 'advanc structur advanc index' coSim: 0.0805589942188751 froNorm: 2.8519567970759496 TWF: 4.0
oText: 'LSI tutorials and fast tracks.' pText: 'lsi tutori fast track' coSim: 0.0 froNorm: 1.3979400086720377 TWF: 4.0
oText: 'latent #semantic indexing' pText: 'latent semant index' coSim: 0.0 froNorm: 1.7320508075688772 TWF: 3.0
word: 'analysi' weight: 1.0208020694198054 freq: 2.0
word: 'latent' weight: 0.9827308500215992 freq: 2.0
word: 'advanc' weight: 0.9803374371626637 freq: 1.0
word: 'semant' weight: 0.8488323810621531 freq: 2.0
word: 'book' weight: 0.7789101371437053 freq: 1.0
word: 'structur' weight: 0.7168825401833648 freq: 2.0
word: 'learn' weight: 0.7120391520506408 freq: 1.0
word: 'index' weight: 0.5449128518257125 freq: 2.0
word: 'lsi' weight: 0.5 freq: 1.0
word: 'fast' weight: 0.5 freq: 1.0
word: 'tutori' weight: 0.5 freq: 1.0
word: 'track' weight: 0.5 freq: 1.0
```