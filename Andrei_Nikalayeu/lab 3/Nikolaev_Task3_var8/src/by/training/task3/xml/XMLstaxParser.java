package by.training.task3.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import by.training.task3.domain.Airplane;
import by.training.task3.domain.AirplaneType;
import by.training.task3.domain.Gun;
import by.training.task3.domain.Radar;
import by.training.task3.domain.RocketAirToAir;
import by.training.task3.domain.RocketAirToGrnd;

public class XMLstaxParser {
    public static List<Airplane> parseXMLfile(String fileName) {
        List<Airplane> airplanesList = new ArrayList<>();
        Airplane airplane = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals("airplane")) {
                        airplane = new Airplane();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));

                        if (idAttr != null) {
                            airplane.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("model")) {
                        xmlEvent = reader.nextEvent();
                        airplane.setModel(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("origin")) {
                        xmlEvent = reader.nextEvent();
                        airplane.setOrigin(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("type")) {
                        xmlEvent = reader.nextEvent();
                        airplane.setAirplaneType(AirplaneType.valueOf(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("seats")) {
                        xmlEvent = reader.nextEvent();
                        airplane.setSeats(Byte.parseByte(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("gun")) {
                        xmlEvent = reader.nextEvent();
                        Attribute caliberAttr = startElement.getAttributeByName(new QName("caliber"));
                        Gun gun = null;

                        if (caliberAttr != null) {
                            gun = new Gun(Integer.parseInt(caliberAttr.getValue()));
                            airplane.setGun(gun);
                        }
                    } else if (startElement.getName().getLocalPart().equals("airToAir")) {
                        xmlEvent = reader.nextEvent();
                        Attribute amountAttr = startElement.getAttributeByName(new QName("amount"));
                        Attribute rangetAttr = startElement.getAttributeByName(new QName("range"));
                        RocketAirToAir rocketAirToAir = null;

                        if (amountAttr != null) {
                            rocketAirToAir = new RocketAirToAir(Byte.parseByte(amountAttr.getValue()),
                                                                Integer.parseInt(rangetAttr.getValue()));
                            airplane.setRocketAirToAir(rocketAirToAir);
                        }
                    } else if (startElement.getName().getLocalPart().equals("airToGrnd")) {
                        xmlEvent = reader.nextEvent();
                        Attribute amountAttr = startElement.getAttributeByName(new QName("amount"));
                        Attribute heightAttr = startElement.getAttributeByName(new QName("height"));
                        RocketAirToGrnd rocketAirToGrnd = null;

                        if (amountAttr != null) {
                            rocketAirToGrnd = new RocketAirToGrnd(Byte.parseByte(amountAttr.getValue()),
                                                                  Integer.parseInt(heightAttr.getValue()));
                            airplane.setRocketAirToGrnd(rocketAirToGrnd);
                        }
                    } else if (startElement.getName().getLocalPart().equals("radar")) {
                        xmlEvent = reader.nextEvent();
                        Attribute nameAttr = startElement.getAttributeByName(new QName("name"));
                        Radar radar = null;

                        if (nameAttr != null) {
                            radar = new Radar(nameAttr.getValue());
                            airplane.setRadar(radar);
                        }
                    } else if (startElement.getName().getLocalPart().equals("parameters")) {
                        xmlEvent = reader.nextEvent();
                        Attribute lengthAttr = startElement.getAttributeByName(new QName("length"));
                        Attribute widthAttr = startElement.getAttributeByName(new QName("width"));
                        Attribute heightAttr = startElement.getAttributeByName(new QName("height"));

                        if (lengthAttr != null) {
                            airplane.setLength(Double.parseDouble(lengthAttr.getValue()));
                        }
                        if (widthAttr != null) {
                            airplane.setWidth(Double.parseDouble(widthAttr.getValue()));
                        }
                        if (heightAttr != null) {
                            airplane.setHeight(Double.parseDouble(heightAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("price")) {
                        xmlEvent = reader.nextEvent();
                        airplane.setPrice(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } 
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("airplane")) {
                        airplanesList.add(airplane);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
        return airplanesList;
    }
}
