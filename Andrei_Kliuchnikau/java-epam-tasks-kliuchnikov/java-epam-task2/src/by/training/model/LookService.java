package by.training.model;

import by.training.domain.PunctuationMark;
import by.training.domain.Sentence;
import by.training.domain.Text;
import by.training.domain.Word;
import by.training.domain.abstractions.LanguageUnit;

import java.util.*;

/*
Looking for the GREATEST number of sentences in the text, that contain the same words.
At first, find the word that is repeated in the largest number of sentences,
and then we look for these sentences themselves.
 */

public class LookService {
    public List<Sentence> DuplicateWordsSentence(Text text) {
        List<Sentence> duplicateWordsSentences = new ArrayList<>();
        List<Sentence> textAllSentences = (ArrayList) text.getComponentsList();
        Map<String, Integer> repeatingWords = new HashMap<>();
        for (Sentence sentence : textAllSentences) {
            Set<LanguageUnit> words = new HashSet<>(sentence.getComponentsList());
            words.removeIf(unit -> unit instanceof PunctuationMark);
            for (LanguageUnit word : words) {
                String stringWord = word.getUnitString().toLowerCase();
                if (repeatingWords.containsKey(stringWord)) {
                    repeatingWords.put(stringWord, repeatingWords.get(stringWord) + 1);
                } else {
                    repeatingWords.put(stringWord, 1);
                }
            }
        }
        Map.Entry<String, Integer> mostRepeatingWord = null;
        for (Map.Entry<String, Integer> entry : repeatingWords.entrySet()) {
            if (mostRepeatingWord == null || entry.getValue() > mostRepeatingWord.getValue()) {
                mostRepeatingWord = entry;
            }
        }
        for (Sentence sentence : textAllSentences) {
            assert mostRepeatingWord != null;
            if (sentence.getComponentsList().contains(new Word(mostRepeatingWord.getKey()))) {
                duplicateWordsSentences.add(sentence);
            }
        }
        return duplicateWordsSentences;
    }
}