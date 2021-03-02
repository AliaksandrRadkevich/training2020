package by.training.text.logic;

import java.util.Comparator;

import by.training.text.domain.LanguageUnit;

public class RepeatingLettersComparator implements Comparator<LanguageUnit>{
    private String letter;

    public RepeatingLettersComparator(String letter) {
        this.letter = letter;
    }

    public int countRepeatingLetter(String word) {
        int count = 0;

        String letterToLowerCase = letter.toLowerCase();
        String wordToLowerCase = word.toLowerCase();

        for (int i = 0; i < wordToLowerCase.length(); i++) {
            if (letterToLowerCase.indexOf(wordToLowerCase.charAt(i))>=0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int compare(LanguageUnit s1, LanguageUnit s2) {
        return Integer.compare(countRepeatingLetter(s1.get()), countRepeatingLetter(s2.get()));
    }
}
