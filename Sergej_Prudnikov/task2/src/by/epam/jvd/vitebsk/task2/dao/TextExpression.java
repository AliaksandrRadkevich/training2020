package by.epam.jvd.vitebsk.task2.dao;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TextExpression implements Expression {

    private TypeTextExpression typeTextExpression;
    private String criteriaRegExpr;

    TextExpression(String criteria, TypeTextExpression typeTextExpression) {
        this.criteriaRegExpr = criteria;
        this.typeTextExpression = typeTextExpression;
    }

    public TextExpression() {
    }

    @Override
    public List<String> interpret(String textData) { // формирует регулярное выражение и применяет его к textData
        List<String> result; // строки с совпадениями по заданным рег выражениям
        result = new ArrayList<>();
        Matcher matcher = Pattern.compile(criteriaRegExpr).matcher(textData);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public String getCriteriaRegExpr() {
        return criteriaRegExpr;
    }

    public void setCriteria(String criteriaRegExpr) {
        this.criteriaRegExpr = criteriaRegExpr;
    }

    public TypeTextExpression getTypeTextExpression() {
        return typeTextExpression;
    }

    public void setTypeTextExpression(TypeTextExpression typeTextExpression) {
        this.typeTextExpression = typeTextExpression;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((criteriaRegExpr == null) ? 0 : criteriaRegExpr.hashCode());
        result = prime * result + ((typeTextExpression == null) ? 0 : typeTextExpression.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TextExpression other = (TextExpression) obj;
        if (criteriaRegExpr == null) {
            if (other.criteriaRegExpr != null)
                return false;
        } else if (!criteriaRegExpr.equals(other.criteriaRegExpr))
            return false;
        if (typeTextExpression != other.typeTextExpression)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TextExpression [typeTextExpression=" + typeTextExpression + ", criteriaRegExpr=" + criteriaRegExpr
                + "]";
    }

}