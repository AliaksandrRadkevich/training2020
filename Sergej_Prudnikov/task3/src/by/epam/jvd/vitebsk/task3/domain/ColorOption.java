package by.epam.jvd.vitebsk.task3.domain;

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

public enum ColorOption {
    GREEN, RED, YELLOW, BLUE, BLACK, WHITE;
}
