# textanalyzer
# Cosine similarity text analyzer with Frobenius normalization

* Frobenius normalization is used to minimize impact of term stuffing.
* The implementation of Frobenius normalization is based on the research of Edel Garcia, Ph.D.
* The code is optimized for high volume queries against a corpus.
* Adding documents to the corpus is thread-safe, but synchronized.
* Queries against the corpus are thread-safe, and NOT synchronized.
* This implementation uses Porter-stemming by default, and is most appropriate for the English language.

## See also:

[Cosine Similarity](https://en.wikipedia.org/wiki/Cosine_similarity)
[Edel Garcia, Ph.D.](http://minerazzi.com/)
[Frobenius norm](https://mathworld.wolfram.com/FrobeniusNorm.html)
[Porter Stemming](https://tartarus.org/martin/PorterStemmer/)

