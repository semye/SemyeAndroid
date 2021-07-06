package com.semye.android.xml

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.util.*

object XmlPull {
    @Throws(Exception::class)
    fun parseXML(`is`: InputStream?): List<HashMap<String, String>> {
        val list: MutableList<HashMap<String, String>> = ArrayList()
        val parser = Xml.newPullParser()
        val map = HashMap<String, String>()
        parser.setInput(`is`, "UTF-8")
        var eventCode = parser.eventType
        while (eventCode != XmlPullParser.END_DOCUMENT) {
            when (eventCode) {
                XmlPullParser.START_DOCUMENT -> {
                }
                XmlPullParser.START_TAG -> if (parser.name == "product") {
                } else if (parser.name == "id") {
                    map["id"] = parser.nextText()
                } else if (parser.name == "name") {
                    map["name"] = parser.nextText()
                } else if (parser.name == "price") {
                    map["price"] = parser.nextText()
                }
                XmlPullParser.END_TAG -> if (parser.name == "product") {
                    list.add(map)
                }
                else -> {
                }
            }
            eventCode = parser.next()
        }
        return list
    }
}