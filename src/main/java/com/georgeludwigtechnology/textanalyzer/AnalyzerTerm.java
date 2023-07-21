package com.georgeludwigtechnology.textanalyzer;

public interface AnalyzerTerm extends Comparable<AnalyzerTerm> {

	public String getTerm();
	
	public String toString();
	
	public int getFrequency();
}
