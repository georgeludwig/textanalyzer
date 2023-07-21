package com.georgeludwigtechnology.textanalyzer;

import java.util.Map;

public class TfIdfTerm implements AnalyzerTerm {
	
	public TfIdfTerm(String term) {
		this.setTerm(term);
	}
	
	public TfIdfTerm(String term,Map<String,AnalyzerTerm>docTermMap) {
		this.setTerm(term);
		this.setDocTermMap(docTermMap);
	}
	
	private String term;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	private Map<String,AnalyzerTerm>docTermMap;
	
	public Map<String,AnalyzerTerm> getDocTermMap() {
		return docTermMap;
	}

	public void setDocTermMap(Map<String, AnalyzerTerm> docTermMap) {
		this.docTermMap = docTermMap;
	}

	private int frequency=0;

	public int getFrequency() {
		return frequency;
	}

	public void incFrequency() {
		this.frequency += 1;
	}

	private int documentFrequency=0;

	public int getDocFrequency() {
		return documentFrequency;
	}

	public void incDocFrequency() {
		this.documentFrequency += 1;
	}
	
	private int docCount=0;

	public int getDocCount() {
		return docCount;
	}

	public void incDocCount() {
		this.docCount += 1;
	}

	@Override
	public int compareTo(AnalyzerTerm o) throws ClassCastException {
		TfIdfTerm t=(TfIdfTerm)o;
		double thisScore=getTfIdf();
		double thatScore=t.getTfIdf();
		if(thisScore>thatScore)
			return 1;
		if(thisScore<thatScore)
			return -1;
		return 0;
	}
	
	public double getTfIdf() {
		return getTfIdf((double)getFrequency(),
				(double)getDocTermMap().size(),
				(double)getDocCount(),
				(double)getDocFrequency());
	}
	
	public static double getTfIdf(double docTermFreq,double docTermCount,double corpusDocCount,double corpusTermFreq) {
		double tf=docTermFreq/docTermCount;
		double d=corpusDocCount/corpusTermFreq;
		double idf=Math.log10(d);
		if (idf==0)
			idf=1.00;
		double tfidf=tf*idf;
		return tfidf;
	}
	
	
	public String toString() {
		return getTerm()+" "+getFrequency()+" "+getDocFrequency()+" "+getDocCount()+" "+getTfIdf();
	}
}
