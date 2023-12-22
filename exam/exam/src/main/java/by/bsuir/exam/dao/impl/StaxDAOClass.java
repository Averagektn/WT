package by.bsuir.exam.dao.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.StaxDAO;
import by.bsuir.exam.dao.exception.DAOException;
import org.w3c.dom.Document;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StaxDAOClass implements StaxDAO {
    @Override
    public List<Data> getData() throws DAOException {
        List<Data> dataset = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("sample.xml");

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            String firstName = null;
            String lastName = null;
            String middleName = null;
            String currentElement = null;

            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();

                        if (currentElement != null) {
                            switch (currentElement) {
                                case "firstName":
                                    firstName = text;
                                    break;
                                case "lastName":
                                    lastName = text;
                                    break;
                                case "middleName":
                                    middleName = text;
                                    break;
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equalsIgnoreCase("person")) {
                            Data data = new Data(firstName, lastName, middleName);
                            dataset.add(data);

                            firstName = null;
                            lastName = null;
                            middleName = null;
                        }
                        currentElement = null;
                        break;
                }
            }

            reader.close();
        } catch (XMLStreamException e) {
            throw new DAOException(e.getMessage());
        }

        return dataset;
    }
}
