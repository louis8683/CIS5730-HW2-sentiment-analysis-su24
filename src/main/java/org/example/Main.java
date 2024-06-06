package org.example;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * Main class for the sentiment analysis program.
 * 
 */
public class Main {
	
	public static void main(String[] args) {
		
		/* Implement this in Part 4 */

		if (args.length == 0) {
			System.out.println("bad input file");
			return;
		}

		// 1. Pass the name of the input file to the readFile method to get the Set of Sentence objects
		String filename = args[0];
		try {
			Set<Sentence> sentences = Reader.readFile(filename);

			// 2. Pass the Set of Sentence objects to the calculateWordScores method to get the Map of words and their scores
			Map<String, Double> scores = Analyzer.calculateWordScores(sentences);

			while (true) {
				// 3. Prompt the user to enter a sentence
				Scanner scanner = new Scanner(System.in);
				System.out.println("Please enter a sentence: ");
				String inputSentence = scanner.nextLine();

				// 5. Go back to step 3 and continue looping; however, if the sentence entered by the user contains only the word “quit” (case-sensitive), stop looping and terminate the program.
				if (inputSentence.equals("quit")) {
					break;
				}

				// 4. Pass the Map and the sentence that the user entered to the calculateSentenceScore method, and print out the result
				double score = Analyzer.calculateSentenceScore(scores, inputSentence);
				System.out.println("Sentiment score: " + score);
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("bad input file");
			return;
		}





	}

}
