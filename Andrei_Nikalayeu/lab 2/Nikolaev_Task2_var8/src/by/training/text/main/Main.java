package by.training.text.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

import by.training.text.printer.ConsolePrinter;
import by.training.text.printer.Printer;
import by.training.text.domain.CompositeLanguageUnit;
import by.training.text.domain.LanguageUnit;
import by.training.text.logic.AlphabetComparator;
import by.training.text.logic.RepeatingLettersComparator;
import by.training.text.reader.FileTextReader;
import by.training.text.reader.Reader;
import by.training.text.chain.*;

public class Main {
    public static void main(String[] args) {  
        String stringText = null;
        Printer printer = new ConsolePrinter();

        try {
            Reader fileTextReader = new FileTextReader("input_text.txt");
            stringText = fileTextReader.read();
            stringText = stringText.replaceAll("\t+", "\s").replaceAll("\s+", "\s").trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//        Сначала, в любом случае прибавляем два пробела к тексту
//        Потом уже парсим текст на предложения и каждое предложение тримим по отдельности.
//        Тем самым мы убираем лишние пробелы и табуляции

        SearcherForWords searcherForWords = new SearcherForWords();  
        SearcherForSentences searcherForSentences = new SearcherForSentences(searcherForWords); 

        CompositeLanguageUnit compositeLanguageUnit = searcherForSentences.handleRequest(stringText);

        printer.println("===Representing Text as Composite Objects===");
        for (LanguageUnit languageUnit : compositeLanguageUnit.getComponents()) {
            printer.println(languageUnit.get());
        }

        Scanner sc = new Scanner(System.in);

        printer.print("\nEnter a letter to sort words: ");
        String letter = sc.next();
        sc.close();

        printer.println("\n===Sort words in text===");
        RepeatingLettersComparator repeatingLettersComparator = new RepeatingLettersComparator(letter);
        AlphabetComparator alphabetComparator = new AlphabetComparator();

        Comparator<LanguageUnit> twoComparators = repeatingLettersComparator.thenComparing(alphabetComparator);

        CompositeLanguageUnit compositeLanguageUnitForSort = searcherForWords.handleRequest(stringText);

        List<LanguageUnit> listLanguageUnit = compositeLanguageUnitForSort.getComponents();

        Collections.sort(listLanguageUnit, twoComparators);

        for (LanguageUnit languageUnit : listLanguageUnit) {
           if (!languageUnit.get().contains("\s")) {
               printer.println(languageUnit.get());
           }
        }

        printer.println("\n===OK===");
    }
}
