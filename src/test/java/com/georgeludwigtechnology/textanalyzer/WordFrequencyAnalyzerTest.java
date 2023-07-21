package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class WordFrequencyAnalyzerTest {
	
	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception{
		String input="one two two three three three four four four four five five five five five six six six six six six"+
		" seven seven seven seven seven seven seven eight eight eight eight eight eight eight eight"+
		" nine nine nine nine nine nine nine nine nine ten ten ten ten ten ten ten ten ten ten";
		////////////////////////
		WordFrequencyAnalyzer analyzer=new WordFrequencyAnalyzer();
		analyzer.addStringToAnalysis(input);
		////////////////////////
		// get all words by frequency, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_LOCAL_FREQUENCY);
		List<AnalyzerTerm>topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "ten"
		WordFrequencyTerm freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("ten") && freq.getFrequency()==10);
		// get lowest scoring word, confirm that it is "six"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("six") && freq.getFrequency()==6);
		// same as above, in a different way
		topTerms=analyzer.getTopTerms();
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "ten"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("ten") && freq.getFrequency()==10);
		// get lowest scoring word, confirm that it is "six"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("six") && freq.getFrequency()==6);
		// print all word by frequency
		System.out.println("+++ all words by local frequency");
		analyzer.printWordFrequency();
		////////////////////////
		// get all words by weight, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_SIMPLE_WEIGHT);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "eight"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("eight") && WordFrequencyTerm.getSimpleWeight(freq)==40);
		// get lowest scoring word, confirm that it is "five"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("five") && WordFrequencyTerm.getSimpleWeight(freq)==20);
		// same as above, in a different way
		topTerms=analyzer.getTopTerms();
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "ten"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("eight") && freq.getFrequency()==8);
		// get lowest scoring word, confirm that it is "six"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("five") && freq.getFrequency()==5);
		// print all word by weight
		System.out.println("\n+++ all words by simple weight");
		analyzer.printWordFrequency();
		////////////////////////
		// similar test to all the above, only we will limit the word length
		analyzer.setMinWordLength(3);
		analyzer.setMaxWordLength(4);
		////////////////////////		
		// get all words by frequency, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_LOCAL_FREQUENCY);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==7);
		// get the highest scoring word, confirm that it is "ten"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("ten") && freq.getFrequency()==10);
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("four") && freq.getFrequency()==4);
		// same as above, in a different way
		topTerms=analyzer.getTopTerms();
		Assertions.assertTrue(topTerms.size()==7);
		// get the highest scoring word, confirm that it is "ten"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("ten") && freq.getFrequency()==10);
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("four") && freq.getFrequency()==4);
		// print min 3, max 4 word by frequency
//		System.out.println("\n+++ min 3, max 4 words by local frequency");
//		analyzer.printWordFrequency();
		////////////////////////		
		// get all words by weight, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_SIMPLE_WEIGHT);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==7);
		// get the highest scoring word, confirm that it is "nine"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("nine") && WordFrequencyTerm.getSimpleWeight(freq)==36);
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("four") && WordFrequencyTerm.getSimpleWeight(freq)==16);
		// same as above, in a different way
		topTerms=analyzer.getTopTerms();
		Assertions.assertTrue(topTerms.size()==7);
		// get the highest scoring word, confirm that it is "nine"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("nine") && WordFrequencyTerm.getSimpleWeight(freq)==36);
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("four") && WordFrequencyTerm.getSimpleWeight(freq)==16);
		// print min 3, max 4 word by weight
//		System.out.println("\n+++ min 3, max 4 words by simple weight");
//		analyzer.printWordFrequency();
		////////////////////////
		// get all words by global frequency, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_GLOBAL_FREQUENCY);
		analyzer.setMinWordLength(0);
		analyzer.setMaxWordLength(WordFrequencyAnalyzer.UNLIMITED_MAX_WORD_LENGTH);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "ten"
		freq=(WordFrequencyTerm)topTerms.get(0);
		WordFrequencyTerm globalFreq=WordFrequencyTerm.getGlobalWordFrequencyMap().get(freq.getTerm());
		Assertions.assertTrue(freq.getTerm().equals("nine") && globalFreq.getFrequency()==1906);
		// get lowest scoring word, confirm that it is "six"
		freq=(WordFrequencyTerm)topTerms.get(4);
		globalFreq=WordFrequencyTerm.getGlobalWordFrequencyMap().get(freq.getTerm());
		Assertions.assertTrue(freq.getTerm().equals("six") && globalFreq.getFrequency()==4737);
		// print all word by frequency
		System.out.println("\n+++ all words by global frequency");
		analyzer.printGlobalWordFrequency();
		////////////////////////
		// get all words by global weight, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_GLOBAL_WEIGHT);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "nine"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("nine") && WordFrequencyTerm.getGlobalWeight(freq)==10215);
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("six") && WordFrequencyTerm.getGlobalWeight(freq)==3468);
		// print min 3, max 4 word by weight
		System.out.println("\n+++ all words by global weight");
		analyzer.printGlobalWordFrequency();
		////////////////////////
		// get all words by global weight, confirm that the count = wordCount
		WordFrequencyTerm.setCompareMode(WordFrequencyTerm.COMPARE_MODE_BY_GLOBAL_RANK);
		topTerms=analyzer.getTopTerms(10);
		Assertions.assertTrue(topTerms.size()==10);
		// get the highest scoring word, confirm that it is "nine"
		freq=(WordFrequencyTerm)topTerms.get(0);
		Assertions.assertTrue(freq.getTerm().equals("nine"));
		// get lowest scoring word, confirm that it is "four"
		freq=(WordFrequencyTerm)topTerms.get(4);
		Assertions.assertTrue(freq.getTerm().equals("six"));
		// print all words by global rank
		System.out.println("\n+++ all words by global rank");
		analyzer.printGlobalWordFrequency();
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
