package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        String[] args = new String[1];
        args[0] = "src/main/java/org/example/" + "reviews.txt";
        Main.main(args);
    }
}