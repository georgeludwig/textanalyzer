package com.georgeludwigtechnology.textanalyzer;

public class Word implements Comparable<Word> {
	
	public Word() {}
	
	public Word(String word) {
		setWord(word);
	}
	
	private String word;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private double frequency;

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public void incFrequency() {
		frequency++;
	}
	
	public void incFrequency(double increment) {
		frequency=frequency+increment;
	}
	
	private double weight;

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Word word) {
		double thisScore=getWeight();
		double thatScore=word.getWeight();
		if(thisScore>thatScore)
			return -1;
		if(thisScore<thatScore)
			return 1;
		return 0;
	}

	public String toString() {
		return "word: '"+getWord()+"' weight: "+getWeight()+" freq: "+getFrequency();
	}
}
