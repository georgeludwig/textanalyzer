package com.georgeludwigtechnology.textanalyzer;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TfIdfAnalyzerTest {

	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testTfIdf() throws Exception{
		TfIdfAnalyzer analyzer=new TfIdfAnalyzer();
		String string="One thing I remember is running for the door";
		analyzer.addStringToAnalysis(string);
		string="One time I remembered a dream that never happened";
		analyzer.addStringToAnalysis(string);
		string="It just so happens that one of my colleagues is Mexican";
		analyzer.addStringToAnalysis(string);
		string="Here's another one for you: plastic";
		analyzer.addStringToAnalysis(string);
		List<AnalyzerTerm> terms=analyzer.getTopTerms();
		TfIdfTerm term=(TfIdfTerm)terms.get(0);
		Assertions.assertTrue(term.getTerm().equals("one"));
		Assertions.assertTrue(term.getDocFrequency()==4);
		Assertions.assertTrue(term.getFrequency()==4);
		term=(TfIdfTerm)terms.get(terms.size()-1);
		Assertions.assertTrue(term.getTerm().equals("mexican"));
		Assertions.assertTrue(term.getDocFrequency()==1);
		Assertions.assertTrue(term.getFrequency()==1);
	}
	
	@AfterEach
	public void tearDown() throws Exception {}
}