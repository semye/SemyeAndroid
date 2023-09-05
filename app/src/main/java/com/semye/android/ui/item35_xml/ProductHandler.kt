package com.semye.android.ui.item35_xml

import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.util.*

class ProductHandler : DefaultHandler() {
    private var list: MutableList<HashMap<String, String?>>? = null
    private var hashMap: HashMap<String, String?>? = null
    private var s: String? = null

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        super.characters(ch, start, length)
        s = String(ch, start, length)
    }

    @Throws(SAXException::class)
    override fun startDocument() {
        super.startDocument()
        list = ArrayList()
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        super.endDocument()
    }

    @Throws(SAXException::class)
    override fun startElement(
        uri: String,
        localName: String,
        qName: String,
        attributes: Attributes
    ) {
        super.startElement(uri, localName, qName, attributes)
        if (localName == "product") {
            hashMap = HashMap()
        }
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, localName: String, qName: String) {
        super.endElement(uri, localName, qName)
        if (localName == "id") {
            hashMap?.put("id", s)
        }
        if (localName == "name") {
            hashMap?.put("name", s)
        }
        if (localName == "price") {
            hashMap?.put("price", s)
        }
        if (localName == "product") {
            list?.add(hashMap!!)
        }
    }
}