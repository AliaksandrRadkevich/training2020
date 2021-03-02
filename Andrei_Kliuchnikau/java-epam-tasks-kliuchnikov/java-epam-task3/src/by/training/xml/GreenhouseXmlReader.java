package by.training.xml;

import by.training.domain.Plant;
import by.training.enums.Multiplying;
import by.training.enums.Soil;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GreenhouseXmlReader {
    public List<Plant> readPlants(String xmlConfig) {
        List<Plant> plantList = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (InputStream inputStream = new FileInputStream(xmlConfig)) {
            XMLEventReader eventReader = inputFactory.createXMLEventReader(inputStream);
            Plant plant = null;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case "plant":
                            plant = new Plant();
                            Iterator<Attribute> attributeIterator = startElement.getAttributes();
                            while (attributeIterator.hasNext()) {
                                Attribute attribute = attributeIterator.next();
                                if (attribute.getName().toString().equals("id")) {
                                    plant.setId(attribute.getValue());
                                }
                            }
                            break;
                        case "name":
                            event = eventReader.nextEvent();
                            plant.setName(event.asCharacters().getData());
                            break;
                        case "soil":
                            event = eventReader.nextEvent();
                            plant.setSoil(Soil.getInstance(event.asCharacters().getData()));
                            break;
                        case "origin":
                            event = eventReader.nextEvent();
                            plant.setOrigin(event.asCharacters().getData());
                            break;
                        case "stem-color":
                            event = eventReader.nextEvent();
                            plant.getVisualParameters().setStemColor(event.asCharacters().getData());
                            break;
                        case "leaf-color":
                            event = eventReader.nextEvent();
                            plant.getVisualParameters().setLeavesColor(event.asCharacters().getData());
                            break;
                        case "average-size":
                            event = eventReader.nextEvent();
                            plant.getVisualParameters().setAverageSize(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "temp":
                            event = eventReader.nextEvent();
                            plant.getGrowingTips().setTemperature(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "lighting":
                            event = eventReader.nextEvent();
                            plant.getGrowingTips().setLighting(Boolean.parseBoolean(event.asCharacters().getData()));
                            break;
                        case "irrigation":
                            event = eventReader.nextEvent();
                            plant.getGrowingTips().setIrrigation(Integer.parseInt(event.asCharacters().getData()));
                            break;
                        case "multiplying":
                            event = eventReader.nextEvent();
                            plant.setMultiplying(Multiplying.getInstance(event.asCharacters().getData()));
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("plant")) {
                        plantList.add(plant);
                    }
                }
            }

        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
        return plantList;
    }
}