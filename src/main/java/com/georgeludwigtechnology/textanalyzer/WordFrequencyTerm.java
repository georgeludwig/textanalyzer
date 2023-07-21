package com.georgeludwigtechnology.textanalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyTerm implements AnalyzerTerm {
		
	public static final int COMPARE_MODE_BY_LOCAL_FREQUENCY=0;
	public static final int COMPARE_MODE_BY_SIMPLE_WEIGHT=1;
	public static final int COMPARE_MODE_BY_GLOBAL_FREQUENCY=2;
	public static final int COMPARE_MODE_BY_GLOBAL_WEIGHT=3;
	public static final int COMPARE_MODE_BY_GLOBAL_RANK=4;
	
	public WordFrequencyTerm() { }
	
	public WordFrequencyTerm(String term) {
		setTerm(term);
	}
	
	private static int compareMode=COMPARE_MODE_BY_LOCAL_FREQUENCY;
	
	public static int getCompareMode() {
		return compareMode;
	}

	public static void setCompareMode(int compareMode) {
		WordFrequencyTerm.compareMode = compareMode;
	}

	private String term="";
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String word) {
		this.term = word;
	}

	private int frequency=0;
	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency=frequency;
	}

	public void incFrequency() {
		this.frequency += 1;
	}
	
	private int rank;
	/**
	 * this field is optional
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(AnalyzerTerm wf) {
		if(getCompareMode()==COMPARE_MODE_BY_LOCAL_FREQUENCY)
			return compareByLocalFrequency(wf);
		if(getCompareMode()==COMPARE_MODE_BY_SIMPLE_WEIGHT)
			return compareBySimpleWeight(wf);
		if(getCompareMode()==COMPARE_MODE_BY_GLOBAL_FREQUENCY)
			return compareByGlobalFrequency(wf);
		if(getCompareMode()==COMPARE_MODE_BY_GLOBAL_WEIGHT)
			return compareByGlobalWeight(wf);
		if(getCompareMode()==COMPARE_MODE_BY_GLOBAL_RANK)
			return compareByGlobalRank(wf);
		return 0;
	}
	
	/**
	 * compare based on the frequency as stored in this and wf
	 * @param wf
	 * @return
	 */
	public int compareByLocalFrequency(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		if(getFrequency()<term.getFrequency())
			return 1 ;
		if(getFrequency()>term.getFrequency())
			return -1;
		if(getTerm().length()<term.getTerm().length())
			return 1 ;
		if(getTerm().length()>term.getTerm().length())
			return -1;
		return 0;
	}
	
	/**
	 * compare based on simple weight of this and wf
	 * @see getSimpleWeight()
	 * @param wf
	 * @return
	 */
	public int compareBySimpleWeight(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		int weightA=getSimpleWeight(this);
		int weightB=getSimpleWeight(term);
		if(weightA<weightB)
			return 1 ;
		if(weightA>weightB)
			return -1;
		if(getTerm().length()<term.getTerm().length())
			return 1 ;
		if(getTerm().length()>term.getTerm().length())
			return -1;
		return 0;
	}
	
	/**
	 * compare based on the global word frequency as stored in the global word frequency map
	 * @param wf
	 * @return
	 */
	public int compareByGlobalFrequency(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		WordFrequencyTerm wf1=WordFrequencyTerm.getGlobalWordFrequencyMap().get(this.getTerm());
		WordFrequencyTerm wf2=WordFrequencyTerm.getGlobalWordFrequencyMap().get(term.getTerm());
		if(wf1==null && wf2!=null)
			return 1;
		if(wf1!=null && wf2==null)
			return -1;
		if(wf1==null && wf2==null)
			return 0;
		if(wf1.getFrequency()<wf2.getFrequency())
			return -1 ;
		if(wf1.getFrequency()>wf2.getFrequency())
			return 1;
		if(wf1.getTerm().length()<wf2.getTerm().length())
			return 1 ;
		if(wf1.getTerm().length()>wf2.getTerm().length())
			return -1;
		return 0;
	}
	
	/**
	 * compare based on global weight of this and wf
	 * @see getGlobalWeight()
	 * @param wf
	 * @return
	 */
	public int compareByGlobalWeight(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		WordFrequencyTerm wf1=WordFrequencyTerm.getGlobalWordFrequencyMap().get(this.getTerm());
		WordFrequencyTerm wf2=WordFrequencyTerm.getGlobalWordFrequencyMap().get(term.getTerm());
		if(wf1==null && wf2!=null)
			return 1;
		if(wf1!=null && wf2==null)
			return -1;
		if(wf1==null && wf2==null)
			return 0;
		if(getGlobalWeight(wf1)<getGlobalWeight(wf2))
			return -1 ;
		if(getGlobalWeight(wf1)>getGlobalWeight(wf2))
			return 1;
		if(wf1.getTerm().length()<wf2.getTerm().length())
			return 1 ;
		if(wf1.getTerm().length()>wf2.getTerm().length())
			return -1;
		return 0;
	}
	
	/**
	 * based on the global word rank as stored in the global word frequency map
	 * @see getSimpleWeight()
	 * @param wf
	 * @return
	 */
	public int compareByGlobalRank(AnalyzerTerm wf) {
		WordFrequencyTerm wf1=WordFrequencyTerm.getGlobalWordFrequencyMap().get(this.getTerm());
		WordFrequencyTerm wf2=WordFrequencyTerm.getGlobalWordFrequencyMap().get(wf.getTerm());
		if(wf1==null && wf2!=null)
			return 1;
		if(wf1!=null && wf2==null)
			return -1;
		if(wf1==null && wf2==null)
			return 0;
		if(wf1.getRank()<wf2.getRank())
			return 1 ;
		if(wf1.getRank()>wf2.getRank())
			return -1;
		if(wf1.getTerm().length()<wf2.getTerm().length())
			return 1 ;
		if(wf1.getTerm().length()>wf2.getTerm().length())
			return -1;
		return 0;
	}
	
	/**
	 * returns wf.frequency*wf.word.length
	 * @param wf
	 * @return
	 */
	public static int getSimpleWeight(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		return term.getFrequency()*term.getTerm().length();
	}
	
	/**
	 * returns wf.frequency*globalRank
	 * @param wf
	 * @return
	 */
	public static int getGlobalWeight(AnalyzerTerm at) {
		WordFrequencyTerm term=(WordFrequencyTerm)at;
		int rank=0;
		WordFrequencyTerm w=getGlobalWordFrequencyMap().get(term.getTerm());
		if(w!=null)
			rank=w.getRank();
		int weight=term.getFrequency()*rank;
		return weight;
	}
	
	public String toString() {
		if(getCompareMode()==COMPARE_MODE_BY_LOCAL_FREQUENCY || getCompareMode()==COMPARE_MODE_BY_SIMPLE_WEIGHT)
			return getTerm()+" rank:"+getRank()+" freq:"+getFrequency()+" weight:"+getSimpleWeight(this);
		if(getCompareMode()==COMPARE_MODE_BY_GLOBAL_FREQUENCY || getCompareMode()==COMPARE_MODE_BY_GLOBAL_WEIGHT || getCompareMode()==COMPARE_MODE_BY_GLOBAL_RANK)
			return getTerm()+" rank:"+getRank()+" freq:"+getFrequency()+" weight:"+getGlobalWeight(this);
		return null;
	}
	
	private static Map<String,WordFrequencyTerm>globalWordFrequencyMap=new HashMap<String,WordFrequencyTerm>();
	
	public static Map<String, WordFrequencyTerm> getGlobalWordFrequencyMap() {
		return globalWordFrequencyMap;
	}
	
	static {
		try {
			List<String> rawFreqs= FileUtil.readFile("classpath:///textanalyzer/WordFrequencyAndRank.txt");
			for(String rawFreq:rawFreqs) {
				String[]rawFreqArray=rawFreq.split("\t");
				WordFrequencyTerm freq=new WordFrequencyTerm();
				freq.setRank(Integer.parseInt(rawFreqArray[0].trim()));
				freq.setTerm(rawFreqArray[1].trim().toLowerCase());
				freq.setFrequency(Integer.parseInt(rawFreqArray[2].trim()));
				globalWordFrequencyMap.put(freq.getTerm(), freq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private static Set<String>missingWordMap=new HashSet<String>();
//
//	public static Set<String> getMissingWordMap() {
//		return missingWordMap;
//	}
//
//	public static void addMissingWord(String missingWord) {
//		synchronized(getMissingWordMap()) {
//			getMissingWordMap().add(missingWord);
//		}
//	}
	
}
