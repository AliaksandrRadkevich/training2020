package by.epam.jvd.vitebsk.task2.dao;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class TextExpressionObject extends TextExpressionCoR {

    public TextExpressionObject() {
        super();
    }

    public TextExpressionObject(String criteria, TypeTextExpression typeTextExpression) {
        super(criteria, typeTextExpression);
    }
}
