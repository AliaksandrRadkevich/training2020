package by.epam.jvd.vitebsk.task2.dao;

import java.util.List;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public abstract class TextExpressionCoR extends TextExpression { // Базовый класс цепочки.
    // CoR - Chain of Responsibility, реализуем цепочку обязанностей

    private TextExpressionCoR next;
    private static List<String> result; // результат поиска совпадений в тексте по регулярному выражению

    public TextExpressionCoR() {
        super();
    }

    public TextExpressionCoR(String criteria, TypeTextExpression typeTextExpression) {
        super(criteria, typeTextExpression);
    }

    // Помогает строить цепь из объектов-проверок.
    public Expression linkWith(TextExpressionCoR next) {
        this.next = next;
        return next;
    }

    private boolean check(String textData) { // проверка совпадение в тексте по регулярному выражению
        System.out.println("check");
        result = interpret(textData);
        if (result == null) {
            return checkNext(textData);
        }
        return true;
    }

    private boolean checkNext(String textData) { // Запускает проверку в следующем объекте или завершает проверку,
        System.out.println("checkNext"); // если мы в последнем элементе цепи.
        if (next == null) {
            return true;
        }
        return next.check(textData);
    }

    public List<String> StartCheck(String textData) {
        result = interpret(textData);
        if (result == null) {
            checkNext(textData);
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((next == null) ? 0 : next.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextExpressionCoR other = (TextExpressionCoR) obj;
        if (next == null) {
            if (other.next != null)
                return false;
        } else if (!next.equals(other.next))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TextExpressionCoR [next=" + next + "]";
    }

}