package com.semye.android.module.item35_xml

import android.os.Bundle
import android.util.Xml
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream

/**
 * 安卓XML的三种解析方式
 */
class XMLMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_xml)
        findViewById<TextView>(R.id.tv_parse).setOnClickListener(this)
    }


    override fun onClick(v: View) {
        val inputStream = resources.openRawResource(R.raw.products)
        executeSaxParse(inputStream)
        val inputStream2 = resources.openRawResource(R.raw.products)
        executeDomParse(inputStream2)
        val inputStream3 = resources.openRawResource(R.raw.products)
        executePullParse(inputStream3)
    }

    /**
     * pull解析 android 推荐的解析方式
     * PULL解析器小巧轻便，解析速度快，简单易用，非常适合在Android移动设备中使用 pull可以随时停止
     *
     * @param inputStream
     */
    private fun executePullParse(inputStream: InputStream) {
        try {
            val start = System.currentTimeMillis()
            val list = PullXmlParser.parseXML(inputStream)
            val end = System.currentTimeMillis()
            println("pull=====>" + (end - start))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Dom解析
     *
     * @param inputStream
     */
    private fun executeDomParse(inputStream: InputStream) {
        val start = System.currentTimeMillis()
        val list = DomXmlParser.parse(inputStream)
        val end = System.currentTimeMillis()
        println("dom=====>" + (end - start))
    }

    /**
     * sax解析  基于事件驱动
     * 优点：解析速度快，占用内存少。非常适合在Android移动设备中使用。
     *
     * @param inputStream
     */
    private fun executeSaxParse(inputStream: InputStream) {
        try {
            val start = System.currentTimeMillis()
            val productHandler = ProductHandler()
            Xml.parse(inputStream, Xml.Encoding.UTF_8, productHandler)
            val end = System.currentTimeMillis()
            println("sax=====>" + (end - start))
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        } finally {
            inputStream.close()
        }
    }
}