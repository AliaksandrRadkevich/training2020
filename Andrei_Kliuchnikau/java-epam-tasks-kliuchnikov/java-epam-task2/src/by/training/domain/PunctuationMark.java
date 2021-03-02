package by.training.domain;

import by.training.domain.abstractions.SimpleUnit;

import java.util.regex.Pattern;

public class PunctuationMark extends SimpleUnit {
    PunctuationMark(char punctuationMark) {
        if (Pattern.matches("\\p{Punct}", Character.toString(punctuationMark))) {
            unit = punctuationMark;
        } else {
            System.out.println("This is not a punctuation mark!");
            unit = null;
        }
    }

    static boolean isPunctuationMark(String str) {
        return Pattern.matches("\\p{Punct}", str);
    }
}