package by.training;

import by.training.console.GreenhouseInfo;
import by.training.domain.Greenhouse;
import by.training.domain.Plant;
import by.training.xml.GreenhouseXmlReader;
import by.training.xml.GreenhouseXmlValidator;
import by.training.xml.GreenhouseXmlWriter;

import java.util.Collections;
import java.util.Comparator;

public class Runner {
    public static void main(String[] args) {
        GreenhouseXmlValidator validator = new GreenhouseXmlValidator();
        validator.setPathXml("data/greenhouse.xml");
        if (validator.validate()) {
            GreenhouseXmlReader reader = new GreenhouseXmlReader();
            Greenhouse greenhouse = new Greenhouse(reader.readPlants("data/greenhouse.xml"));
            GreenhouseInfo.printFullInfo(greenhouse);
            GreenhouseXmlWriter writer = new GreenhouseXmlWriter();
            writer.setPath("data/greenhouseWrite.xml");
            writer.setPlants(greenhouse.getPlantList());
            System.out.println("Success! Sort list by name...");
            Collections.sort(greenhouse.getPlantList(), new Comparator<Plant>() {
                @Override
                public int compare(Plant o1, Plant o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
            GreenhouseInfo.printFullInfo(greenhouse);
        } else {
            System.out.println(validator.getError());
        }
    }
}