package org.example;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CalculateWordScoresTest {

    @Test
    void testNullInput() {
        // Path: B1a - R1
        // Branch coverage: 1A, R1
        assertEquals(0, Analyzer.calculateWordScores(null).size());
    }

    @Test
    void testEmptyInput() {
        // Path: B1B - R1
        // Branch coverage: 1B, R1
        assertEquals(0, Analyzer.calculateWordScores(Collections.emptySet()).size());
    }

    @Test
    void testSampleInput() {
        // Path: L1 - L2 - B3 (x6) - B4 - L2 - B3 (x2) - B2 - C1 - B4 - B3 (x5) - B4 - B3 - B4 - R2
        // Branch coverage: B2, B3, B4, L1, L2, C1, R2
        HashSet<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I like cake and could eat cake all day ."));
        sentences.add(new Sentence(1, "I hope the dog does not eat my cake ."));

        Map<String, Double> scores = Analyzer.calculateWordScores(sentences);
        assertEquals(1, scores.get("dog"));
        assertEquals(1.5, scores.get("eat"));
        assertEquals(1.6667, scores.get("cake"), 0.001);
    }

    @Test
    void testIgnoreToken() {
        // Branch coverage: B2, B3, B4, L1, L2, C1, R2
        HashSet<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, "It 's she's she his"));
        sentences.add(new Sentence(1, "! @ # $ % ^ & * ( ) _ +"));
        sentences.add(new Sentence(1, "12345 Hi"));
        Map<String, Double> scores = Analyzer.calculateWordScores(sentences);

        assertEquals(5, scores.size());
        assertTrue(scores.containsKey("it"));
        assertTrue(scores.containsKey("she's"));
        assertTrue(scores.containsKey("she"));
        assertTrue(scores.containsKey("his"));
        assertTrue(scores.containsKey("hi"));
    }

    @Test
    void testToLowerCase() {
        // Branch coverage: B2, B3, B4, L1, L2, C1, R2
        HashSet<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, "It 's She's She His"));
        sentences.add(new Sentence(3, "it 'S she's SHE hIs"));
        Map<String, Double> scores = Analyzer.calculateWordScores(sentences);

        assertEquals(4, scores.size());
        assertEquals(2, scores.get("it"));
        assertEquals(2, scores.get("she's"));
        assertEquals(2, scores.get("she"));
        assertEquals(2, scores.get("his"));
    }

    @Test
    void testAverage() {
        // Branch coverage: B2, B3, B4, L1, L2, C1, R2
        HashSet<Sentence> sentences = new HashSet<>();
        int total = 0, numbers = 100;
        Random random = new Random(12345);
        for (int i = 0; i < numbers; i++) {
            int randomInt = (int) (random.nextInt(5) - 2);
            total += randomInt;
            sentences.add(new Sentence(randomInt, "Test " + i));
        }
        Map<String, Double> scores = Analyzer.calculateWordScores(sentences);

        assertEquals((double) total / numbers, scores.get("test"), 0.00001);
    }

    @Test
    void testNoToken() {
        // Branch coverage: L1 - R2
        HashSet<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(4, ""));
        sentences.add(new Sentence(2, ""));
        Map<String, Double> scores = Analyzer.calculateWordScores(sentences);

        assertEquals(0, scores.size());
    }
}