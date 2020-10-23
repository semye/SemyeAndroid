package com.semye.android.jsbridge;

import android.content.Context;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BridgeUtil {

    final static String YY_OVERRIDE_SCHEMA = "abc://";
    final static String YY_RETURN_DATA = YY_OVERRIDE_SCHEMA + "return/";//格式为   yy://return/{function}/returncontent

    final static String YY_FETCH_QUEUE = YY_RETURN_DATA + "_fetchQueue/";
    final static String EMPTY_STR = "";
    final static String UNDERLINE_STR = "_";
    final static String SPLIT_MARK = "/";

    final static String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    final static String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    final static String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();";
    public final static String JAVASCRIPT_STR = "javascript:";

    public static String parseFunctionName(String jsUrl) {
        return jsUrl.replace("javascript:WebViewJavascriptBridge.", "").replaceAll("\\(.*\\);", "");
    }

    /**
     * 从URL中获取js中的data
     *
     * @param url url
     * @return  从URL中获取js中的data
     */
    public static String getDataFromReturnUrl(String url) {
        if (url.startsWith(YY_FETCH_QUEUE)) {
            return url.replace(YY_FETCH_QUEUE, EMPTY_STR);
        }

        String temp = url.replace(YY_RETURN_DATA, EMPTY_STR);
        String[] functionAndData = temp.split(SPLIT_MARK);

        if (functionAndData.length >= 2) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < functionAndData.length; i++) {
                sb.append(functionAndData[i]);
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 从URL中获取js中的function名
     *
     * @param url url
     * @return 从URL中获取js中的function名
     */
    public static String getFunctionFromReturnUrl(String url) {
        String temp = url.replace(YY_RETURN_DATA, EMPTY_STR);
        String[] functionAndData = temp.split(SPLIT_MARK);
        if (functionAndData.length >= 1) {
            return functionAndData[0];
        }
        return null;
    }


    /**
     * js 文件将注入为第一个script引用
     *
     * @param view view
     * @param url  url
     */
    public static void webViewLoadJs(WebView view, String url) {
        String js = "var newscript = document.createElement(\"script\");";
        js += "newscript.src=\"" + url + "\";";
        js += "document.scripts[0].parentNode.insertBefore(newscript,document.scripts[0]);";
        view.loadUrl("javascript:" + js);
    }

    /**
     * webview加载指定的js
     *
     * @param view view
     * @param path path
     */
    public static void webViewLoadLocalJs(WebView view, String path) {
        String jsContent = assetFile2Str(view.getContext(), path);
        view.loadUrl("javascript:" + jsContent);
    }

    /**
     * 从资源文件中读取文件 并转换成字符串
     *
     * @param c c
     * @param urlStr url
     * @return  从资源文件中读取文件 并转换成字符串
     */
    public static String assetFile2Str(Context c, String urlStr) {
        InputStream in = null;
        try {
            in = c.getAssets().open(urlStr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) {
                    sb.append(line);
                }
            } while (line != null);

            bufferedReader.close();
            in.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
