package com.georgeludwigtechnology.textanalyzer;

import java.util.List;
import java.util.Map;

public interface TextAnalyzer {

	public List<AnalyzerTerm>getTopTerms();
	
	public List<AnalyzerTerm>getTopTerms(int wordCount);
	
	public Map<String,AnalyzerTerm>getTermMap();
	
	public String addStringToAnalysis(String string); // returns the String that was actually added, after filtering etc.
	
	public void setUseStemming(boolean useStemming);
	
	public boolean isUseStemming();
	
	public void setRemoveStopWords(boolean removeStopWords);
	
	public boolean isRemoveStopWords();

	public void setCleanPunctuation(boolean cleanPunctuation);
	
	public boolean isCleanPunctuation();
	
	public String preprocessText(String text);
	
}
