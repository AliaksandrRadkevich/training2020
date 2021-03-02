package by.training.text.chain;

import by.training.text.domain.CompositeLanguageUnit;

public interface Searcher{
    CompositeLanguageUnit handleRequest(String string);
}
