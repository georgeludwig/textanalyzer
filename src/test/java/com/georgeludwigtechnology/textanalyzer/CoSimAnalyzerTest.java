package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class CoSimAnalyzerTest {

	static final String DOC1 = "LSI tutorials and fast tracks.";
	static final String DOC2 = "Books on #semantic analysis.";
	static final String DOC3 = "Learning latent #semantic indexing.";
	static final String DOC4 = "Advances in structures and advances in indexing.";
	static final String DOC5 = "Analysis of latent structures.";
	static final String QUERY = "latent #semantic indexing";

	@BeforeEach
	public void setUp() throws Exception {}


	@Test
	public void testApp() throws Exception {
		// NOTE: test values were painstakingly, manually calculated
		// set global Document optional params
		Document.setCleanPunctuation(true);
		Document.setUseStemming(true);
		Document.setRemoveStopWords(true);
		// create the analyzer
		CoSimAnalyzer analyzer=new CoSimAnalyzer();
		// create the docs...params are cleanPunctuation, useStemming, removeStopWords
		Document doc1=new Document(DOC1);
		analyzer.addDocument(doc1);
		Document doc2=new Document(DOC2);
		analyzer.addDocument(doc2);
		Document doc3=new Document(DOC3);
		analyzer.addDocument(doc3);
		Document doc4=new Document(DOC4);
		analyzer.addDocument(doc4);
		Document doc5=new Document(DOC5);
		analyzer.addDocument(doc5);
		// create the query
		Document query=new Document(QUERY);
		analyzer.setQuery(query);
		// analyze
		analyzer.analyze();
		Document topDoc=analyzer.getDocuments().firstElement();
		Assertions.assertTrue(topDoc==doc3);
		Assertions.assertTrue(topDoc.getCoSim()==0.7021397624027602);
		List<Word>sortedWords=analyzer.getSortedWords();
		Assertions.assertTrue(sortedWords.get(0).getWord().contains("analysi"));
		Assertions.assertTrue(sortedWords.get(0).getWeight()==1.0208020694198054);
		Assertions.assertTrue(doc1.getOriginalText().equals(DOC1));
		Assertions.assertTrue(doc2.getOriginalText().equals(DOC2));
		Assertions.assertTrue(doc3.getOriginalText().equals(DOC3));
		Assertions.assertTrue(doc4.getOriginalText().equals(DOC4));
		Assertions.assertTrue(doc5.getOriginalText().equals(DOC5));
		Assertions.assertTrue(query.getOriginalText().equals(QUERY));

		analyzer.getDocuments().stream().forEach(d -> System.out.println(d));
		System.out.println(query);
		analyzer.getSortedWords().stream().forEach(w -> System.out.println(w));

		analyzer.reset();
		
		// re-analyze
		analyzer.analyze();
		topDoc=analyzer.getDocuments().firstElement();
		Assertions.assertTrue(topDoc==doc3);
		Assertions.assertTrue(topDoc.getCoSim()==0.7021397624027602);
		sortedWords=analyzer.getSortedWords();
		Assertions.assertTrue(sortedWords.get(0).getWord().contains("analysi"));
		Assertions.assertTrue(sortedWords.get(0).getWeight()==1.0208020694198054);
		Assertions.assertTrue(doc1.getOriginalText().equals(DOC1));
		Assertions.assertTrue(doc2.getOriginalText().equals(DOC2));
		Assertions.assertTrue(doc3.getOriginalText().equals(DOC3));
		Assertions.assertTrue(doc4.getOriginalText().equals(DOC4));
		Assertions.assertTrue(doc5.getOriginalText().equals(DOC5));
		Assertions.assertTrue(query.getOriginalText().equals(QUERY));
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
