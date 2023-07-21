package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordFrequencyTest {

	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception{
		WordFrequencyTerm testFreq=WordFrequencyTerm.getGlobalWordFrequencyMap().get("you");
		Assertions.assertTrue(testFreq.getTerm().equals("you"));
		Assertions.assertTrue(testFreq.getRank()==1);
		Assertions.assertTrue(testFreq.getFrequency()==1222421);
		testFreq=WordFrequencyTerm.getGlobalWordFrequencyMap().get("a'right");
		Assertions.assertTrue(testFreq.getTerm().equals("a'right"));
		Assertions.assertTrue(testFreq.getRank()==41284);
		Assertions.assertTrue(testFreq.getFrequency()==6);
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
