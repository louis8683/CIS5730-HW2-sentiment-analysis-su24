package org.example;

import java.util.*;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		/*
		 * Implement this method in Part 2
		 */

		if (sentences == null || sentences.isEmpty()) { // Branch 1
			return Collections.emptyMap(); // Return 1
		}

		HashMap<String, Double> wordScores = new HashMap<>();
		HashMap<String, Integer> wordCounts = new HashMap<>();

		for (Sentence sentence : sentences) { // Loop 1
			StringTokenizer tokenizer = new StringTokenizer(sentence.getText());
			while (tokenizer.hasMoreTokens()) { // Loop 2
				String token = tokenizer.nextToken();
				if (!Character.isLetter(token.charAt(0))) { // token cannot be empty, Branch 2
					continue; // Continue 1
				}
				token = token.toLowerCase();
				// New token.
				if (wordScores.containsKey(token)) { // Branch 3
					Double scores = wordScores.get(token);
					int count = wordCounts.get(token);
					wordScores.put(token, ((scores * count) + sentence.getScore()) / (count + 1));
					wordCounts.put(token, wordCounts.get(token) + 1);
				}
				// Seen the token before.
				else { // Branch 4
					wordScores.put(token, (double) sentence.getScore());
					wordCounts.put(token, 1);
				}
			}
		}
		return wordScores; // Return 2
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		/*
		 * Implement this method in Part 3
		 */

		if (wordScores == null || wordScores.isEmpty()) // Branch 1
			return 0; // Return 1

		if (sentence == null || sentence.isEmpty()) { // Branch 2
			return 0; // Return 2
		}

		StringTokenizer tokenizer = new StringTokenizer(sentence);
		double score = 0;
		int count = 0;
		while (tokenizer.hasMoreTokens()) { // Loop 1
			String token = tokenizer.nextToken();
			if (!Character.isLetter(token.charAt(0))) { // Branch 3
				continue; // Continue 1
			}
			token = token.toLowerCase();
			if (wordScores.containsKey(token)) { // Branch 4
				score += wordScores.get(token);
				count++;
			}
			else { // Branch 5
				score += 0;
				count++;
			}
		}

		if (count == 0) // Branch 6
			return 0; // Return 3
		else // Branch 7
			return (double) score / count; // Return 4
	}
}
