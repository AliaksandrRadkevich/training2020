package by.epam.jvd.vitebsk.task2.dao;

import java.util.ArrayList;
import java.util.List;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class TextExprObjectCreator {
    public TextExprObjectCreator() {
    }

    public List<TextExpressionObject> create() {

        String regExprWord, regExprPhoneNumber, regExprEmail, regExprLetter, regExprNumber, regExprPunctuationMark,
                regExprOtherSymbol, regExprSentence;

        regExprLetter = "[а-яА-Яa-zA-Z]";
        regExprNumber = "\\d";
        regExprPunctuationMark = "(\\.|!|,|-|\\?|:|;)";
        regExprOtherSymbol = "(@|#|/|%|$|\\||№)";
        regExprPhoneNumber = "((\\+\\d{3})\\((\\d{2})\\)\\d{3}\\-\\d{2}\\-\\d{2})";
        regExprEmail = "(\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4})";
        regExprWord = regExprPhoneNumber + "|" + regExprEmail + "|" + "([.]*[^.[^а-яА-Яa-zA-Z]]+)";
        regExprSentence = "([^.!?]+[" + regExprWord + "]+[.!?])";

        List<TextExpressionObject> textElementArray;
        textElementArray = new ArrayList<TextExpressionObject>();

        textElementArray.add(new TextExpressionObject(regExprSentence, TypeTextExpression.SENTENCE));
        textElementArray.add(new TextExpressionObject(regExprWord, TypeTextExpression.WORD));
        textElementArray.add(new TextExpressionObject(regExprPhoneNumber, TypeTextExpression.PHONE_NUMBER));
        textElementArray.add(new TextExpressionObject(regExprEmail, TypeTextExpression.EMAIL));
        textElementArray.add(new TextExpressionObject(regExprLetter, TypeTextExpression.LETTER));
        textElementArray.add(new TextExpressionObject(regExprNumber, TypeTextExpression.NUMBER));
        textElementArray.add(new TextExpressionObject(regExprPunctuationMark, TypeTextExpression.PUNCTUATION_MARK));
        textElementArray.add(new TextExpressionObject(regExprOtherSymbol, TypeTextExpression.OTHER_SYMBOL));

        init(textElementArray);

        return textElementArray;
    }

    // Проверки связаны в одну цепь. Каждый объект имеет ссылку на следующий объект
    // в List<ElementTextExpression> elementArray
    private void init(List<TextExpressionObject> elementArray) {
        for (int i = 0; i < elementArray.size() - 1; i++) {
            elementArray.get(i).linkWith(elementArray.get(i + 1));
        }

    }
}
