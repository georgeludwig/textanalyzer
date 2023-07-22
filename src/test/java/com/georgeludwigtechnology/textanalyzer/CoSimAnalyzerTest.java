package com.georgeludwigtechnology.textanalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


public class CoSimAnalyzerTest {
	
	@BeforeEach
	public void setUp() throws Exception {}
	
	@Test
	public void testApp() throws Exception {
		// create the analyzer
		CoSimAnalyzer analyzer=new CoSimAnalyzer();
		// set optional doc params
//		Document.setCleanPunctuation(true);
//		Document.setUseStemming(true);
//		Document.setRemoveStopWords(true);
		// create the docs
		Document doc1=new Document(true,true,true);
		doc1.setText("LSI tutorials and fast tracks.");
		analyzer.addDocument(doc1);
		Document doc2=new Document(true,true,true);
		doc2.setText("Books on #semantic analysis.");
		analyzer.addDocument(doc2);
		Document doc3=new Document(true,true,true);
		doc3.setText("Learning latent #semantic indexing.");
		analyzer.addDocument(doc3);
		Document doc4=new Document(true,true,true);
		doc4.setText("Advances in structures and advances in indexing.");
		analyzer.addDocument(doc4);
		Document doc5=new Document(true,true,true);
		doc5.setText("Analysis of latent structures.");
		analyzer.addDocument(doc5);
		// create the query
		Document query=new Document(true,true,true);
		query.setText("latent #semantic indexing");
		analyzer.setQuery(query);
		// analyze
		analyzer.analyze();
		Document topDoc=analyzer.getDocuments().firstElement();
		Assertions.assertTrue(topDoc==doc3);
		Assertions.assertTrue(topDoc.getCoSim()==0.7021397624027602);
		List<Word>sortedWords=analyzer.getSortedWords();
		Assertions.assertTrue(sortedWords.get(0).getWord().contains("analysi"));
		Assertions.assertTrue(sortedWords.get(0).getWeight()==1.0208020694198054);
		Assertions.assertTrue(doc1.getOriginalText().equals("LSI tutorials and fast tracks."));
		Assertions.assertTrue(doc2.getOriginalText().equals("Books on #semantic analysis."));
		Assertions.assertTrue(doc3.getOriginalText().equals("Learning latent #semantic indexing."));
		Assertions.assertTrue(doc4.getOriginalText().equals("Advances in structures and advances in indexing."));
		Assertions.assertTrue(doc5.getOriginalText().equals("Analysis of latent structures."));
		Assertions.assertTrue(query.getOriginalText().equals("latent #semantic indexing"));
		
		analyzer.reset();
		
		// re-analyze
		analyzer.analyze();
		topDoc=analyzer.getDocuments().firstElement();
		Assertions.assertTrue(topDoc==doc3);
		Assertions.assertTrue(topDoc.getCoSim()==0.7021397624027602);
		sortedWords=analyzer.getSortedWords();
		Assertions.assertTrue(sortedWords.get(0).getWord().contains("analysi"));
		Assertions.assertTrue(sortedWords.get(0).getWeight()==1.0208020694198054);
		Assertions.assertTrue(doc1.getOriginalText().equals("LSI tutorials and fast tracks."));
		Assertions.assertTrue(doc2.getOriginalText().equals("Books on #semantic analysis."));
		Assertions.assertTrue(doc3.getOriginalText().equals("Learning latent #semantic indexing."));
		Assertions.assertTrue(doc4.getOriginalText().equals("Advances in structures and advances in indexing."));
		Assertions.assertTrue(doc5.getOriginalText().equals("Analysis of latent structures."));
		Assertions.assertTrue(query.getOriginalText().equals("latent #semantic indexing"));
	}
	
	@AfterEach
	public void tearDown() throws Exception {}

}
