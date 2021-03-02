package by.training.text.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.text.domain.CompositeLanguageUnit;
import by.training.text.domain.SimpleLanguageUnit;

public class SearcherForWords implements Searcher {
    private final String REGEX_WORD = "([\\S]+\\b)|([\\S]*[\\s])";

    private List<String> searchWordsInternal(String sentence) {
        List<String> words = new ArrayList<String>();

        Pattern pattern = Pattern.compile(REGEX_WORD);
        Matcher matcher = pattern.matcher(sentence);

        while (matcher.find()){
            String word = matcher.group();
            words.add(word);
        }

        return words;
    }

    public CompositeLanguageUnit handleRequest(String sentence) {        
        CompositeLanguageUnit compositeLanguageUnit = new CompositeLanguageUnit();

        for (String word : searchWordsInternal(sentence)) {
//            System.out.println(word);
            compositeLanguageUnit.addComponent(new SimpleLanguageUnit(word));
        }

        return compositeLanguageUnit;    
    }
}
