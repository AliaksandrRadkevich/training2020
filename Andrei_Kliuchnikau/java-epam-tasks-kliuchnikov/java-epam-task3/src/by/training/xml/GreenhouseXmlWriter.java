package by.training.xml;

import by.training.domain.Plant;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GreenhouseXmlWriter {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public void setPlants(List<Plant> plants) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try (OutputStream outputStream = new FileOutputStream(path)) {
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputStream);
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD(System.lineSeparator());
            StartDocument startDocument = eventFactory.createStartDocument("UTF-8");
            eventWriter.add(startDocument);
            eventWriter.add(end);
            eventWriter.add(eventFactory.createStartElement("", "", "greenhouse"));
            eventWriter.add(eventFactory.createAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"));
            eventWriter.add(eventFactory.createAttribute("xmlns", "https://training.by/greenhouse/namespaces"));
            eventWriter.add(eventFactory.createAttribute("xsi:schemaLocation", "https://training.by/greenhouse/namespaces greenhouse.xsd"));
            eventWriter.add(end);
            for (Plant plant : plants) {
                eventWriter.add(eventFactory.createStartElement("", "", "plant"));
                eventWriter.add(eventFactory.createAttribute("id", plant.getId()));
                eventWriter.add(end);
                createNode(eventWriter, "name", plant.getName());
                createNode(eventWriter, "soil", plant.getSoil().getSoilType());
                createNode(eventWriter, "origin", plant.getOrigin());
                eventWriter.add(eventFactory.createStartElement("", "", "visual-parameters"));
                eventWriter.add(end);
                createNode(eventWriter, "stem-color", plant.getVisualParameters().getStemColor());
                createNode(eventWriter, "leaf-color", plant.getVisualParameters().getLeavesColor());
                createNode(eventWriter, "average-size", String.valueOf(plant.getVisualParameters().getAverageSize()));
                eventWriter.add(eventFactory.createEndElement("", "", "visual-parameters"));
                eventWriter.add(end);
                eventWriter.add(eventFactory.createStartElement("", "", "growing-tips"));
                eventWriter.add(end);
                createNode(eventWriter, "temp", String.valueOf(plant.getGrowingTips().getTemperature()));
                createNode(eventWriter, "lighting", String.valueOf(plant.getGrowingTips().isLighting()));
                createNode(eventWriter, "irrigation", String.valueOf(plant.getGrowingTips().getIrrigation()));
                eventWriter.add(eventFactory.createEndElement("", "", "growing-tips"));
                eventWriter.add(end);
                createNode(eventWriter, "multiplying", plant.getMultiplying().getMultiplyingType());
                eventWriter.add(eventFactory.createEndElement("", "", "plant"));
                eventWriter.add(end);
            }
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value) {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD(System.lineSeparator());
        XMLEvent tab = eventFactory.createDTD("\t");
        StartElement sElement = eventFactory.createStartElement("", "", name);
        try {
            eventWriter.add(tab);
            eventWriter.add(sElement);
            Characters characters = eventFactory.createCharacters(value);
            eventWriter.add(characters);
            EndElement eElement = eventFactory.createEndElement("", "", name);
            eventWriter.add(eElement);
            eventWriter.add(end);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}