package org.example;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CalculateSentenceScoreTest {

    @Test
    void testNullMap() {
        // Branch coverage: B1A, R1
        assertEquals(0, Analyzer.calculateSentenceScore(null, "hello"));
    }

    @Test
    void testEmptyMap() {
        // Branch coverage: B1B, R1
        assertEquals(0, Analyzer.calculateSentenceScore(Collections.emptyMap(), "hello"));
    }

    @Test
    void testNullSentence() {
        // Branch coverage: B2A, R2
        assertEquals(0, Analyzer.calculateSentenceScore(Collections.emptyMap(), null));
    }

    @Test
    void testEmptySentence() {
        // Branch coverage: B2B, R2
        assertEquals(0, Analyzer.calculateSentenceScore(Collections.emptyMap(), ""));
    }

    @Test
    void testWordNotInMap() {
        // Branch coverage: L1, B4, B5, B7, R4
        HashMap<String, Double> map = new HashMap<>();
        map.put("dogs", 1.0);
        map.put("are", 2.0);

        assertEquals(1, Analyzer.calculateSentenceScore(map, "Dogs are funny"));
    }

    @Test
    void testInvalidWords() {
        // Branch coverage: L1, B3, B4, B7, R4
        HashMap<String, Double> map = new HashMap<>();
        map.put("dogs", 0.5);
        map.put("are", 1.0);
        map.put("smart", 2.0);

        assertEquals(1.5 / 2.0, Analyzer.calculateSentenceScore(map, "Dogs are ?smart"));
    }

    @Test
    void testToLowerLetters() {
        // Branch coverage: L1, B3, B4, B7, R4
        HashMap<String, Double> map = new HashMap<>();
        map.put("dogs", 0.5);
        map.put("are", 1.0);
        map.put("smart", 2.0);

        assertEquals(3.5 / 3.0, Analyzer.calculateSentenceScore(map, "DOGS aRe smart"));
    }

    @Test
    void testNoMatchingWords() {
        // Branch coverage: L1, B3, B5, B6, R3
        HashMap<String, Double> map = new HashMap<>();

        assertEquals(0, Analyzer.calculateSentenceScore(map, "DOGS aRe ?smart"));
    }

    @Test
    void testSingleWordInput() {
        // Branch coverage: L1, B4, B7, R4
        HashMap<String, Double> map = new HashMap<>();
        map.put("dogs", 0.5);
        map.put("are", 1.0);
        map.put("smart", 2.0);

        assertEquals(0.5, Analyzer.calculateSentenceScore(map, "Dogs"));
    }

    @Test
    void testDoubleCalculation() {
        // Branch coverage: L1, B4, B7, R4
        HashMap<String, Double> map = new HashMap<>();
        map.put("dogs", 0.31245);
        map.put("are", 0.12345);
        map.put("smart", 0.54321);

        assertEquals(0.32637, Analyzer.calculateSentenceScore(map, "DOGS aRe smart"));
    }
}