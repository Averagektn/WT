package by.bsuir.exam.dao.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.SaxDAO;
import by.bsuir.exam.dao.exception.DAOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SaxDAOClass implements SaxDAO {
    @Override
    public List<Data> getData() throws DAOException {
        List<Data> dataset = new ArrayList<>();

        // Реализация обработчика событий SAX
        DefaultHandler handler = new DefaultHandler() {
            private StringBuilder currentElementValue;
            private String firstName;
            private String lastName;
            private String middleName;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                currentElementValue = new StringBuilder();
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("firstName")) {
                    firstName = currentElementValue.toString();
                } else if (qName.equalsIgnoreCase("lastName")) {
                    lastName = currentElementValue.toString();
                } else if (qName.equalsIgnoreCase("middleName")) {
                    middleName = currentElementValue.toString();
                } else if (qName.equalsIgnoreCase("person")) {
                    Data data = new Data(firstName, lastName, middleName);
                    dataset.add(data);
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                currentElementValue.append(new String(ch, start, length));
            }
        };

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("sample.xml");
            saxParser.parse(inputStream, handler);
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }

        return dataset;
    }
}