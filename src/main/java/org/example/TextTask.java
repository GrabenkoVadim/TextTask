package org.example;

import java.util.ArrayList;
import java.util.List;

public class TextTask {
    private static final String VOWELS = "aeiouAEIOUаеєиіїоуюяАЕЄИІЇОУЮЯ";

    public void execute() {
        try {
            StringBuilder text = new StringBuilder(
                    "Це приклад тексту для лабораторної роботи з Java."
            );

            List<StringBuilder> words  =  splitToWords(text);

            if (words.isEmpty()) {
                throw new IllegalArgumentException("Text does not contain words.");
            }

            sortByVowelCount(words);

            System.out.println("Original text: " + text);
            System.out.println("Sorted words: " + words);

            for (StringBuilder word : words) {
                System.out.println(word + " (" + countWovels(word) + ")");
            }
        }  catch (Exception e) {
            System.out.println("Error while processing text: " + e.getMessage());
        }
    }

    private int countWovels(StringBuilder word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (VOWELS.indexOf(ch) >= 0) {
                count++;
            }
        }

        return count;
    }

    private void sortByVowelCount(List<StringBuilder> words) {
        boolean swapped;
        int n = words.size();

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                int c1 = countWovels(words.get(i));
                int c2 = countWovels(words.get(i + 1));

                if (c1 > c2) {
                    StringBuilder word = words.get(i);
                    words.set(i, words.get(i + 1));
                    words.set(i + 1, word);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    private List<StringBuilder> splitToWords(StringBuilder text) {
        List<StringBuilder> words = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetterOrDigit(ch) || ch == '\'') {
                current.append(ch);
            } else {
                if (!current.isEmpty()) {
                    words.add(new StringBuilder(current));
                    current.setLength(0);
                }
            }
        }

        if (!current.isEmpty()) {
            words.add(current);
        }

        return words;
    }
}
