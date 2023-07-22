package com.georgeludwigtechnology.textanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyAnalyzer extends AbstractTextAnalyzer {
	
	public static final int UNLIMITED_MAX_WORD_LENGTH=-1;
	public static final int UNLIMITED_MAX_WORD_COUNT=-1;
	
	public WordFrequencyAnalyzer() {
		setWordMap(new HashMap<String,AnalyzerTerm>());
	}
	
	/**
	 * decomposes the input string in to words, adds the 
	 * individual words to the appropriate containers
	 * @param string
	 */
	public String addStringToAnalysis(String string) {
		// clean text
		string=super.preprocessText(string);
		// tokenize the string
		String[] words=string.split("\\s");
		StringBuffer ret=new StringBuffer();
		for(int i=0;i<words.length;i++) {
			String w=words[i].trim();
			if(w.length()>0) {
				addWordToAnalysis(w);
				ret.append(w+" ");
			}
		}
		return ret.toString();
	}
	
	public void addWordToAnalysis(String word) {
		word=word.toLowerCase();
		// get existing WordFrequency
		Map<String,AnalyzerTerm>wm=getTermMap();
		synchronized(wm) {
			if(wm.containsKey(word)) {
				WordFrequencyTerm term=(WordFrequencyTerm)wm.get(word);
				term.incFrequency();
			} else {
				WordFrequencyTerm term=new WordFrequencyTerm(word);
				term.setFrequency(1);
				wm.put(word,term);
			}
		}
	}

	/**
	 * returns the most frequent words from the word list, with the results being modified by the params
	 * 
	 * @param wordCount the max number of word to be returned
	 * @return
	 */
	public List<AnalyzerTerm>getTopTerms(int wordCount) {
		if(wordCount==UNLIMITED_MAX_WORD_COUNT)
			wordCount=getTermMap().size();
		wordCount=Math.min(wordCount,getTermMap().size());
		// create a list of wordfrequencies that will be sorted later
		List<AnalyzerTerm> wordFrequecyList=new ArrayList<AnalyzerTerm>();
		// get the wordmap and sort it
		for(AnalyzerTerm term:getTermMap().values()) {
			if(term.getTerm().length()>=getMinWordLength() && (term.getTerm().length()<=getMaxWordLength()||getMaxWordLength()==UNLIMITED_MAX_WORD_LENGTH)) {
				wordFrequecyList.add(term);
			}
		}
		// sort the WordFrequencyList
		Collections.sort(wordFrequecyList);
		// create return value
		ArrayList<AnalyzerTerm>ret=new ArrayList<AnalyzerTerm>();
		int cnt=Math.min(wordCount,wordFrequecyList.size());
		for(int i=0;i<cnt;i++) {
			ret.add(wordFrequecyList.get(i));
		}
		return ret;
	}
	
	public List<AnalyzerTerm>getTopTerms() {
		return getTopTerms(UNLIMITED_MAX_WORD_COUNT);
	}
	
	private HashMap<String,AnalyzerTerm>wordMap=new HashMap<String,AnalyzerTerm>();
	
	public Map<String,AnalyzerTerm> getTermMap() {
		return wordMap;
	}

	private void setWordMap(HashMap<String,AnalyzerTerm> wordMap) {
		this.wordMap = wordMap;
	}
	
	public String printWordFrequency() {
		return printWordFrequency(UNLIMITED_MAX_WORD_COUNT);
	}
	
	public String printWordFrequency(int wordCount) {
		return printWordFrequency(wordCount,false);
	}
	
	public String printGlobalWordFrequency() {
		return printWordFrequency(UNLIMITED_MAX_WORD_COUNT,true);
	}
	
	public String printGlobalWordFrequency(int wordCount) {
		return printWordFrequency(wordCount,true);
	}
	
	private String printWordFrequency(int wordCount,boolean asGlobal) {
		String ret="";
		if(wordCount==UNLIMITED_MAX_WORD_COUNT)
			wordCount=getTermMap().size();
		wordCount=Math.min(wordCount, getTermMap().size());
		// get all the words
		List<AnalyzerTerm>wfl=getTopTerms(wordCount);
		//calc # of words
		wordCount=Math.min(wordCount,wfl.size());
		// print the words from low to high
		for(int i=wordCount-1;i>-1;i--) {
			WordFrequencyTerm freq=(WordFrequencyTerm)wfl.get(i);
			if(asGlobal) {
				WordFrequencyTerm wf=WordFrequencyTerm.getGlobalWordFrequencyMap().get(freq.getTerm());
				if(wf==null)
					System.out.println(freq.toString()+" MISSING WORD");
				else System.out.println(wf.toString());
			} else {
				System.out.println(freq.toString());
			}
			if(i!=wordCount-1) 
				ret=ret+"\n"+freq.toString();
			else ret=freq.toString();
		}
		return ret;
	}
	
	private int minWordLength=0;

	public int getMinWordLength() {
		return minWordLength;
	}

	public void setMinWordLength(int minWordLength) {
		this.minWordLength = minWordLength;
	}
	
	private int maxWordLength=-1;

	public int getMaxWordLength() {
		return maxWordLength;
	}

	public void setMaxWordLength(int maxWordLength) {
		this.maxWordLength = maxWordLength;
	}
	
	public String toString() {
		String ret=this.getClass().getName()+"\n"+
		"minWordLength:"+getMinWordLength()+"\n"+
		"maxWordLength:"+getMaxWordLength()+"\n"+
		"wordMapSize:"+getTermMap().size();
		return ret;
	}
}
