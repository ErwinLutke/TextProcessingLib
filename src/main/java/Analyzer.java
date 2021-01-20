package main.java;

import java.util.*;

public class Analyzer implements WordFrequencyAnalyzer {

    private String[] separateWords(String text) {
        return text.split("[^a-zA-Z]");
    }

    @Override
    public int calculateHighestFrequency(String text) throws NullPointerException{
        if (text == null) {
            throw new NullPointerException("the var 'text' is not set");
        }

        int highestFrequency = 0;
        String[] separatedWords = separateWords(text);

        for (String word : separatedWords) {
            if(!word.isEmpty()) {
                int frequency = calculateFrequencyForWord(text, word);
                highestFrequency = Math.max(frequency, highestFrequency);
            }
        }
        return highestFrequency;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) throws NullPointerException {
        if (text == null) {
            throw new NullPointerException("the var 'text' is not set");
        } else if (word == null) {
            throw new NullPointerException("the var 'word' is not set");
        }

        int frequency = 0;
        String[] separatedWords = separateWords(text);

        for (String separatedWord : separatedWords) {
            if(!word.isEmpty() && separatedWord.equalsIgnoreCase(word)) {
                frequency++;
            }
        }
        return frequency;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) throws NullPointerException,
            NumberFormatException {
        if (text == null) {
            throw new NullPointerException("the var 'text' is not set");
        } else if (n < 0) {
            throw new NumberFormatException("the var 'n' must be a positive number");
        }

        List<WordFrequency> wordFrequencies = new ArrayList<>();
        String[] separatedWords = separateWords(text);
        Set<String> uniqueWords = new HashSet<>();

        for (String word : separatedWords) {
            if(!word.isEmpty()) {
                uniqueWords.add(word.toLowerCase());
            }
        }

        for (String word : uniqueWords) {
            int frequency = calculateFrequencyForWord(text, word);
            wordFrequencies.add(new WordFrequencyImpl(word, frequency));
        }

        n = Math.min(n, wordFrequencies.size());

        wordFrequencies.sort(Comparator.comparingInt(WordFrequency::getFrequency).reversed()
                .thenComparing(WordFrequency::getWord));
        wordFrequencies = new ArrayList<>(wordFrequencies.subList(0, n));

        return wordFrequencies;
    }
}
