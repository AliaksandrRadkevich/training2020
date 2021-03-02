package by.training.xml;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.security.sasl.SaslException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class GreenhouseXmlValidator extends DefaultHandler {
    private String pathXml;
    private StringBuilder error = new StringBuilder();

    public void setPathXml(String pathXsd) {
        this.pathXml = pathXsd;
    }

    private StringBuilder getErrorInfo(SAXParseException e) {
        StringBuilder builder = new StringBuilder();
        builder.append('[').append(e.getLineNumber()).append(':').append(e.getColumnNumber()).append(']');
        builder.append(System.lineSeparator()).append(e.getLocalizedMessage()).append('\n');
        return builder;
    }

    @Override
    public void warning(SAXParseException e) {
        error.append("WARNING: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void error(SAXParseException e) {
        error.append("ERROR: ");
        error.append(getErrorInfo(e));
    }

    @Override
    public void fatalError(SAXParseException e) {
        error.append("FATALERROR: ");
        error.append(getErrorInfo(e));
    }

    public String getError() {
        return error.length() > 0 ? error.toString() : null;
    }

    public boolean validate() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try (InputStream inputStream = new FileInputStream(pathXml)) {
            Schema schema = schemaFactory.newSchema(new File("data/greenhouse.xsd"));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(this);
            validator.validate(new StreamSource(inputStream));
            return getError() == null;
        }
        catch (SAXException | IOException e) {
            return false;
        }
    }
}