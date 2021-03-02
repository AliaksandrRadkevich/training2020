package by.epam.jvd.vitebsk.task2.main;

import java.util.List;
import by.epam.jvd.vitebsk.task2.dao.TextExpressionObject;
import by.epam.jvd.vitebsk.task2.dao.TextExprObjectCreator;
import by.epam.jvd.vitebsk.task2.logic.Logic;
import by.epam.jvd.vitebsk.task2.reader.TextReader;
import by.epam.jvd.vitebsk.task2.view.View;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class Main {

    public static void main(String[] args) {
        View view;
        Logic logic;
        TextReader textReader;
        TextExprObjectCreator textExprObjectCreator;
        List<TextExpressionObject> textExpressionObjectArray;
        String textData, pathToFile;

        textExprObjectCreator = new TextExprObjectCreator();
        logic = new Logic();
        view = new View();
        pathToFile = "resource/text.txt";

        textReader = new TextReader(pathToFile);
        view.showMessage("1__________Source text_________");
        textData = textReader.readReturnString();
        view.show(textData);

        view.showMessage("1__________Replacing tabs and a sequence of spaces with a single space_________");
        textData = logic.textProcessing(textData);
        view.show(textData);

        textExpressionObjectArray = textExprObjectCreator.create(); // формирование объектов для представления: символа,
                                                                    // слова,
        // предложения, знака препинания и др.
        view.showMessage("2__________Presentation of objects type - SENTENCE_________");
        List<String> resultFind = textExpressionObjectArray.get(0).StartCheck(textData);
        view.show(resultFind);

        view.showMessage(
                "3__________Print all sentences of the specified text in ascending order of the number of words in each of them._________");
        logic.sortByQuantityWords(resultFind);
        view.show(resultFind);

        logic.demonstrationTextElements(textExpressionObjectArray, textExprObjectCreator, view, textData);
    }
}
