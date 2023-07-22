package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputCleaningTest {
	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception {
		TfIdfAnalyzer analyzer=new TfIdfAnalyzer();
		analyzer.setCleanPunctuation(true);
		analyzer.setRemoveStopWords(true);
		analyzer.setUseStemming(true);
		analyzer.preprocessText("all of my words are preconceived anti-notions of allegorical instances");
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
