package com.georgeludwigtechnology.textanalyzer;

import java.util.Map;

public class RelevancyCalculator {

	/**
	 * Calculate float representing the percentage of similarity between the content 
	 * of WordFrequencyAnalyzer A to the content of WordFrequencyAnalyzer B.
	 * 
	 * The current immplementation is very simple, returning the percentage of A that is contained in B
	 * based on word frequency
	 * 
	 * A return value of 1.0 represents an exact match
	 * @param wfa
	 * @return
	 */
	public static double calcMutualInformationRatio(TextAnalyzer A, TextAnalyzer B) throws Exception {
		// returns the percentage of A that is contained in B
		// first, calculate the total frequency of A
		int totalFrequencyA=0;
		Map<String,AnalyzerTerm>wordFrequencyMapA=A.getTermMap();
		for(AnalyzerTerm word:wordFrequencyMapA.values()) {
			AnalyzerTerm freq=word;
			totalFrequencyA+=freq.getFrequency();
		}
		// for each word in B, add to commonFrequencyB the frequency of a word in B that is
		// shared by A, however, add the min of A or B. I.e., a word in B does not have any greater
		// effect on commonFrequencyB that the frequency in A for that word
		int commonFrequencyB=0;
		Map<String,AnalyzerTerm>wordFrequencyMapB=B.getTermMap();
		for(AnalyzerTerm word:wordFrequencyMapB.values()) {
			if(wordFrequencyMapA.containsKey(word.getTerm())) {
				AnalyzerTerm fA=wordFrequencyMapA.get(word.getTerm());
				AnalyzerTerm fB=wordFrequencyMapB.get(word.getTerm());
				int freq=Math.min(fA.getFrequency(), fB.getFrequency());
				commonFrequencyB+=freq;		
			}
		}
		// return the ratio of commonFrequencyB to totalFrequencyA as a percentage
		double ratio=(double)commonFrequencyB/(double)totalFrequencyA;
		return ratio;
	}
	
	public static double calcCosineSimilarity(TfIdfAnalyzer A, TfIdfAnalyzer B) throws Exception {
		double dpCommon=computeDotProduct(A,B);
		double dpA=computeDotProduct(A,A);
		double dpB=computeDotProduct(B,B);
		double sqrtDpA=Math.sqrt(dpA);
		double sqrtDpB=Math.sqrt(dpB);
		double ret=dpCommon/(sqrtDpA*sqrtDpB);
		return ret;
	}
	
	public static double computeDotProduct(TfIdfAnalyzer A, TfIdfAnalyzer B) throws Exception {
		double ret=0.0;
		for(AnalyzerTerm t:A.getTermMap().values()) {
			TfIdfTerm t1=(TfIdfTerm)t;
			if(B.getTermMap().containsKey(t1.getTerm())) {
				TfIdfTerm t2=(TfIdfTerm)B.getTermMap().get(t1.getTerm());
				double d=t1.getTfIdf()*t2.getTfIdf();
				ret+=d;
			}
		}
		return ret;
	}
	
	
	
}
