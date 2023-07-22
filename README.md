# Cosine similarity text analyzer with Frobenius normalization

* Frobenius normalization is used to minimize impact of term stuffing.
* The implementation of Frobenius normalization is based on the research of Edel Garcia, Ph.D.
* The code is optimized for high volume queries against a corpus.
* This implementation provides optional Porter-stemming.
* This implementation provides optional stop word removal, based on Rainbow stop words.
* This implementation provides optional punctuation cleaning.
* Note that the use of Porter-stemming and stop word removal are only appropriate for use with an English corpus.

## References:

* [Cosine Similarity](https://en.wikipedia.org/wiki/Cosine_similarity)
* [Edel Garcia, Ph.D.](http://minerazzi.com/)
* [Frobenius norm](https://mathworld.wolfram.com/FrobeniusNorm.html)
* [Porter Stemming](https://tartarus.org/martin/PorterStemmer/)
* [Rainbow](http://www.cs.cmu.edu/~mccallum/bow/rainbow/)

