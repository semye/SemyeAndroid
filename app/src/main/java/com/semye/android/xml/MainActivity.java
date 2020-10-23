package com.semye.android.xml;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.semye.android.R;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_xml);
        initWidget();
    }

    private void initWidget() {
    }

    @Override
    public void onClick(@NonNull View v) {
        InputStream is = getResources().openRawResource(R.raw.products);
        executeSaxParse(is);
        executeDomParse(is);
        executePullParse(is);
    }

    /**
     * pull解析 android 推荐的解析方式
     * PULL解析器小巧轻便，解析速度快，简单易用，非常适合在Android移动设备中使用 pull可以随时停止
     *
     * @param is
     */
    private void executePullParse(InputStream is) {
        try {
            long start = System.currentTimeMillis();
            List<HashMap<String, String>> list = XmlPull.parseXML(is);
            long end = System.currentTimeMillis();
            System.out.println("pull=====>" + (end - start));
            Toast.makeText(this, "Pull解析====>" + list.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Dom解析
     *
     * @param is
     */
    private void executeDomParse(InputStream is) {
        long start = System.currentTimeMillis();
        DomXml myDomDemo = new DomXml(is);
        List<HashMap<String, String>> list = myDomDemo.getProducts();
        long end = System.currentTimeMillis();
        System.out.println("dom=====>" + (end - start));
        Toast.makeText(this, "Dom解析====>" + list.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * sax解析  基于事件驱动
     * 优点：解析速度快，占用内存少。非常适合在Android移动设备中使用。
     *
     * @param is
     */
    private void executeSaxParse(InputStream is) {
        try {
            long start = System.currentTimeMillis();
            ProductHandler productHandler = new ProductHandler();
            Xml.parse(is, Xml.Encoding.UTF_8, productHandler);
            List<HashMap<String, String>> list = productHandler.getProduct();
            long end = System.currentTimeMillis();
            System.out.println("sax=====>" + (end - start));
            if (list != null) {
                Toast.makeText(this, "Sax解析====>" + list.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
