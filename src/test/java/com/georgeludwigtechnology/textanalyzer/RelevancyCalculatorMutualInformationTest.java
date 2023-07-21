package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RelevancyCalculatorMutualInformationTest {

	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception{
		// test for 100 percent match
		String inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		WordFrequencyAnalyzer analyzerA=new WordFrequencyAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		String inputB="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		WordFrequencyAnalyzer analyzerB=new WordFrequencyAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		double ratio=RelevancyCalculator.calcMutualInformationRatio(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==1.0);
		// calc for one
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new WordFrequencyAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="one";
		analyzerB=new WordFrequencyAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcMutualInformationRatio(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.01818181818181818);
		// calc for 7
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new WordFrequencyAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="seven seven seven seven seven seven seven";
		analyzerB=new WordFrequencyAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcMutualInformationRatio(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.12727272727272726);
		// calc for 7, with too many sevens...ratio should remain constant
		inputA="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		analyzerA=new WordFrequencyAnalyzer();
		analyzerA.addStringToAnalysis(inputA);
		inputB="seven seven seven seven seven seven seven seven seven seven seven seven seven";
		analyzerB=new WordFrequencyAnalyzer();
		analyzerB.addStringToAnalysis(inputB);
		ratio=RelevancyCalculator.calcMutualInformationRatio(analyzerA, analyzerB);
		Assertions.assertTrue(ratio==0.12727272727272726);
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
