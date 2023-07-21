package com.georgeludwigtechnology.textanalyzer;

import java.util.Comparator;

public class TfIdfComparator implements Comparator<TfIdfTerm>{

	@Override
	public int compare(TfIdfTerm arg0, TfIdfTerm arg1) {
		// start with tfidf
		if(arg0.getTfIdf()>arg1.getTfIdf())
			return 1;
		if(arg0.getTfIdf()<arg1.getTfIdf())
			return -1;
		// if tfidf is equal, use frequency
		if(arg0.getFrequency()>arg1.getFrequency())
			return 1;
		if(arg0.getFrequency()<arg1.getFrequency())
			return -1;
		return 0;
	}

}
