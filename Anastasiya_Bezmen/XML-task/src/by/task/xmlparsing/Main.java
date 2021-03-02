package by.task.xmlparsing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main {

    public static void main(String[] args) {
        ArrayList<ComputerAccessory> computerAccessories = new ArrayList<>();
        String filepath = "computer_accessories.xml";
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList computerAccessoryList = document.getDocumentElement().getElementsByTagName("computer_accessory");
            for (int i = 0; i < computerAccessoryList.getLength(); i++) {
                Node computerAccessoryNode = computerAccessoryList.item(i);

                ComputerAccessory computerAccessory = buildComputerAccessory(computerAccessoryNode);
                computerAccessories.add(computerAccessory);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        sortAndPrintComputerAccessories(computerAccessories);
    }

    private static ComputerAccessory buildComputerAccessory(Node computerAccessoryNode) {
        ComputerAccessory computerAccessory = new ComputerAccessory();
        NodeList childNodes = computerAccessoryNode.getChildNodes();
        computerAccessory.setId(childNodes.item(1).getTextContent());
        computerAccessory.setName(childNodes.item(3).getTextContent());
        computerAccessory.setOrigin(childNodes.item(5).getTextContent());
        computerAccessory.setPrice(Integer.valueOf(childNodes.item(7).getTextContent()));
        computerAccessory.setType(buildAccessory(childNodes.item(9)));
        computerAccessory.setCritical(Boolean.valueOf(childNodes.item(11).getTextContent()));
        return computerAccessory;
    }

    private static Accessory buildAccessory(Node item) {
        Accessory accessory = new Accessory();
        NodeList childNodes = item.getChildNodes();
        accessory.setPeripheral(Boolean.valueOf(childNodes.item(1).getTextContent()));
        accessory.setEnergyConsumption(Integer.valueOf(childNodes.item(3).getTextContent()));
        accessory.setAvailabilityCooler(Boolean.valueOf(childNodes.item(5).getTextContent()));
        accessory.setType(Type.findByName(childNodes.item(7).getTextContent()));
        accessory.setPort(Port.findByName(childNodes.item(9).getTextContent()));
        return accessory;
    }

    private static void sortAndPrintComputerAccessories(ArrayList<ComputerAccessory> computerAccessories) {
        computerAccessories.sort(new Comparator<ComputerAccessory>() {

            @Override
            public int compare(ComputerAccessory o1, ComputerAccessory o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (ComputerAccessory computerAccessory : computerAccessories) {
            System.out.println(computerAccessory);
        }
    }
}