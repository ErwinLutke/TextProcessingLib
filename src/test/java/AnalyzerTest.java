import main.java.Analyzer;
import main.java.WordFrequency;
import main.java.WordFrequencyAnalyzer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    WordFrequencyAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new Analyzer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateHighestFrequencyNullTextShouldThrowNullPointerException() {
        String text = null;
        assertThrows(NullPointerException.class, () -> analyzer.calculateHighestFrequency(text));
    }

    @Test
    void calculateHighestFrequencyEmptyStringShouldReturnZero() {
        String text = "";
        assertEquals(0, analyzer.calculateHighestFrequency(text));
    }

    @Test
    void calculateHighestFrequencySingleWordShouldReturnOne() {
        String text = "Hello, World";
        assertEquals(1, analyzer.calculateHighestFrequency(text));
    }

    @Test
    void calculateHighestFrequencyDoubleWordShouldReturnTwo() {
        String text = "Hello Hello, World";
        assertEquals(2, analyzer.calculateHighestFrequency(text));
    }

   @Test
    void calculateHighestFrequencyCaseInsensitiveWordShouldReturnThree() {
        String text = "Hello hEllO HELLO, World";
        assertEquals(3, analyzer.calculateHighestFrequency(text));
    }

   @Test
    void calculateHighestFrequencyWithMoreDelimitersThanWordsShouldReturnThree() {
        String text = "Hello hEllO  :::::;;;;;    HELLO,,,,,,, World";
        assertEquals(3, analyzer.calculateHighestFrequency(text));
    }

   @Test
    void calculateFrequencyForWordCaseInsensitiveWordShouldReturnThree() {
        String text = "Hello hEllO HELLO, World";
        assertEquals(3, analyzer.calculateHighestFrequency(text));
    }

    @Test
    void calculateFrequencyForWordNullTextShouldThrowNullPointerException() {
        String text = null;
        String word = "Hello";
        assertThrows(NullPointerException.class, () -> analyzer.calculateFrequencyForWord(text, word));
    }

    @Test
    void calculateFrequencyForWordNullWordShouldThrowNullPointerException() {
        String text = "Hello, World";
        String word = null;
        assertThrows(NullPointerException.class, () -> analyzer.calculateFrequencyForWord(text, word));
    }

    @Test
    void calculateFrequencyForWordEmptyStringsShouldGiveZero() {
        String text = "";
        String word = "";
        assertEquals(0, analyzer.calculateFrequencyForWord(text, word));
    }

    @Test
    void calculateFrequencyForWordHelloCaseInsensitiveShouldGiveTwo() {
        String text = "Hello, helLo, HeLLoo HHelloo, World";
        String word = "hello";
        assertEquals(2, analyzer.calculateFrequencyForWord(text, word));
    }

    @Test
    void calculateMostFrequentNWordsNullTextShouldThrowNullPointerException() {
        String text = null;
        assertThrows(NullPointerException.class, () -> analyzer.calculateMostFrequentNWords(text, 0));
    }

    @Test
    void calculateMostFrequentNWordsNegativeNumberShouldThrowNumberFormatException() {
        String text = "";
        assertThrows(NumberFormatException.class, () -> analyzer.calculateMostFrequentNWords(text, -3));
    }

    @Test
    void calculateMostFrequentNWordsEmptyTextShouldGiveEmptyList() {
        String text = "";
        assertEquals(0, analyzer.calculateMostFrequentNWords(text, 0).size());
    }

    @Test
    void calculateMostFrequentNWordsLimitSetToNineWithFiveWordsInListShouldReturnFive() {
        String text = "Hello, this is World speaking!";
        assertEquals(5, analyzer.calculateMostFrequentNWords(text, 9).size());
    }

    @Test
    void calculateMostFrequentNWordsListReturnsInProperOrder() {
        String text = "The sun shines over the lake";

        List<WordFrequency> words =  analyzer.calculateMostFrequentNWords(text, 4);
        assertEquals(4, words.size());

        assertEquals("the",         words.get(0).getWord());
        assertEquals(2,             words.get(0).getFrequency());

        assertEquals("lake",        words.get(1).getWord());
        assertEquals(1,             words.get(1).getFrequency());

        assertEquals("over",        words.get(2).getWord());
        assertEquals(1,             words.get(2).getFrequency());

        assertEquals("shines",      words.get(3).getWord());
        assertEquals(1,             words.get(3).getFrequency());
    }
}