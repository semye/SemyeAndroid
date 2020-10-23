package com.semye.android.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * String工具类
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * email的正则表达式
     */
    private final static Pattern email = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 手机号的正则表达式
     */
    private final static Pattern phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");


    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * Returns true if the string is numeric
     *
     * @param str string
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    public static String renamePhoto(@NonNull String filename) {
        return getPictureName(filename) + ".png";
    }


    public static String getPictureName(String filename) {
        return filename.substring(0, filename.indexOf("."));
    }


    /**
     * 生成uuid
     *
     * @return 处理后的uuid
     */
    public static String createUUID() {
        String val;
        UUID uuid = UUID.randomUUID();
        val = uuid.toString().replace("-", "");
        return val;
    }


    /**
     * 判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
     */
    private final static Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");


    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @SuppressLint("SimpleDateFormat")
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    /**
     * 毫秒值转换为mm:ss
     *
     * @param ms
     */
    public static String timeFormat(int ms) {
        StringBuilder time = new StringBuilder();
        time.delete(0, time.length());
        ms /= 1000;
        int s = ms % 60;
        int min = ms / 60;
        if (min < 10) {
            time.append(0);
        }
        time.append(min).append(":");
        if (s < 10) {
            time.append(0);
        }
        time.append(s);
        return time.toString();
    }

    /**
     * 将字符串转位日期类型
     *
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(@Nullable String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(@Nullable String email) {
        return !(email == null || email.trim().length() == 0) && StringUtils.email.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(@Nullable String phoneNum) {
        if (phoneNum == null || phoneNum.trim().length() == 0)
            return false;
        return phone.matcher(phoneNum).matches();
    }


    /**
     * 判断是不是一个合法的身份证号
     *
     * @param idnum 身份证号
     * @return 如果是合法的身份证号返回true,
     */
    public static boolean isIDcard(@Nullable String idnum) {
        return !(idnum == null || idnum.trim().length() == 0) && idNumPattern.matcher(idnum).matches();
    }


    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(@NonNull String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(@Nullable Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }


    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(@NonNull String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(@NonNull String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0D;
    }


    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(@NonNull String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }








    /**
     * MD5加密
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * KJ加密
     */
    public static String KJencrypt(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c + 5));
        }
        return hex.toString();
    }

    /**
     * KJ解密
     */
    public static String KJdecipher(String str) {
        char[] cstr = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char c : cstr) {
            hex.append((char) (c - 5));
        }
        return hex.toString();
    }


    /**
     * 将对象序列化为字符串
     *
     * @param obj
     * @return
     * @throws IOException
     */
    @Nullable
    public static String getStrFromObj(Object obj) {
        String serStr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.toString();
        }

        return serStr;
    }

    /**
     * 将字符串反序列化成对象
     *
     * @param serStr
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Nullable
    public static Object getObjFromStr(String serStr) {
        Object result = null;
        try {
            String redStr = java.net.URLDecoder.decode(serStr, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            result = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (@NonNull IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 解析XML转换成对象
     *
     * @param is          输入流
     * @param clazz       对象Class
     * @param fields      字段集合一一对应节点集合
     * @param elements    节点集合一一对应字段集合
     * @param itemElement 每一项的节点标签
     * @return
     */
    @NonNull
    public static List<Object> parse(InputStream is, @NonNull Class<?> clazz, @NonNull List<String> fields, @NonNull List<String> elements, @NonNull String itemElement) {
        Log.v("rss", "开始解析XML.");
        List<Object> list = new ArrayList<Object>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(is, "UTF-8");
            int event = xmlPullParser.getEventType();

            Object obj = null;

            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (itemElement.equals(xmlPullParser.getName())) {
                            obj = clazz.newInstance();
                        }
                        if (obj != null && elements.contains(xmlPullParser.getName())) {
                            setFieldValue(obj, fields.get(elements.indexOf(xmlPullParser.getName())), xmlPullParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (itemElement.equals(xmlPullParser.getName())) {
                            list.add(obj);
                            obj = null;
                        }
                        break;
                }
                event = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.e("rss", "解析XML异常：" + e.getMessage());
            throw new RuntimeException("解析XML异常：" + e.getMessage());
        }
        return list;
    }

    /**
     * 设置字段值
     *
     * @param propertyName 字段名
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    public static void setFieldValue(@NonNull Object obj, @NonNull String propertyName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    /**
     * 比较两个String是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalStr(@Nullable String str1, @Nullable String str2) {
        return !(null == str1 || null == str2) && str1.equals(str2);
    }

    /**
     * 将流保存到指定文件
     *
     * @param fileName 要保存的文件路径
     * @param in       要写入文件的流
     */
    public static void saveToFile(String fileName, InputStream in) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        bis = new BufferedInputStream(in); // 获取网络输入流
        try {
            fos = new FileOutputStream(fileName); // 建立文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try { // 保存文件
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MD5加密
     *
     * @param source 要加密的字符串比特流
     * @return 经过加密的字符串
     */
    @Nullable
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>>
                // 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    @Nullable
    public static String toHexString(@Nullable byte[] keyData) {
        if (keyData == null) {
            return null;
        }
        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);
        for (int i = 0; i < keyData.length; i++) {
            String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }


    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        int length = data.length;
        for (int i = 0; i < length; ++i) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (++two_halfs < 1);
        }
        return buf.toString();
    }


    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String encodeHex(byte[] data) {
        final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int l = data.length;

        char[] out = new char[l << 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return new String(out);
    }


    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String toLocalDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 要判断的字符串
     * @return 是否为空
     */
    public static boolean isNull(@Nullable String str) {
        return (str == null || str.equals(""));
    }


    /**
     * Utf8URL编码
     *
     * @param text 文字
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String Utf8URLencode(String text) {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            byte[] b = new byte[0];
            try {
                b = Character.toString(c).getBytes("UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 0; j < b.length; j++) {
                int k = b[j];
                if (k < 0)
                    k += 256;
                result.append("%" + Integer.toHexString(k).toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 获得图片
     *
     * @param url      图片地址
     * @param fileName 文件名
     * @return 0 文件存在, 1 正常创建文件, -1 创建文件失败
     * @throws Exception
     */
    public static int getImage(String url, String fileName) {
//        try {
//            File file = new File(fileName);
//            if (file.exists()) {
//                return 0;
//            }
//            HttpClient client = new DefaultHttpClient();
//            URI uri = URI.create(url);
//            HttpGet get = new HttpGet(uri);
//            HttpResponse response = client.execute(get);
//            HttpEntity entity = response.getEntity();
//            final int BUFFER = 1024;
//            InputStream in = entity.getContent();
//            if (in != null) {
//                FileOutputStream out = new FileOutputStream(file);
//                byte[] b = new byte[BUFFER];
//                int len = 0;
//                while ((len = in.read(b)) != -1) {
//                    out.write(b, 0, len);
//                }
//                in.close();
//                out.close();
//                return 1;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    /**
     * 像素转换为dip
     *
     * @param scale
     * @param px
     * @return
     */
    public static int pxToDip(float scale, int px) {
        return (int) (px / scale + 0.5f);
    }

    /**
     * dip转换为像素
     *
     * @param scale
     * @param dip
     * @return
     */
    public static int dipToPx(float scale, int dip) {
        return (int) (dip * scale + 0.5f);
    }

    /**
     * 判断是否有SD卡
     *
     * @return
     */
    public static boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String htmlToContent(String str) {
        if (isNull(str)) {
            return str;
        }
        str = str.replaceAll("\n", "<br />");
        str = str.replaceAll("\r\r", "<br />");
        Spanned span = Html.fromHtml(str);
        return span.toString();
    }

    /**
     * SD卡路径
     *
     * @return SD卡路径
     */
    public static String getSdPath() {
        if (hasSdcard()) {
            File file = Environment.getExternalStorageDirectory();
            return file.getAbsolutePath();
        } else {
            return null;
        }
    }

    /**
     * 读取ＳＤ卡中文件
     *
     * @return str
     */
    public static String getDataFromSD(String path) {
        String partJson = "";
        if (hasSdcard()) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    InputStream input = new BufferedInputStream(new FileInputStream(file));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String temp;
                    while ((temp = reader.readLine()) != null) {
                        partJson += temp;
                    }
                    reader.close();
                    reader = null;
                    input.close();
                    input = null;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return partJson;
    }

    /**
     * MD5加密
     *
     * @param plainText 要加密的字符串
     * @return 经过MD5加密的字符串
     */
    @Nullable
    public static String Md5(String plainText) {
        String temp = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            temp = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * 去除list中重复数据
     *
     * @param list
     */
    @NonNull
    public static List<String> removeDuplicate(List<String> list) {
        HashSet<String> h = new HashSet<String>(list);
        List<String> mList = new ArrayList<String>();
        mList.addAll(h);
        return mList;
    }

    /**
     * 抛出异常退出程序
     */
    @SuppressWarnings("null")
    public static void exit() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                // 遇到不可抓取的异常会到这里来,就不会弹出对话框了,完美结局
                // 在这里最好让所有的activity全finish了，也另加入关闭进程的方法
            }
        });
        String meIsNull = null;
        // 在这里肯定是空指针异常，遇到异常之后，执行上面的回调代码，就不会弹出对话框了
        meIsNull.equals("空指针");
    }

    /**
     * 计算textview宽度
     *
     * @param tv
     * @return
     */
    public static float getTextWidth(@Nullable TextView tv) {
        if (null == tv)
            return 0f;
        return getCharacterWidth(tv.getText().toString(), tv.getTextSize());
    }


    /**
     * 获取手机信息
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    public static void phoneInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // 手机制造商
        String phoneModle = Build.MANUFACTURER;
        // 手机型号
        String phoneName = Build.MODEL;
        // SDK号
        int sdk = Build.VERSION.SDK_INT;
        // android系统版本号
        String release = Build.VERSION.RELEASE;
        // 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID.
        // Return null if device ID is not available.
        tm.getDeviceId();
        System.out.println(phoneModle);
        System.out.println(phoneName);
        System.out.println(String.valueOf(sdk));
        System.out.println(release);
        System.out.println(tm.getDeviceId());
    }


    /**
     * 判断软件是否第一次开启
     */
    public static void theFirstEnter(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        int currentVersion = info.versionCode;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int lastVersion = prefs.getInt("VERSION_KEY", 0);
        if (currentVersion != lastVersion) {
            // saveIcon(context);
            inputSP(context, "FirstEnter", "isFirst", true);
            inputSP(context, "FirstEnter", "FirstPortraitView", true);
            inputSP(context, "FirstEnter", "FirstLandView", true);
            prefs.edit().putInt("VERSION_KEY", currentVersion).commit();
        } else {
            inputSP(context, "FirstEnter", "isFirst", false);
            return;
        }

    }

    /**
     * SharedPreferences input
     */
    public static void inputSP(Context mContext, String fileName, String Key, Boolean value) {
        @SuppressWarnings("static-access")
        SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Key, value);
        editor.commit();
    }

    public static void inputSP(Context mContext, String fileName, String Key, long value) {
        @SuppressWarnings("static-access")
        SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(Key, value);
        editor.commit();
    }

    /**
     * SharedPreferences get
     */
    public static boolean getSP(@Nullable Context mContext, String fileName, String Key, Boolean value) {
        if (mContext != null) {
            @SuppressWarnings("static-access")
            SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);

            boolean result = preferences.getBoolean(Key, value);

            return result;
        } else {
            return true;
        }
    }

    /**
     * 写漫画路径
     */
    public static File newFileInSdcard(String path) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {// 创建一个文件夹对象，赋值为外部存储器的目录
            File sdcardDir = Environment.getExternalStorageDirectory();
            String mypath = sdcardDir.getPath() + "/" + path;
            Log.d("test", "comic path = " + mypath);
            File file = new File(mypath);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        } else {
            return null;
        }
    }


    /**
     * SharedPreferences input
     */
    public static void inputSP(Context mContext, String fileName, String Key, String value) {
        @SuppressWarnings("static-access")
        SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Key, value);
        editor.commit();
    }

    /**
     * SharedPreferences get
     */
    @Nullable
    public static String getSP(@Nullable Context mContext, String fileName, String Key, String value) {
        if (mContext != null) {
            @SuppressWarnings("static-access")
            SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);
            String result = preferences.getString(Key, value);

            return result;
        } else {
            return null;
        }
    }

    /**
     * SharedPreferences get
     */
    @SuppressWarnings("null")
    public static long getSP(@Nullable Context mContext, String fileName, String Key, long value) {
        if (mContext != null) {
            @SuppressWarnings("static-access")
            SharedPreferences preferences = mContext.getSharedPreferences(fileName, mContext.MODE_PRIVATE);
            long result = preferences.getLong(Key, value);

            return result;
        } else {
            return (Long) null;
        }
    }

    public static boolean isTopApp(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("rawtypes")
        List tasks = activityManager.getRunningTasks(5);
        if (tasks == null || tasks.isEmpty()) {
            return false;
        }
        ActivityManager.RunningTaskInfo task = (ActivityManager.RunningTaskInfo) tasks.get(0);
        String taskPackageName = task.topActivity.getPackageName();
        @SuppressWarnings("unused")
        String taskClassName = task.topActivity.getClassName();
        // Log.d("uuu","taskClassName = "+ taskClassName);
        // Log.d("uuu","isTop = "+
        // context.getClass().getName().equals(taskClassName));
        Log.d("uuu", "taskPackageName = " + taskPackageName);
        Log.d("uuu", "isTop = " + context.getPackageName().equals(taskPackageName));
        Log.d("uuu", "context.getPackageName() = " + context.getPackageName());
        return context.getPackageName().equals(taskPackageName);
    }

    /**
     * 判断桌面是否存在应用的快捷方式
     *
     * @param cx
     * @return
     */
    public static boolean hasShortcut(@NonNull Context cx) {
        boolean result = false;
        // 获取当前应用名称
        String title = null;
        try {
            final PackageManager pm = cx.getPackageManager();
            title = pm.getApplicationLabel(pm.getApplicationInfo(cx.getPackageName(), PackageManager.GET_META_DATA)).toString();
        } catch (Exception e) {
        }
        final String uriStr;
        if (Build.VERSION.SDK_INT < 8) {
            uriStr = "content://com.android.launcher.settings/favorites?notify=true";
        } else {
            uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
        }
        final Uri CONTENT_URI = Uri.parse(uriStr);
        final Cursor c = cx.getContentResolver().query(CONTENT_URI, null, "title=?", new String[]{title}, null);
        if (c != null && c.getCount() > 0) {
            result = true;
        }
        return result;
    }


    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public static void deleteFile(File file) {
        if (!file.exists()) {
            Log.d("test", "文件可能不存在");
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }


    @NonNull
    private static String TAG = StringUtils.class.getName();


    /**
     * 检测是否有网络链接
     *
     * @param context 上下文
     * @return 是否有网络链接
     */
    public static boolean isConnect(@NonNull Context context) {
        try { // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo(); // 获取网络连接管理的对象
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) { // 判断当前网络是否已经连接
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return false;
    }


    /**
     * SHA1加密
     *
     * @param source
     * @return
     */
    @Nullable
    public static String SHA1(byte[] source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(source);
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * sha1加密
     *
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }


    public static String hmacSha1(@NonNull String value, @NonNull String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            Log.i("async", "rawHmac==" + rawHmac.length);
            return Base64.encodeToString(rawHmac, Base64.DEFAULT);
            // return Base64.encode(hexBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 与小数位精度(四舍五入等)相关的一些常用工具方法.
     * <p>
     * <p>
     * <p>
     * float/double的精度取值方式分为以下几种: <br>
     * <p>
     * java.math.BigDecimal.ROUND_UP <br>
     * <p>
     * java.math.BigDecimal.ROUND_DOWN <br>
     * <p>
     * java.math.BigDecimal.ROUND_CEILING <br>
     * <p>
     * java.math.BigDecimal.ROUND_FLOOR <br>
     * <p>
     * java.math.BigDecimal.ROUND_HALF_UP<br>
     * <p>
     * java.math.BigDecimal.ROUND_HALF_DOWN <br>
     * <p>
     * java.math.BigDecimal.ROUND_HALF_EVEN <br>
     * <p>
     * <p>
     * 对double数据进行取精度.
     * <p>
     * <p>
     * <p>
     * For example: <br>
     * <p>
     * double value = 100.345678; <br>
     * <p>
     * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
     * <p>
     * ret为100.3457 <br>
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */

    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);

        double d = bd.doubleValue();
        bd = null;

        return d;
    }


    /**
     * 解码并缩小图片尺寸，以减少内存消耗
     *
     * @param filePath
     * @return
     */
    public static Bitmap decodeFile(String filePath) {
        // decodes image and scales it to reduce memory consumption
        File f = new File(filePath);
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE = 120;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            // Log.i(TAG, "scale:" + scale);
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

    /**
     * 将路径中的图片转换为Bitmap
     *
     * @param filePath
     * @return
     */
    public static Bitmap fileToBitmap(String filePath) {
        return BitmapFactory.decodeFile(filePath);
    }

    public static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static int computeSampleSize(@NonNull BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    public static Bitmap makeSuitableDrawable(Context context, @NonNull File file) {
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
            Rect rect = new Rect(0, 0, opts.outWidth, opts.outHeight);
            opts.inJustDecodeBounds = false;
            FileInputStream fis = new FileInputStream(file);
            if (file.length() > 1024 * 500) {
            }
            opts.inSampleSize = 4;
            Bitmap decodeStream = BitmapFactory.decodeStream(fis, rect, opts);
            fis.close();
            return decodeStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static int computeSampleSize(BitmapFactory.Options options, int target) {
        int w = options.outWidth;
        int h = options.outHeight;
        int candidateW = w / target;
        int candidateH = h / target;
        int candidate = Math.max(candidateW, candidateH);
        if (candidate == 0)
            return 1;
        if (candidate > 1) {
            if ((w > target) && (w / candidate) < target)
                candidate -= 1;
        }
        if (candidate > 1) {
            if ((h > target) && (h / candidate) < target)
                candidate -= 1;
        }
        return candidate;
    }

    @SuppressWarnings("deprecation")
    public static Drawable makeSuitableDrawable(Context context, int ResId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), ResId, opts);

        // opts.inSampleSize = BitmapUtil.computeSampleSize(opts, -1, 128 *
        // 128)-2;
        try {
            final int IMAGE_MAX_SIZE = 500000; // 200k
            int scale = 1;
            while ((opts.outWidth * opts.outHeight) * (1 / Math.pow(scale, 2)) > IMAGE_MAX_SIZE) {
                scale++;
            }
            Bitmap temp = null;
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                opts = new BitmapFactory.Options();
                opts.inSampleSize = scale;
                temp = BitmapFactory.decodeResource(context.getResources(), ResId, opts);

                // resize to desired dimensions
                int height = temp.getHeight();
                int width = temp.getWidth();

                double y = Math.sqrt(IMAGE_MAX_SIZE / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(temp, (int) x, (int) y, true);
                temp.recycle();
                temp = scaledBitmap;
            } else {
                temp = BitmapFactory.decodeResource(context.getResources(), ResId, opts);
            }
            return new BitmapDrawable(temp);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return null;
    }

    public static Bitmap makeSuitableDrawable(Context context, @Nullable String pathName) {
        if (pathName == null) {
            throw new RuntimeException("路径为空");
        }
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        // BitmapFactory.decodeResource(context.getResources(), ResId, opts);
        BitmapFactory.decodeFile(pathName, opts);
        // opts.inSampleSize = BitmapUtil.computeSampleSize(opts, -1, 128 *
        // 128)-2;
        try {
            final int IMAGE_MAX_SIZE = 500000; // 200k
            int scale = 1;
            while ((opts.outWidth * opts.outHeight) * (1 / Math.pow(scale, 2)) > IMAGE_MAX_SIZE) {
                scale++;
            }
            Bitmap temp = null;
            if (scale > 1) {
                scale--;
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                opts = new BitmapFactory.Options();
                opts.inSampleSize = scale;
                temp = BitmapFactory.decodeFile(pathName, opts);

                // resize to desired dimensions
                int height = temp.getHeight();
                int width = temp.getWidth();

                double y = Math.sqrt(IMAGE_MAX_SIZE / (((double) width) / height));
                double x = (y / height) * width;

                Bitmap scaledBitmap = Bitmap.createScaledBitmap(temp, (int) x, (int) y, true);
                temp.recycle();
                temp = scaledBitmap;
            } else {
                temp = BitmapFactory.decodeFile(pathName, opts);
            }
            return temp;
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static Bitmap toRoundCorner(@Nullable Bitmap bitmap, int pixels) {
        if (bitmap == null) {
            return null;
        }
        // 这里经常内存溢出
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();// 内存溢出啊亲
        bitmap = null;
        return output;
    }

    @Nullable
    public static Bitmap compressBitmap(@Nullable Bitmap bitmap, int width) {
        if (bitmap == null) {
            return null;
        }

        Bitmap output = Bitmap.createScaledBitmap(bitmap, width, bitmap.getHeight() * width / bitmap.getWidth(), true);
        if (bitmap != output) {
            bitmap.recycle();
            bitmap = null;
        }
        return output;
    }

    public static boolean isErrMsg(String jsonString) {
        return jsonString.contains("err_msg");
    }

    public static <T> Object json2Object(String jsonStr, @NonNull Class<T> className) {
        Gson gson = new Gson();
        Object object = gson.fromJson(jsonStr, className);
        return object;
    }

    public static <T> ArrayList<T> json2List(String jsonStr, Class<T> t) {
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }


    public static byte[] Stream2byte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) > 0) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }

    @NonNull
    public static String getTwoBitFloatString(@NonNull String src) {
        if (!TextUtils.isEmpty(src)) {
            int pointIndex = src.indexOf("");
            if (pointIndex != -1 && pointIndex + 2 <= src.length() - 1) {
                return src.substring(0, pointIndex + 2 + 1);
            }
        }
        return src;
    }

    public static void showToast(Context context, int stringId) {
        Toast msg = Toast.makeText(context, stringId, Toast.LENGTH_SHORT);
        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getXOffset() / 2);
        msg.show();
    }

    public static void showToast(Context context, int stringId, int type) {
        if (type != Toast.LENGTH_LONG || type != Toast.LENGTH_SHORT) {
            type = Toast.LENGTH_SHORT;
        }
        Toast msg = Toast.makeText(context, stringId, type);
        msg.show();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return
     */
    @SuppressWarnings("unused")
    private static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                Log.i(TAG, lineStr);
                if (lineStr.contains("sdcard") && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure", "");
                        return result;
                    }
                }
                if (p.waitFor() != 0 && p.exitValue() == 1) { // 检查命令是否执行失败。
                    // p.exitValue()==0表示正常结束，1：非正常结束
                    Log.e(TAG, "命令执行失败!");
                }
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return Environment.getExternalStorageDirectory().getPath();
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(@NonNull Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 根据ＵＲＬ从网络下载图片
     *
     * @return bitmap
     */
    @Nullable
    public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        byte[] data = null;
        try {
            data = readInputStream(new URL(url).openStream());
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 将Bitmap图片保存到本地
     *
     * @param bitmap
     * @param mPath
     * @return path
     */
    @Nullable
    public static String saveBitmaptoSD(@NonNull Bitmap bitmap, String mPath, int ratio) {
        String path = null;
        File tempfile = new File(mPath);
        if (tempfile.exists()) { // 如果文件存在删除重建
            tempfile.delete();
        }
        try {
            tempfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(tempfile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, ratio, fOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            path = mPath;
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 流转换为byte[]
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


    /**
     * 保存文件到ＳＤ卡，如果文件存在覆盖原来文件
     *
     * @param mPath
     * @param fileName
     * @param partJson
     * @return
     */
    public static void saveFileToSD(String mPath, String fileName, String partJson) {
        if (TextUtils.isEmpty(partJson)) {
            return;
        }
        // 路径
        File path = new File(mPath);
        // 文件
        File file = new File(path, fileName + ".txt");
        if (path.exists()) { // 路径已存在
            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                writer.write(partJson);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // 路径不存在创建路径写入文件
            path.mkdirs();
            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                writer.write(partJson);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 删除文件夹,及其所有文件
     * <p>
     * param folderPath 文件夹完整绝对路径
     *
     * @param folderPath
     */
    public static void delFolder(@NonNull String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(@NonNull String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 从网络获取json数据
     *
     * @param urlStr url
     * @return
     */
    @Nullable
    public static String getJsonFromNet(String urlStr) {
        String josn = null;
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(urlStr);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            urlConn.connect();
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                byte[] data = readStream(urlConn.getInputStream());
                josn = new String(data, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (urlConn != null)
                urlConn.disconnect();
        }

        return josn;
    }


    /**
     * 流转换为byte
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] data = baos.toByteArray();
        inputStream.close();
        baos.close();
        return data;
    }


    /**
     * 获得锁屏时间 毫秒
     */
    public static int getScreenOffTime(@NonNull Context context) {
        int screenOffTime = 0;
        try {
            screenOffTime = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
        } catch (Exception localException) {
            //
        }
        return screenOffTime;
    }

    /**
     * 设置背光时间 毫秒
     * <p>
     * 需要加入权限　android.permission.WRITE_SETTINGS
     *
     * @param context
     * @param paramInt
     * @return
     */
    public static boolean setScreenOffTime(@NonNull Context context, int paramInt) {
        boolean set = false;
        try {
            set = Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, paramInt);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return set;
    }


    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap readBitMap(String url) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        FileInputStream is = null;
        try {
            is = new FileInputStream(url);
            return BitmapFactory.decodeStream(is, null, opt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    @Nullable
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于50kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            //options -= 10;//每次都减少10
            options = options - options / 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 取得bitmap占用内存大小
     *
     * @param data
     * @return
     */
    @SuppressLint("NewApi")
    public static int sizeOf(@NonNull Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else {
            return data.getByteCount();
        }
    }


    /**
     * 压缩图片到指定大小
     *
     * @param bitmap
     */
    @NonNull
    public static Bitmap imageZoom(Bitmap bitmap, double size) {
        // 图片允许最大空间 单位：KB
        double maxSize = size;
        // 将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        // 将字节换成KB
        double mid = b.length / 1024;
        // 判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            // 获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            // 开始压缩 此处用到平方根 将宽带和高度压缩掉对应的平方根倍
            // （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitmap = zoomImage(bitmap, bitmap.getWidth() / Math.sqrt(i), bitmap.getHeight() / Math.sqrt(i));
        }
        return bitmap;
    }


    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 取得当前网络名称
     */
    public static String getNetName(Context context) {
        String netName;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获得当前活动的网络信息
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo == null) {
            netName = "无网";
        } else {
            netName = activeNetInfo.getTypeName();
        }
        return netName;
    }

    /**
     * 判断过去的某时间是否在当天
     *
     * @param time yyyy-MM-dd HH:mm
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean isToday(String time) {
        try {
            // 当前时间毫秒数
            long currTime = System.currentTimeMillis();
            // 某时间的下一天的0点
            String nextTime = getSpecifiedDayAfter(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(nextTime);
            long next = date.getTime();
            if (currTime > next) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }


    @SuppressLint("SimpleDateFormat")
    public static String getData(String time) {
        // 当前日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currDate = format.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(time);
            long old = date.getTime();
            long currTime = System.currentTimeMillis();
            long spanTime = currTime - old;
            // 传入日期
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
            String timeDate = timeFormat.format(date);
            //当前日期的前一天
            String beforeDate = getSpecifiedDayBefore();
            if (spanTime < 3600000) { // xx分钟前
                return spanTime / 60000 + "分钟前";
            } else if (equalStr(currDate, timeDate)) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                return "今天 " + sdf2.format(date);
            } else if (equalStr(timeDate, beforeDate)) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                return "昨天" + sdf2.format(date);
            } else if ((3600000 * 24) * 2 < spanTime) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm");
                return sdf2.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 判断当前日期是否在某段时间范围内
     *
     * @param starttime yyyy-MM-dd HH:mm
     * @param endtime   yyyy-MM-dd HH:mm
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean compareDate(String starttime, String endtime) {
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date startdate = sdf.parse(starttime);
            Date enddate = sdf.parse(endtime);
            long olddate1 = startdate.getTime();
            long olddate2 = enddate.getTime();
            // 获得系统的当前时间
            long currTime = System.currentTimeMillis();
            if (currTime > olddate1 && currTime < olddate2) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获得当前日期的前一天
     *
     * @return
     * @throws Exception
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSpecifiedDayBefore() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        try {
            Date endDate = dft.parse(dft.format(date.getTime()));
            return dft.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(new Date());
    }

    /**
     * 获得某时间的下一天的0点
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     * @throws Exception
     */
    public static String getSpecifiedDayAfter(String time) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date deletedate = sdf.parse(time);

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        date.setTime(deletedate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
        try {
            Date endDate = dft.parse(dft.format(date.getTime()));
            return sdf.format(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(new Date());
    }


    /**
     * Html-encode the string.
     *
     * @param s the string to be encoded
     * @return the encoded string
     */
    public static String htmlEncode(@NonNull String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;"); //$NON-NLS-1$
                    break;
                case '>':
                    sb.append("&gt;"); //$NON-NLS-1$
                    break;
                case '&':
                    sb.append("&amp;"); //$NON-NLS-1$
                    break;
                case '\'':
                    sb.append("&apos;"); //$NON-NLS-1$
                    break;
                case '"':
                    sb.append("&quot;"); //$NON-NLS-1$
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将特殊字符替换
     *
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
        str = str.replace("&lt;", "<");
        str = str.replace("&gt;", ">");
        str = str.replace("&amp;", "&");
        str = str.replace("&apos;", "\'");
        str = str.replace("&quot;", "\"");
        return str;
    }


    /**
     * 第一个参数是要计算的字符串，第二个参数是字提大
     *
     * @param text
     * @param size
     * @return
     */
    public static float getCharacterWidth(@Nullable String text, float size) {
        if (null == text || "".equals(text)) return 0;
        Paint paint = new Paint();
        paint.setTextSize(size);
        float text_width = paint.measureText(text);//得到总体长度
        return text_width;
    }

}

