package by.task.processingstrings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String FILENAME = "Text.txt";

    public static void main(String[] args) {
        String text = readFile();
        String replaceText = handleSentences(text);
        System.out.println(replaceText);
    }

    private static String readFile() {
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String text;
            while ((text = br.readLine()) != null) {
                result.append(text).append(" ");
            }
        } catch (IOException ex) {
            System.out.println("Проблемы считывания текста");
        }
        return result.toString();
    }

    private static String handleSentences(String text) {
        text = text.replaceAll("\\s+ | \\t | [\n\r]+", " ").trim();
        text = text.replaceAll("\\.\\s", ".##").replaceAll("!\\s", "!##").replaceAll("\\?\\s", "?##");
        String[] sentences = text.split("##");
        String constructedText;
        List<String> sentenceList = new ArrayList<>();
        for (String sentence : sentences) {
            sentenceList.add(handleSentence(sentence));
        }
        constructedText = String.join(" ", sentenceList);
        return constructedText;
    }

    private static String handleSentence(String sentence) {
        StringBuilder constructedSentences = new StringBuilder();
        String[] words = sentence.split(" ");
        List<String> wordsList = Arrays.asList(words);
        if (wordsList.size() > 1) {
            replaceWords(wordsList);
        }
        constructedSentences.append(String.join(" ", wordsList));
        return constructedSentences.toString();
    }

    private static void replaceWords(List<String> wordsList) {
        String firstWord = wordsList.get(0);
        String lastWord = wordsList.get(wordsList.size() - 1);
        String firstPunctuationMark = "";
        String replacedWord;
        String lastPunctuationMark = "";
        if (firstWord.matches(".*[,;:]")) {
            firstPunctuationMark = firstWord.substring(firstWord.length() - 1, firstWord.length());
            replacedWord = firstWord.substring(0, firstWord.length() - 1);
        } else {
            replacedWord = firstWord;
        }
        if (lastWord.matches(".*[.?!]")) {
            lastPunctuationMark = lastWord.substring(lastWord.length() - 1, lastWord.length());
            wordsList.set(0, lastWord.substring(0, lastWord.length() - 1) + firstPunctuationMark);
        } else {
            wordsList.set(0, lastWord + firstPunctuationMark);
        }
        wordsList.set(wordsList.size() - 1, replacedWord + lastPunctuationMark);
    }
}
