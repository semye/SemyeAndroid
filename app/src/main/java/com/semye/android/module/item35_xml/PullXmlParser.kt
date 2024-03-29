package com.semye.android.module.item35_xml

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.util.*

object PullXmlParser {

    @JvmStatic
    fun parseXML(inputStream: InputStream): List<HashMap<String, String>> {
        val list: MutableList<HashMap<String, String>> = ArrayList()
        try {
            val parser = Xml.newPullParser()
            val map = HashMap<String, String>()
            parser.setInput(inputStream, "UTF-8")
            var eventCode = parser.eventType
            while (eventCode != XmlPullParser.END_DOCUMENT) {
                when (eventCode) {
                    XmlPullParser.START_DOCUMENT -> {
                    }

                    XmlPullParser.START_TAG -> {
                        if (parser.name == "product") {
                        } else if (parser.name == "id") {
                            map["id"] = parser.nextText()
                        } else if (parser.name == "name") {
                            map["name"] = parser.nextText()
                        } else if (parser.name == "price") {
                            map["price"] = parser.nextText()
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if (parser.name == "product") {
                            list.add(map)
                        }
                    }

                    else -> {
                    }
                }
                eventCode = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}