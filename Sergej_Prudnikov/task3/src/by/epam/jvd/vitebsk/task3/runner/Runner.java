package by.epam.jvd.vitebsk.task3.runner;

import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLStreamException;
import by.epam.jvd.vitebsk.task3.domain.Stone;
import by.epam.jvd.vitebsk.task3.logic.Logic;
import by.epam.jvd.vitebsk.task3.view.View;
import by.epam.jvd.vitebsk.task3.xml.StoneXmlReader;
import by.epam.jvd.vitebsk.task3.xml.StoneXmlValidator;
import by.epam.jvd.vitebsk.task3.xml.StoneXmlWriter;

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

public class Runner { // TODO перенести грамотно классы по пакетам согласно ООП
    public static void main(String[] args) {

        Logic logic;
        View view;
        StoneXmlValidator validator;
        StoneXmlReader reader;
        StoneXmlWriter writer;
        List<Stone> stones;

        logic = new Logic();
        view = new View();
        validator = new StoneXmlValidator("stones.xml");

        try {
            if (validator.validate()) {
                reader = new StoneXmlReader();
                stones = reader.read("stones.xml");

                view.showMesssage("____1. List of stones from the base XML-file:_____");
                view.showListStones(stones);

                view.showMesssage("____2. List of stones sorted by value (carat weight):____");
                logic.sortListStonesbyValue(stones);
                view.showListStones(stones);

                view.showMesssage("____3. List of stones sorted by name:____");
                logic.sortListStonesbyName(stones);
                view.showListStones(stones);

                writer = new StoneXmlWriter();
                writer.write(stones, "stones-new.xml");
                view.showMesssage("____4. New XML-file (stones-new.xml) created.____");
            } else {
                System.out.println(validator.getError());
            }
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
