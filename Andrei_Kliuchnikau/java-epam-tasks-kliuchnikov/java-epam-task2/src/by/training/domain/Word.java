package by.training.domain;

import by.training.domain.abstractions.ComplexUnit;
import by.training.domain.abstractions.LanguageUnit;

import java.util.List;

public class Word extends ComplexUnit {
    public Word(String unit) {
        super(unit);
    }

    @Override
    public List<LanguageUnit> getComponentsList() {
        throw new UnsupportedOperationException("Операция не поддерживается");   //Not use in this task with this type of object
    }
}