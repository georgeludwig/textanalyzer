package com.georgeludwigtechnology.textanalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document implements Comparable<Document> {

	public Document() {	}

	public Document(String text) {
		setText(text);
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		setOriginalText(text);
		// clean the text
		this.text=preprocessText(text);
		// split into words, and add to word map
		setWordMap(new HashMap<String,Word>());
		parseInput(this.text);
	}
	
	private void parseInput(String text) {
		String[] wordArray=text.toLowerCase().split(" ");
		for(int i=0;i<wordArray.length;i++) {
			String w=wordArray[i].trim();
			if(w.length()>0) {
				if(getWordMap().containsKey(w)) {
					// increment frequency of existing word
					Word word=getWordMap().get(w);
					word.incFrequency();
				} else {
					// create new word and add it
					Word word=new Word();
					word.setWord(w);
					word.setFrequency(1);
					getWordMap().put(w,word);
				}
			}
		}
	}
	
	private Map<String,Word>wordMap=new HashMap<String,Word>();
	
	public Map<String, Word> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<String, Word> wordMap) {
		this.wordMap = wordMap;
	}

	public String cleanWordPunctuation(String word) {
		String ret=word.replaceAll("\\W", "");
		return ret;
	}
	
	public String cleanInputPunctuation(String input) {
		// split the string  based on whitespace
		String[] words=input.split(" ");
		List<String>wordList=new ArrayList<String>();
		for(int i=0;i<words.length;i++) {
			String w=words[i].trim();
			// replace some punctuation with blanks
			w=cleanWordPunctuation(w);
			if(w.trim().length()>0)
				wordList.add(w);
		}
		// reconstitute words back in to string for re-tokenization
		StringBuilder s=new StringBuilder();
		for(String w:wordList) {
			s.append(w+" ");	
		}
		String ret=s.toString().trim();
		return ret;
	}
	
	private static boolean useStemming;

	public static boolean isUseStemming() {
		return useStemming;
	}

	public static void setUseStemming(boolean useStemming) {
		Document.useStemming = useStemming;
	}
	
	private static boolean removeStopWords;

	public static boolean isRemoveStopWords() {
		return removeStopWords;
	}

	public static void setRemoveStopWords(boolean removeStopWords) {
		Document.removeStopWords = removeStopWords;
	}
	
	private static boolean cleanPunctuation;

	public static boolean isCleanPunctuation() {
		return cleanPunctuation;
	}

	public static void setCleanPunctuation(boolean cleanPunctuation) {
		Document.cleanPunctuation = cleanPunctuation;
	}
	
	public String preprocessText(String text) {
		if(isCleanPunctuation())
			text=cleanInputPunctuation(text);
		if(isRemoveStopWords())
			text=stopWords.removeStopwordsFromString(text);
		if(isUseStemming())
			text=stemInput(text);
		return text;
	}

	private Stopwords stopWords=new Stopwords();
	
	private Stemmer stemmer=new Stemmer();
	
	private String stemInput(String input) {
		// split the string  based on whitespace
		String[] words=input.split(" ");
		List<String>wordList=new ArrayList<String>();
		for(int i=0;i<words.length;i++) {
			String w=words[i].trim();
			// stem the word
			w=stemmer.stemWord(w);
			wordList.add(w);
		}
		// reconstitute words back in to string
		StringBuilder s=new StringBuilder();
		for(String w:wordList) {
			s.append(w+" ");	
		}
		String ret=s.toString().trim();
		return ret;
	}
	
	private double frobeniusNorm;

	public double getFrobeniusNorm() {
		return frobeniusNorm;
	}

	public void setFrobeniusNorm(double frobeiusNorm) {
		this.frobeniusNorm = frobeiusNorm;
	}
	
	public double getTotalWordFrequency() {
		double ret=0;
		for(Word word:getWordMap().values()) {
			ret=ret+word.getFrequency();
		}
		return ret;
	}
	
	private double coSim;

	public double getCoSim() {
		return coSim;
	}

	public void setCoSim(double coSim) {
		this.coSim = coSim;
	}

	@Override
	public int compareTo(Document doc) {
		double thisScore=getCoSim();
		double thatScore=doc.getCoSim();
		if(thisScore>thatScore)
			return -1;
		if(thisScore<thatScore)
			return 1;
		return 0;
	}
	
	private String originalText;

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String toString() {
		return "oText: \'"+getOriginalText()+"\' pText: \'"+getText()+"\' coSim: "+getCoSim()+" froNorm: "+getFrobeniusNorm()+" TWF: "+getTotalWordFrequency();
	}
	
}
