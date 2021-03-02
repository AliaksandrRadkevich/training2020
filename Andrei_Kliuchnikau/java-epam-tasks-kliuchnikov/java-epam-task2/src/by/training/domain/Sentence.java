package by.training.domain;

import by.training.domain.abstractions.ComplexUnit;
import by.training.domain.abstractions.LanguageUnit;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence extends ComplexUnit {
    Sentence(String unit) {
        super(unit);
    }

    @Override
    public List<LanguageUnit> getComponentsList() {
        if (componentsList != null) {
            return componentsList;
        } else {
            componentsList = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\+[0-9]{3}\\([0-9]{2}\\)[0-9]{3}-[0-9]{2}-[0-9]{2}|" +
                    "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b|" +
                    "[\\w’'/-]+|\\p{Punct}", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(unit);
            String groupUnit;
            while (matcher.find()) {
                groupUnit = matcher.group();
                if (PunctuationMark.isPunctuationMark(groupUnit)) {
                    componentsList.add(new PunctuationMark(groupUnit.charAt(0)));
                } else {
                    componentsList.add(new Word(groupUnit));
                }
            }
            return componentsList;
        }
    }
}