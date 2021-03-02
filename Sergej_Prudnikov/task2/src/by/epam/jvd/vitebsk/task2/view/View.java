package by.epam.jvd.vitebsk.task2.view;

import java.util.List;

//Создать программу обработки текста учебника по программированию с использованием классов (по необходимости) 
//для представления: символа, слова, предложения, знака препинания и др. Во всех задачах с формированием текста 
//заменять табуляции и последовательности пробелов одним пробелом. Программа должна обрабатывать адреса электронной
//почты и номера телефонов в формате +XXX(XX)XXX-XX-XX как отдельные слова.
//Вариант 2
//Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.

public class View {

    public View() {
    }

    public void show(List<String> textData) {
        textData.forEach(data -> {
            System.out.println(data);
        });
        System.out.println();
    }

    public void show(String textData) {
        System.out.println(textData);
        System.out.println();
    }

    public void showMessage(String textData) {
        System.out.println(textData);
    }
}
