package mobileCompaniesRates.by.epam.domain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import mobileCompaniesRates.by.epam.rates.Rate;
import mobileCompaniesRates.by.epam.rates.callPrices.InNetwork;
import mobileCompaniesRates.by.epam.rates.callPrices.Landline;
import mobileCompaniesRates.by.epam.rates.callPrices.OutNetwork;
import mobileCompaniesRates.by.epam.rates.params.ConnectionFees;
import mobileCompaniesRates.by.epam.rates.params.FavoriteNumber;
import mobileCompaniesRates.by.epam.rates.params.Tariffication;

public class RatesXMLReader {
    public List<Rate> read(String fileName) throws FileNotFoundException{
        XMLStreamReader reader = null;
        try {
            List<Rate> rates = new ArrayList<Rate>();
            Rate rate = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        switch(tagName) {
                            case "rate":
                                rate = new Rate();
                                rate.setId(reader.getAttributeValue(null, "id"));
                                break;
                            case "name":
                                rate.setName(reader.getElementText());
                                break;
                            case "operatorName":
                                rate.setOperatorName(reader.getElementText());
                                break;
                            case "payroll":
                                rate.setPayroll(Double.parseDouble(reader.getElementText()));
                                break;
                            case "inNetwork":
                                InNetwork inNetwork = new InNetwork();
                                inNetwork.setElement(tagName);
                                inNetwork.setValue(reader.getElementText());
                                rate.getCallPrices().add(inNetwork);
                                break;
                            case "outNetwork":
                                OutNetwork outNetwork = new OutNetwork();
                                outNetwork.setElement(tagName);
                                outNetwork.setValue(reader.getElementText());
                                rate.getCallPrices().add(outNetwork);
                                break;
                            case "landline":
                                Landline landline = new Landline();
                                landline.setElement(tagName);
                                landline.setValue(reader.getElementText());
                                rate.getCallPrices().add(landline);
                                break;
                            case "smsPrice":
                                rate.setSmsPrice(Double.parseDouble(reader.getElementText()));
                                break;
                            case "favoriteNumber":
                                FavoriteNumber favoriteNumber = new FavoriteNumber();
                                favoriteNumber.setElement(tagName);
                                favoriteNumber.setValue(reader.getElementText());
                                rate.getParams().add(favoriteNumber);
                                break;
                            case "tariffication":
                                Tariffication tariffication = new Tariffication();
                                tariffication.setElement(tagName);
                                tariffication.setValue(reader.getElementText());
                                rate.getParams().add(tariffication);
                                break;
                            case "connectionFees":
                                ConnectionFees connectionFees = new ConnectionFees();
                                connectionFees.setElement(tagName);
                                connectionFees.setValue(reader.getElementText());
                                rate.getParams().add(connectionFees);
                                break;
                            }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("rate".equals(tagName)) {
                            rates.add(rate);
                        }
                        break;
                    }
                }
            }
            return rates;
        } catch (XMLStreamException e) {
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
            }
        }
    }
}
