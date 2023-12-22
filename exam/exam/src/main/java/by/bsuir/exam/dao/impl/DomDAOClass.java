package by.bsuir.exam.dao.impl;

import by.bsuir.exam.bean.Data;
import by.bsuir.exam.dao.DomDAO;
import by.bsuir.exam.dao.exception.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomDAOClass implements DomDAO {
    @Override
    public List<Data> getData() throws DAOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("sample.xml");
            Document document = builder.parse(inputStream);

            List<Data> dataset = new ArrayList<>();

            NodeList personNodes = document.getElementsByTagName("person");

            for (int i = 0; i < personNodes.getLength(); i++) {
                Element personElement = (Element) personNodes.item(i);
                String firstName = personElement.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = personElement.getElementsByTagName("lastName").item(0).getTextContent();
                String middleName = personElement.getElementsByTagName("middleName").item(0).getTextContent();

                Data data = new Data(firstName, lastName, middleName);
                dataset.add(data);
            }

            return dataset;
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
    }
}
