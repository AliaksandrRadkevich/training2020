package by.epam.jvd.vitebsk.task2.logic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.jvd.vitebsk.task2.dao.TextExprObjectCreator;
import by.epam.jvd.vitebsk.task2.dao.TextExpressionObject;
import by.epam.jvd.vitebsk.task2.view.View;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class Logic implements Comparator<String> {
    public Logic() {
    }

    public void sortByQuantityWords(List<String> textData) {
        Logic comparator = new Logic();
        Collections.sort(textData, comparator);
    }

    public String textProcessing(String textData) { // замена табуляции и последовательности пробелов одним пробелом
        return textData.replaceAll("((\\s{2,})|\\t|\\n)", " ");
    }

    public String toStringMy(List<String> textData) {
        String allText;
        allText = "";
        for (int i = 0; i < textData.size() - 1; i++) {
            allText = allText + textData.get(i);
        }
        return allText;
    }

    public void demonstrationTextElements(List<TextExpressionObject> textElementArray,
            TextExprObjectCreator textElementCreator, View view, String textData) {
        textElementArray.forEach(elementArray -> {
            view.showMessage("__________Presentation of objects type - " + elementArray.getTypeTextExpression() + ":");
            List<String> resultFind = elementArray.StartCheck(textData);
            view.show(resultFind);
        });
    }

    @Override
    public int compare(String o1, String o2) {
        String regExprWord, regExprEmail, regExprPhoneNumber; // отдельные цифры и символы не учитываются в сортировке
                                                              // по количеству слов в предложении
        int count1, count2;
        regExprPhoneNumber = "((\\+\\d{3})\\((\\d{2})\\)\\d{3}\\-\\d{2}\\-\\d{2})";
        regExprEmail = "(\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4})";
        regExprWord = regExprPhoneNumber + "|" + regExprEmail + "|" + "([.]*[^.[^а-яА-Яa-zA-Z]]+)";
        count1 = 0;
        count2 = 0;
        Pattern pattern = Pattern.compile(regExprWord);
        Matcher matcher1 = pattern.matcher(o1);
        Matcher matcher2 = pattern.matcher(o2);
        while (matcher1.find()) {
            count1++;
        }
        while (matcher2.find()) {
            count2++;
        }
        if (count1 > count2) {
            return 1;
        } else if (count1 < count2) {
            return -1;
        } else {
            return 0;
        }
    }
}
