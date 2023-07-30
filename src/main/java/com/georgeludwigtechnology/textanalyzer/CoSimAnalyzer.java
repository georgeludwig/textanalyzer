package com.georgeludwigtechnology.textanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 
 * A cosine similarity analyzer based on the paper
 * A Linear Algebra Approach to the Vector Space Model A Fast Track Tutorial 
 * by Dr. E. Garcia 
 *
 * @author George
 *
 */
public class CoSimAnalyzer {
	
	public CoSimAnalyzer() {}
	
	private Document query;

	public Document getQuery() {
		return query;
	}

	public void setQuery(Document query) {
		this.query = query;
	}
	
	private Vector<Document> documents=new Vector<Document>();

	public Vector<Document> getDocuments() {
		return documents;
	}
	
	public void addDocument(Document document) {
		getDocuments().add(document);
	}
	
	public void analyze() {
		// create global word map
		Map<String,Word>globalWordMap=getWordMap();
		// create map of word and doc count i.e. number of docs a word appears in
		Map<String,Integer>wordDocCountMap=new HashMap<String,Integer>();
		for(Document document:getDocuments()) {
			// iterate through the words in the document
			for(Word word:document.getWordMap().values()) {
				String wordString=word.getWord();
				// if it contains the word, inc the frequency
				if(globalWordMap.containsKey(wordString)) {
					Word globalWord=globalWordMap.get(wordString);
					globalWord.incFrequency(1);
				} else {
					//  create a new word and add it to the map
					Word globalWord=new Word();
					globalWord.setWord(wordString);
					globalWord.incFrequency(1);
					globalWordMap.put(globalWord.getWord(), globalWord);
				}
				// increment the doc count
				Integer wordDocCount=null;
				if(wordDocCountMap.containsKey(wordString)) {
					wordDocCount=wordDocCountMap.get(wordString)+1;
				} else {
					wordDocCount=1;
				}
				wordDocCountMap.put(word.getWord(),wordDocCount);
			}
		}
		// for each document/word, calculate A and frobenius norm
		double docCount=getDocuments().size();
		for(Document document:getDocuments()) {
			calcAFrobenius(document,globalWordMap,docCount);
		}
		calcQFrobenius(getQuery());
		// set frobenius for query, which is the sqrt of the sum of the square of the term frequencies
		Document query=getQuery();
		double querySquares=0;
		for(Word word:query.getWordMap().values()) {
			querySquares=querySquares+(word.getFrequency()*word.getFrequency());
		}
		double queryFrobenius=Math.sqrt(querySquares);
		query.setFrobeniusNorm(queryFrobenius);
		// for each doc, divide word weights (A) by frobenius norm
		for(Document document:getDocuments()) {
			// iterate through the words in the document
			for(Word word:document.getWordMap().values()) {
				double A=word.getWeight();
				word.setWeight(A/document.getFrobeniusNorm());
				// update global word map with weight
				Word globalWord=getWordMap().get(word.getWord());
				double globalWeight=globalWord.getWeight()+word.getWeight();
				globalWord.setWeight(globalWeight);
			}
		}
		// divide query word A by frobenius
		for(Word word:getQuery().getWordMap().values()) {
			double A=word.getWeight();
			word.setWeight(A/getQuery().getFrobeniusNorm());
		}
		// for each document, compute dot product with query
		for(Document document:getDocuments()) {
			computeDotProduct(getQuery(),document);
		}
		// sort the doc vector
		Collections.sort(getDocuments());
	}
	
	private void computeDotProduct(Document query, Document document) {
		// for each document, compute dot product with query
			double coSim=calcDotProduct(query,document);
			document.setCoSim(coSim);
			if(coSim<0)
				System.out.println("gotcha");
	}
	
	private void calcAFrobenius(Document document, Map<String,Word>globalWordMap,double docCount) {
		double frobenius=0;
		// iterate through the words in the document
		for(Word word:document.getWordMap().values()) {
			// local frequency x log(docCount/global frequency)
			Word globalWord=globalWordMap.get(word.getWord());
			double log=Math.log10(docCount/globalWord.getFrequency());
			double A=word.getFrequency()*log;
			if(A<0)
				System.out.println("gotcha");
			A=A*word.getFrequency();
			word.setWeight(A);
			frobenius=frobenius+(A*A);
		}
		// set frobenius
		frobenius=Math.sqrt(frobenius);
		document.setFrobeniusNorm(frobenius);
	}
	
	private void calcQFrobenius(Document document) {
		double frobenius=0;
		// iterate through the words in the document
		for(Word word:document.getWordMap().values()) {
			// local frequency x log(docCount/global frequency)
			double A=word.getFrequency();
			if(A<0)
				System.out.println("gotcha");
			word.setWeight(A);
			frobenius=frobenius+(A*A);
		}
		// set frobenius
		frobenius=Math.sqrt(frobenius);
		document.setFrobeniusNorm(frobenius);
	}
	
	public static double calcDotProduct(Document a,Document b) {
		double ret=0.0;
		for(Word word:a.getWordMap().values()) {
			if(b.getWordMap().containsKey(word.getWord())) {
				Word w2=b.getWordMap().get(word.getWord());
				double d=word.getWeight()*w2.getWeight();
				ret+=d;
			}
		}
		return ret;
	}
	
	Map<String,Word>wordMap=new HashMap<String,Word>();
	
	public Map<String,Word>getWordMap() {
		return wordMap;
	}
	
	public List<Word>getSortedWords() {
		List<Word>sortedWords=new ArrayList<Word>();
		for(Word word:getWordMap().values()) {
			sortedWords.add(word);
		}
		Collections.sort(sortedWords);
		return sortedWords;
	}
	
	/**
	 * resets the word map so the analyzer can be used repeatedly
	 */
	public void reset() {
		wordMap=new HashMap<String,Word>();
	}
	
}
