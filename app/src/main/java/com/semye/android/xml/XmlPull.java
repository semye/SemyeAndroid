package com.semye.android.xml;

import android.util.Xml;

import androidx.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class XmlPull {

    @NonNull
    public static List<HashMap<String, String>> parseXML(InputStream is)
            throws Exception {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        XmlPullParser parser = Xml.newPullParser();
        HashMap<String, String> map = new HashMap<String, String>();
        parser.setInput(is, "UTF-8");
        int eventCode = parser.getEventType();
        while (eventCode != XmlPullParser.END_DOCUMENT) {
            switch (eventCode) {
                case XmlPullParser.START_DOCUMENT:

                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("product")) {

                    } else if (parser.getName().equals("id")) {
                        map.put("id", parser.nextText());
                    } else if (parser.getName().equals("name")) {
                        map.put("name", parser.nextText());
                    } else if (parser.getName().equals("price")) {
                        map.put("price", parser.nextText());
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("product")) {
                        list.add(map);
                    }
                    break;

                default:
                    break;
            }
            eventCode = parser.next();
        }

        return list;
    }

}
