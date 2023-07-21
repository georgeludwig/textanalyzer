package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelevancyCalculatorCosineSimilarityTest {

	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception{
		// test for 100 percent match
		String inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		TfIdfAnalyzer analyzerA=new TfIdfAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		String inputB="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		TfIdfAnalyzer analyzerB=new TfIdfAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		double ratio=RelevancyCalculator.calcCosineSimilarity(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.9999999999999999);
		// calc for one
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new TfIdfAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="one";
		analyzerB=new TfIdfAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcCosineSimilarity(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.050964719143762556);
		// calc for 7
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new TfIdfAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="seven seven seven seven seven seven seven";
		analyzerB=new TfIdfAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcCosineSimilarity(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.3567530340063378);
		// calc for 7, with too many sevens...ratio should remain constant
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new TfIdfAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="seven seven seven seven seven seven seven seven seven seven seven seven seven";
		analyzerB=new TfIdfAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcCosineSimilarity(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.35675303400633784);
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
