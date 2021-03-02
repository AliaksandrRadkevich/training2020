package mobileCompaniesRates.by.epam.domain;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class RatesXMLValidator {
    public static final String SCHEMA_FILE_NAME = ".\\src\\mobileCompaniesRates\\by\\epam\\xml\\rates.xsd";

    private String fileName;

    public RatesXMLValidator(String fileName) {
        this.fileName = fileName;
    }

    public boolean validate() throws IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(SCHEMA_FILE_NAME));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileName));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }
}
