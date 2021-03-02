package by.epam.jvd.vitebsk.task3.view;

import java.util.List;

import by.epam.jvd.vitebsk.task3.domain.Stone;

/*Разработать для своего варианта структуру XML документа, описать её с помощью XSD. Создать файл XML, соответствующий 
разработанной XSD схеме. При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и 
предельные значения, обязательно использование атрибутов и типа ID. Создать Java-класс(классы), соответствующие 
разработанной схеме. Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией 
из XML-файла. Для разбора использовать SAX, DOM или StAX парсер. Для сортировки объектов использовать интерфейс 
Comparator. Произвести проверку корректности и валидности XML-документа с привлечением XSD.

Алмазный фонд.
Драгоценные и полудрагоценные камни, содержащиеся в павильоне, имеют следующие характеристики:
Name – название камня.
PreciousnessType – может быть драгоценным или полудрагоценным.
Origin – место добывания.
Visual parameters (должно быть несколько) – могут быть: цвет (зеленый, красный, желтый и т.д.), прозрачность 
(измеряется в процентах 0-100%), способы огранки (количество граней 4-15).
Value – вес камня (измеряется в каратах).*/

public class View {
    public View() {
    }

    public void showListStones(List<Stone> stones) {
        stones.forEach(stone -> {
            showStone(stone);
        });
    }

    public void showStone(Stone stone) {
        StringBuilder builder = new StringBuilder();
        builder.append("ID: ").append(stone.getId()).append('\n');
        builder.append("Name: ").append(stone.getName()).append('\n');
        builder.append("PreciousnessType: ").append(stone.getPreciousnessType().toString()).append('\n');
        builder.append("Origin: ").append(stone.getOrigin()).append('\n');
        builder.append("Value: ").append(stone.getValue()).append("(carat weight of the stone)").append('\n');
        builder.append("Visual parameters:\n");
        stone.getParameters().forEach(parameter -> {
            builder.append('\t').append(parameter.getName()).append(": ").append(parameter.getValue()).append('\n');
        });
        System.out.println(builder);
    }

    public void showMesssage(String messsage) {
        System.out.println(messsage);
    }
}
