package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		/*
		 * Implement this method in Part 1
		 */

		// Throw exception if filename is null
		if (filename == null) {
			throw new IllegalArgumentException("filename cannot be null");
		}

		HashSet<Sentence> sentences = new HashSet<>();

		// Open the file
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				int index = line.indexOf(' ');

				// Handle error: not formatted as "[#] [sentence]"
				if (index == -1) {
					continue;
				}

				try {
					int score = Integer.parseInt(line.substring(0, index));
					// Handle error: number not within [-2, 2]
					if (score < -2 || score > 2) {
						continue;
					}
					sentences.add(new Sentence(score, line.substring(index + 1)));
				} catch (NumberFormatException e) {
					// Handle error: not starting with int
					continue;
				}
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found: " + filename, e);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sentences;
	}
}
