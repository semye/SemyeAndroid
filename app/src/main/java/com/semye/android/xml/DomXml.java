package com.semye.android.xml;


import androidx.annotation.NonNull;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DomXml {
    private Document doc;

    public DomXml(InputStream is) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (@NonNull ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public List<HashMap<String, String>> getProducts() {
        Element root = doc.getDocumentElement();
        NodeList lstNodes = root.getElementsByTagName("product");
        List<HashMap<String, String>> products = new ArrayList<>();
        for (int i = 0; i < lstNodes.getLength(); i++) {
            HashMap<String, String> map = new HashMap<>();
            Element element = (Element) lstNodes.item(i);
            Element elemId = (Element) element.getElementsByTagName("id").item(0);
            map.put("id", elemId.getTextContent());
            Element elemName = (Element) element.getElementsByTagName("name").item(0);
            map.put("name", elemName.getTextContent());
            Element elemPrice = (Element) element.getElementsByTagName("price").item(0);
            map.put("price", elemPrice.getTextContent());
            products.add(map);
        }
        return products;
    }

}
