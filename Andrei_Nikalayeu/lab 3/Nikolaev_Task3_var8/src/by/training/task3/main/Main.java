package by.training.task3.main;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import by.training.task3.domain.Airplane;
import by.training.task3.printer.ConsolePrinter;
import by.training.task3.printer.Printer;
import by.training.task3.sort.WidthComparator;
import by.training.task3.validation.AirplaneXmlValidator;
import by.training.task3.xml.XMLstaxParser;

public class Main {
    public static void main(String[] args) {    
        String xsdFile = "airplanes.xsd";
        String xmlInput = "airplanes.xml";

        if (AirplaneXmlValidator.validateXMLSchema(xsdFile, xmlInput)) {
            List<Airplane> airplanesList = XMLstaxParser.parseXMLfile(xmlInput);
            Printer printer = new ConsolePrinter();

            for (Airplane airplane : airplanesList) {
                printer.print(airplane);
            }

            printer.print("===Sort airplanes by width===\n");

            Comparator<Airplane> comparator = new WidthComparator();

            Collections.sort(airplanesList, comparator);

            for (Airplane airplane : airplanesList) {
                printer.print(airplane);
            }
        }
    }
}
