package by.epam.jvd.vitebsk.task3.logic;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import by.epam.jvd.vitebsk.task3.domain.Stone;
import by.epam.jvd.vitebsk.task3.domain.StoneNameComparator;
import by.epam.jvd.vitebsk.task3.domain.StoneValueComparator;

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

public class Logic {
    public Logic() {
    }

    private static StoneValueComparator stoneValueComparator;
    private static StoneNameComparator stoneNameComparator;

    public void sortListStonesbyValue(List<Stone> stones) {
        if (stoneValueComparator == null) {
            stoneValueComparator = new StoneValueComparator();
            Collections.sort(stones, stoneValueComparator);
        } else {
            Collections.sort(stones, stoneValueComparator);
        }
    }

    public void sortListStonesbyName(List<Stone> stones) {
        if (stoneNameComparator == null) {
            stoneNameComparator = new StoneNameComparator();
            Collections.sort(stones, stoneNameComparator);
        } else {
            Collections.sort(stones, stoneNameComparator);
        }
    }

    public void changeValueofStonesRandomly(List<Stone> stones) {
        Random random;
        Integer maxValue;
        random = new Random();
        maxValue = 2000;
        stones.forEach(stone -> {
            stone.setValueInteger(random.nextInt(maxValue));
        });
    }
}
