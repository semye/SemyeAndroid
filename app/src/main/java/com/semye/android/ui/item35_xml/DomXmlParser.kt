package com.semye.android.ui.item35_xml

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

object DomXmlParser {


    @JvmStatic
    fun parse(inputStream: InputStream): List<HashMap<String, String>> {
        try {
            val factory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            val document = builder.parse(inputStream)
            val rootElement = document.documentElement.getElementsByTagName("product")
            val products: MutableList<HashMap<String, String>> = ArrayList()
            for (i in 0 until rootElement.length) {
                val map = HashMap<String, String>()
                val element = rootElement.item(i) as Element
                val elemId = element.getElementsByTagName("id").item(0) as Element
                map["id"] = elemId.textContent
                val elemName = element.getElementsByTagName("name").item(0) as Element
                map["name"] = elemName.textContent
                val elemPrice = element.getElementsByTagName("price").item(0) as Element
                map["price"] = elemPrice.textContent
                products.add(map)
            }
            return products
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream.close()
        }
        return emptyList()
    }


}