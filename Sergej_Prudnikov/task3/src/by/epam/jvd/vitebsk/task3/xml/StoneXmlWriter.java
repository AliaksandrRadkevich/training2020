package by.epam.jvd.vitebsk.task3.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import by.epam.jvd.vitebsk.task3.domain.Stone;
import by.epam.jvd.vitebsk.task3.domain.VisualParameter;

public class StoneXmlWriter {
    public StoneXmlWriter() {
    }

    public void write(List<Stone> stones, String fileName) throws FileNotFoundException, XMLStreamException {
        XMLStreamWriter writer;
        XMLOutputFactory factory;
        writer = null;

        try {
            factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("stones");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.example.org/stones");
            writer.writeAttribute("xsi:schemaLocation", "http://www.example.org/stones stones.xsd");
            writeInfoStones(writer, stones);
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void writeInfoStones(XMLStreamWriter writer, List<Stone> stones) throws XMLStreamException {
        for (Stone stone : stones) {
            writer.writeStartElement("stone");
            writer.writeAttribute("id", stone.getId());
            writer.writeStartElement("name");
            writer.writeCData(stone.getName());
            writer.writeEndElement();
            writer.writeStartElement("preciousness");
            writer.writeCharacters(stone.getPreciousnessType().toString());
            writer.writeEndElement();
            writer.writeStartElement("origin");
            writer.writeCharacters(stone.getOrigin());
            writer.writeEndElement();
            writer.writeStartElement("value");
            writer.writeCharacters(stone.getValue());
            writer.writeEndElement();
            writer.writeStartElement("visual-parameters");
            for (VisualParameter parameter : stone.getParameters()) {
                writer.writeStartElement(parameter.getElement());
                writer.writeCharacters(parameter.getValue());
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndElement();
        }
    }
}
