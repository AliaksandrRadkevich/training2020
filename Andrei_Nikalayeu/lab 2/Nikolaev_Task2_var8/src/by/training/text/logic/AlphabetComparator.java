package by.training.text.logic;

import java.util.Comparator;

import by.training.text.domain.LanguageUnit;

public class AlphabetComparator implements Comparator<LanguageUnit> {
    @Override
    public int compare(LanguageUnit o1, LanguageUnit o2) {

        String o1ToLowerCase = o1.get().toLowerCase();
        String o2ToLowerCase = o2.get().toLowerCase();

        return o1ToLowerCase.compareTo(o2ToLowerCase);
    }
}
