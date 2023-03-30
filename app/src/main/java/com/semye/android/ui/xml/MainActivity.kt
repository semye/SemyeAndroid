package com.semye.android.ui.xml

import android.os.Bundle
import android.util.Xml
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_xml)
        initWidget()
    }

    private fun initWidget() {}
    override fun onClick(v: View) {
        val `is` = resources.openRawResource(R.raw.products)
        executeSaxParse(`is`)
        executeDomParse(`is`)
        executePullParse(`is`)
    }

    /**
     * pull解析 android 推荐的解析方式
     * PULL解析器小巧轻便，解析速度快，简单易用，非常适合在Android移动设备中使用 pull可以随时停止
     *
     * @param is
     */
    private fun executePullParse(`is`: InputStream) {
        try {
            val start = System.currentTimeMillis()
            val list = XmlPull.parseXML(`is`)
            val end = System.currentTimeMillis()
            println("pull=====>" + (end - start))
            Toast.makeText(this, "Pull解析====>$list", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Dom解析
     *
     * @param is
     */
    private fun executeDomParse(`is`: InputStream) {
        val start = System.currentTimeMillis()
        val myDomDemo = DomXml(`is`)
        val list = myDomDemo.products
        val end = System.currentTimeMillis()
        println("dom=====>" + (end - start))
        Toast.makeText(this, "Dom解析====>$list", Toast.LENGTH_SHORT).show()
    }

    /**
     * sax解析  基于事件驱动
     * 优点：解析速度快，占用内存少。非常适合在Android移动设备中使用。
     *
     * @param is
     */
    private fun executeSaxParse(`is`: InputStream) {
        try {
            val start = System.currentTimeMillis()
            val productHandler = ProductHandler()
            Xml.parse(`is`, Xml.Encoding.UTF_8, productHandler)
            val list = productHandler.product
            val end = System.currentTimeMillis()
            println("sax=====>" + (end - start))
            if (list != null) {
                Toast.makeText(this, "Sax解析====>$list", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }
    }
}