package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class TfIdfItemTest {

	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testTfIdf() throws Exception{
		
		// textbook test to see if "cow" has a TfIdf of 0.12
		// no need to load the analyzer with 10 million "documents"
		// we just need the size of the docMap to be 10million
		
		Map<String,AnalyzerTerm>docTermMap=new HashMap<String,AnalyzerTerm>();
		for(int i=0;i<100;i++) {
			String term=Integer.toString(i);
			docTermMap.put(term,new TfIdfTerm(term));
		}
		TfIdfTerm cow=new TfIdfTerm("cow");
		cow.setDocTermMap(docTermMap);
		for(int i=0;i<3;i++)
			cow.incFrequency();
		for(int i=0;i<10000000;i++)
			cow.incDocCount();
		for(int i=0;i<1000;i++)
			cow.incDocFrequency();
		double tfidf=cow.getTfIdf();
		Assertions.assertTrue(tfidf==0.12);
		tfidf=TfIdfTerm.getTfIdf(3.0, 100.0, 10000000.0, 1000.0);
		Assertions.assertTrue(tfidf==0.12);
		
		// secondary test
		docTermMap=new HashMap<String,AnalyzerTerm>();
		for(int i=0;i<29;i++) {
			String term=Integer.toString(i);
			docTermMap.put(term,new TfIdfTerm(term));
		}
		TfIdfTerm one=new TfIdfTerm("one");
		one.setDocTermMap(docTermMap);
		for(int i=0;i<4;i++)
			one.incFrequency();
		for(int i=0;i<4;i++)
			one.incDocCount();
		for(int i=0;i<4;i++)
			one.incDocFrequency();
		tfidf=one.getTfIdf();
		Assertions.assertTrue(tfidf==0.13793103448275862);
	}
	
	@AfterEach
	public void tearDown() throws Exception {}
}
