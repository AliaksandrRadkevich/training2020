package by.training.text.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.text.domain.CompositeLanguageUnit;
import by.training.text.domain.SimpleLanguageUnit;

public class SearcherForSentences implements Searcher {
    private SearcherForWords root = null;
    private final String REGEX_SENTENCE = "([A-Z]+[^\\.].*?[a-z.][\\.\\?\\!]\\s)(?=[^a-z])"; 

    public SearcherForSentences(SearcherForWords root) {
        this.root = root;
    }

    private List<String> searchSentencesInternal(String text) {
        List<String> sentences = new ArrayList<String>();

        Pattern pattern = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = pattern.matcher(text + "\s\s");

        while (matcher.find()){
            String sentence = matcher.group();

            sentences.add(sentence);
        }

        return sentences;
    }

    public CompositeLanguageUnit handleRequest(String text) {        
        CompositeLanguageUnit compositeLanguageUnit = new CompositeLanguageUnit();

        for (String sentence : searchSentencesInternal(text)) {
//            System.out.println(sentence);
            if (root == null) {
                compositeLanguageUnit.addComponent(new SimpleLanguageUnit(sentence));
            } else {
                compositeLanguageUnit.addComponent(root.handleRequest(sentence));
            }
        }

        return compositeLanguageUnit;
    }
}
