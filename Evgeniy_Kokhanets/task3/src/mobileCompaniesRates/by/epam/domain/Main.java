package mobileCompaniesRates.by.epam.domain;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import org.xml.sax.helpers.DefaultHandler;
import mobileCompaniesRates.by.epam.rates.Rate;

public class Main extends DefaultHandler {

    public static void main(String[] args) throws XMLStreamException, IOException {
        RatesXMLValidator rxv = new RatesXMLValidator(".\\src\\mobileCompaniesRates\\by\\epam\\xml\\rates.xml");
        if (rxv.validate()) {
            RatesXMLReader rxr = new RatesXMLReader();
            List<Rate> rates = rxr.read(".\\src\\mobileCompaniesRates\\by\\epam\\xml\\rates.xml");
            Collections.sort(rates, new Comparator<Rate>() {
                @Override
                public int compare(Rate o1, Rate o2) {
                    return o2.getPayroll().compareTo(o1.getPayroll());
                }
            });
            for (Rate rate : rates) {
                System.out.println(rate);
            }
            RatesXMLWriter wr = new RatesXMLWriter();
            wr.write(rates, ".\\src\\mobileCompaniesRates\\by\\epam\\xml\\ratesDuplicate.xml");
            System.out.println(rxv.validate());
        } else {
            System.out.println(rxv.validate());
        }
    }
}
