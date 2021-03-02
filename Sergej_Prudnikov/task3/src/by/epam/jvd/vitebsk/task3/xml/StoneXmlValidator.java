package by.epam.jvd.vitebsk.task3.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

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

public class StoneXmlValidator extends DefaultHandler {
    public StoneXmlValidator() {
    }

    public static final String SCHEMA_FILE_NAME = "stones.xsd";

    private StringBuilder error = new StringBuilder();

    private String fileName;

    public StoneXmlValidator(String fileName) {
        this.fileName = fileName;
    }

    private StringBuilder getErrorInfo(SAXParseException e) {
        StringBuilder builder = new StringBuilder();
        builder.append('[').append(e.getLineNumber()).append(':').append(e.getColumnNumber()).append(']');
        builder.append('\n').append(e.getLocalizedMessage()).append('\n');
        return builder;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        error.append("WARNING: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        error.append("ERROR: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        error.append("FATALERROR: ");
        error.append(getErrorInfo(e));
    }

    public String getError() {
        if (error.length() > 0) {
            return error.toString();
        } else {
            return null;
        }
    }

    public boolean validate() throws IOException {
        SchemaFactory factory;
        Schema schema;
        Validator validator;

        factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            schema = factory.newSchema(new File(SCHEMA_FILE_NAME));
            validator = schema.newValidator();
            validator.setErrorHandler(this);
            validator.validate(new StreamSource(fileName));
            return getError() == null;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        }
    }
}
