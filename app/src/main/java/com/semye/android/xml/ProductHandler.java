package com.semye.android.xml;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProductHandler extends DefaultHandler {

    @Nullable
    private List<HashMap<String, String>> list = null;
    @Nullable
    private HashMap<String, String> hm = null;
    @Nullable
    private String s = null;

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        s = new String(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        list = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, @NonNull String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("product")) {
            hm = new HashMap<>();
        }
    }

    @Override
    public void endElement(String uri, @NonNull String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (hm != null && list != null) {
            if (localName.equals("id")) {
                hm.put("id", s);
            }
            if (localName.equals("name")) {
                hm.put("name", s);
            }
            if (localName.equals("price")) {
                hm.put("price", s);
            }
            if (localName.equals("product")) {
                list.add(hm);
            }
        }
    }

    @Nullable
    public List<HashMap<String, String>> getProduct() {
        return list;
    }


}
