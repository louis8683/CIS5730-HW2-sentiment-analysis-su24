package org.example;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    String testFilesRoot = "src/test/resources/test-files/";

    @Test
    void testBadFilename() {
        assertThrows(IllegalArgumentException.class, () -> {
            Reader.readFile(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Reader.readFile(testFilesRoot + "12345");
        });
    }

    @Test
    void testSampleValidLine() {
        Set<Sentence> set = Reader.readFile(testFilesRoot + "good_input.txt");
        assertTrue(set.contains(new Sentence(2, "I am learning a lot .")));
    }

    @Test
    void testBadInputOutsideRange() {
        Set<Sentence> set = Reader.readFile(testFilesRoot + "bad_input_outside_range.txt");
        assertEquals(0, set.size());
    }

    @Test
    void testBadInputNotInteger() {
        Set<Sentence> set = Reader.readFile(testFilesRoot + "bad_input_not_integer.txt");
        assertEquals(0, set.size());
    }

    @Test
    void testBadInputNotStartWithNumber() {
        Set<Sentence> set = Reader.readFile(testFilesRoot + "bad_input_not_start_with_number.txt");
        assertEquals(0, set.size());
    }

    @Test
    void testBadInputOnlyNumber() {
        Set<Sentence> set = Reader.readFile(testFilesRoot + "bad_input_only_number.txt");
        assertEquals(0, set.size());
    }


}