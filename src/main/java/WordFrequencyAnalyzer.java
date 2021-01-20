package main.java;

import java.util.List;

public interface WordFrequencyAnalyzer {
    /**
     * Searches a text for the highest frequency of a word
     * @param text to search
     * @return int
     */
    int calculateHighestFrequency(String text);

    /**
     * Searches a text for the frequency of the specified word
     * @param text to search
     * @param word used for search
     * @return int
     */
    int calculateFrequencyForWord (String text, String word);

    /**
     * Searches a text for the most frequent used words. When n exceeds total words available, n will be set to
     * the number of words available
     * @param text to search
     * @param n the number of words to limit the search
     * @return A list<Main.WordFrequency> containing the most used words in lower case coupled with their frequency.
     * The list is sorted by frequency followed by ascending alphabetical order.
     */
    List<WordFrequency> calculateMostFrequentNWords (String text, int n);
}
