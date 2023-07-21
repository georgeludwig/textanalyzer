package com.georgeludwigtechnology.textanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Term Frequency-Inverse Document Frequency Analyzer
 * @author George Ludwig
 *
 */
public class TfIdfAnalyzer extends AbstractTextAnalyzer {
	
	public static final int UNLIMITED_MAX_WORD_LENGTH=-1;
	public static final int UNLIMITED_MAX_WORD_COUNT=-1;
	
	public TfIdfAnalyzer() {
		super();
	}
	
	/**
	 * decomposes the input string in to terms, adds the 
	 * individual terms to the appropriate containers
	 * @param words
	 */
	public String addStringToAnalysis(String string) {
		Map<String,AnalyzerTerm>termMap=getTermMap();
		string=super.preprocessText(string);
		StringBuffer ret=new StringBuffer();
		synchronized(termMap) {
			// tokenize the string
			String[] terms=string.split("\\s");
			// create set for tracking doc frequency
			Map<String,Boolean>docFreqTracker=new HashMap<String,Boolean>();
			for(int i=0;i<terms.length;i++) {
				String s=terms[i].trim().toLowerCase();
				if(s.length()>0) {
					// ensure there is an entry in docFreqTracker
					if(!docFreqTracker.containsKey(s))
						docFreqTracker.put(s,false);
					// create new TermFrequency if we've not seen this term before
					if(!termMap.containsKey(s)) {
						TfIdfTerm freq=new TfIdfTerm(s,termMap);
						termMap.put(s,freq);
						ret.append(s+" ");
					} 
					// process the term frequency
					TfIdfTerm freq=(TfIdfTerm)getTermMap().get(s);
					// inc the term frequency
					freq.incFrequency();
					// inc the document frequency for this term
					if(!docFreqTracker.get(s)) {
						freq.incDocFrequency();
						docFreqTracker.put(s,true);
					}
				}
			}
			// iterate through all terms in inc their docCount
			for( AnalyzerTerm freq:termMap.values()) {
				TfIdfTerm term=(TfIdfTerm)freq;
				term.incDocCount();
			}
		}
		return ret.toString();
	}
	
	/**
	 * 
	 * Returns the highest-weighted term from the term list.
	 * 
	 * @param wordCount the max number of word to be returned
	 * @return
	 */
	public List<AnalyzerTerm>getTopTerms(int wordCount) {
		if(wordCount==UNLIMITED_MAX_WORD_COUNT)
			wordCount=getTermMap().size();
		wordCount=Math.min(wordCount,getTermMap().size());
		// create a list of terms that will be sorted later
		List<TfIdfTerm> termList=new ArrayList<TfIdfTerm>();
		for(AnalyzerTerm term:getTermMap().values()) {
			TfIdfTerm tt=(TfIdfTerm)term;
			termList.add(tt);
		}
		// sort the TermFrequencyList
		Collections.sort(termList,new TfIdfComparator());
		// create return value, but invert the sort order 
		// because we want the highest scoring term at position 0
		ArrayList<AnalyzerTerm>ret=new ArrayList<AnalyzerTerm>();
		int cnt=Math.min(wordCount,termList.size());
		for(int i=cnt-1;i>=0;i--) {
			ret.add(termList.get(i));
		}
		return ret;
	}
	
	public List<AnalyzerTerm>getTopTerms() {
		return getTopTerms(UNLIMITED_MAX_WORD_COUNT);
	}
	
	private HashMap<String,AnalyzerTerm>termMap=new HashMap<String,AnalyzerTerm>();
	
	public Map<String,AnalyzerTerm> getTermMap() {
		return termMap;
	}

//	private void setTermMap(HashMap<String,TermFrequency> termMap) {
//		this.termMap = termMap;
//	}
	
	public String printTermFrequency() {
		return printTermFrequency(UNLIMITED_MAX_WORD_COUNT);
	}
	
	public String printGlobalTermFrequency() {
		return printTermFrequency(UNLIMITED_MAX_WORD_COUNT);
	}
	
	private String printTermFrequency(int termCount) {
		String ret="";
		if(termCount==UNLIMITED_MAX_WORD_COUNT)
			termCount=getTermMap().size();
		termCount=Math.min(termCount, getTermMap().size());
		// get all the words
		List<AnalyzerTerm>wfl=getTopTerms(termCount);
		//calc # of words
		termCount=Math.min(termCount,wfl.size());
		// print the words from low to high
		for(int i=termCount-1;i>-1;i--) {
			TfIdfTerm freq=(TfIdfTerm)wfl.get(i);
			System.out.println(freq.toString());
			if(i!=termCount-1) 
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
