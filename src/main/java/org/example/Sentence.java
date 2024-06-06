package org.example;

import java.util.Objects;

/**
 * @author Chris Murphy
 *
 * This class represents a single sentence from the input file.
 * 
 */


public class Sentence {
	
	/**
	 * The sentiment score for the sentence. Should be in the range [-2, 2]
	 */
	private int score;
	
	/**
	 * The text contained in the sentence. 
	 */
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Sentence sentence = (Sentence) o;
		return score == sentence.score && text.equals(sentence.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, text);
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	
}
