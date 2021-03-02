package mobileCompaniesRates.by.epam.domain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import mobileCompaniesRates.by.epam.rates.Rate;
import mobileCompaniesRates.by.epam.rates.callPrices.CallPrices;
import mobileCompaniesRates.by.epam.rates.params.Params;

public class RatesXMLWriter {

    public void write(List<Rate> rates, String fileName) throws FileNotFoundException, XMLStreamException {
        XMLStreamWriter writer = null;
        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("rates");
            writer.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns", "http://www.epam.by/rates");
            writer.writeAttribute("xsi:schemaLocation", "http://www.epam.by/rates rates.xsd");
            for (Rate rate : rates) {
                writer.writeStartElement("rate");
                writer.writeAttribute("id", rate.getId());
                writer.writeStartElement("name");
                writer.writeCharacters(rate.getName());
                writer.writeEndElement();
                writer.writeStartElement("operatorName");
                writer.writeCharacters(rate.getOperatorName());
                writer.writeEndElement();
                writer.writeStartElement("payroll");
                writer.writeCharacters(rate.getPayroll().toString());
                writer.writeEndElement();
                if(!rate.getCallPrices().isEmpty()) {
                    writer.writeStartElement("callprices");
                    for (CallPrices prices : rate.getCallPrices()) {
                    writer.writeStartElement(prices.getElement());
                    writer.writeCharacters(prices.getValue());
                    writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }
                writer.writeStartElement("smsPrice");
                writer.writeCharacters(rate.getSmsPrice().toString());
                writer.writeEndElement();
                if(!rate.getParams().isEmpty()) {
                    writer.writeStartElement("parameters");
                    for (Params params : rate.getParams()) {
                        writer.writeStartElement(params.getElement());
                        writer.writeCharacters(params.getValue());
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
