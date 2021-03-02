package by.training.domain;

import by.training.domain.abstractions.ComplexUnit;
import by.training.domain.abstractions.LanguageUnit;

import java.util.*;
import java.util.regex.Pattern;

public class Text extends ComplexUnit {
    public Text(String unit) {
        super(unit);
    }

    @Override
    public List<LanguageUnit> getComponentsList() {
        if (componentsList != null) {
            return componentsList;
        } else {
            Scanner scanner = new Scanner(unit);
            componentsList = new ArrayList<>();
            Pattern patternDelimiter = Pattern.compile("[.]\\h+|\\v+|[!?]+", Pattern.UNICODE_CHARACTER_CLASS);
            scanner.useDelimiter(patternDelimiter);
            while (scanner.hasNext()) {
                String str = (scanner.next()).replaceAll("[\\t\\s]{2,}", " ").trim();
                String strDelimiter = scanner.findInLine(patternDelimiter);
                if (strDelimiter == null) {
                    componentsList.add(new Sentence(str));
                } else {
                    componentsList.add(new Sentence((str + strDelimiter).trim()));
                }
            }
            scanner.close();
            return componentsList;
        }
    }
}