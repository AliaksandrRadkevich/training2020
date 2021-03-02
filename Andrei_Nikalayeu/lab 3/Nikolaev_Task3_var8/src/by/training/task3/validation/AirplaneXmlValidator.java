package by.training.task3.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import by.training.task3.printer.ConsolePrinter;
import by.training.task3.printer.Printer;

public class AirplaneXmlValidator {
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            Printer printer = new ConsolePrinter();
            printer.print("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
