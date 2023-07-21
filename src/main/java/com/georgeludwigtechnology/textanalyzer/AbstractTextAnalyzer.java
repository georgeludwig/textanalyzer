package com.georgeludwigtechnology.textanalyzer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextAnalyzer implements TextAnalyzer {

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
	
	private boolean useStemming;

	public boolean isUseStemming() {
		return useStemming;
	}

	public void setUseStemming(boolean useStemming) {
		this.useStemming = useStemming;
	}
	
	private boolean removeStopWords;

	public boolean isRemoveStopWords() {
		return removeStopWords;
	}

	public void setRemoveStopWords(boolean removeStopWords) {
		this.removeStopWords = removeStopWords;
	}
	
	private boolean cleanPunctuation;

	public boolean isCleanPunctuation() {
		return cleanPunctuation;
	}

	public void setCleanPunctuation(boolean cleanPunctuation) {
		this.cleanPunctuation = cleanPunctuation;
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
	

}
