package by.epam.jvd.vitebsk.task3.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.jvd.vitebsk.task3.domain.Color;
import by.epam.jvd.vitebsk.task3.domain.Faceting;
import by.epam.jvd.vitebsk.task3.domain.PreciousnessType;
import by.epam.jvd.vitebsk.task3.domain.Stone;
import by.epam.jvd.vitebsk.task3.domain.Transparency;

public class StoneXmlReader {
    public StoneXmlReader() {
    }

    private Stone stone;
    private List<Stone> stones;

    public List<Stone> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader;
        XMLInputFactory factory;
        int type;

        reader = null;
        stones = new ArrayList<Stone>();
        stone = null;
        factory = XMLInputFactory.newFactory();

        try {
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                type = reader.next();
                formListStones(reader, type);
            }
            return stones;

        } catch (XMLStreamException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void formListStones(XMLStreamReader reader, int type) throws XMLStreamException { // поменять название
        String tagName;

        switch (type) {
        case XMLStreamConstants.START_ELEMENT: {
            tagName = reader.getLocalName();
            createStone(reader, tagName);
            break;
        }
        case XMLStreamConstants.END_ELEMENT: {
            tagName = reader.getLocalName();
            if ("stone".equals(tagName)) {
                stones.add(stone);
            }
            break;
        }
        }

    }

    private void createStone(XMLStreamReader reader, String tagName) throws XMLStreamException {
        Color color;
        Transparency transparency;
        Faceting faceting;

        switch (tagName) {
        case "stone": {
            stone = new Stone();
            stone.setId(reader.getAttributeValue(null, "id"));
            break;
        }
        case "name": {
            stone.setName(reader.getElementText());
            break;
        }
        case "preciousness": {
            stone.setPreciousnessType(PreciousnessType.valueOf(reader.getElementText()));
            break;
        }
        case "origin": {
            stone.setOrigin(reader.getElementText());
            break;
        }
        case "value": {
            stone.setValue(reader.getElementText());
            break;
        }
        case "color": {
            color = new Color();
            color.setElement(tagName);
            color.setValue(reader.getElementText());
            stone.getParameters().add(color);
            break;
        }
        case "transparency": {
            transparency = new Transparency();
            transparency.setElement(tagName);
            transparency.setValue(reader.getElementText());
            stone.getParameters().add(transparency);
            break;
        }
        case "faceting": {
            faceting = new Faceting();
            faceting.setElement(tagName);
            faceting.setValue(reader.getElementText());
            stone.getParameters().add(faceting);
            break;
        }
        }
    }
}