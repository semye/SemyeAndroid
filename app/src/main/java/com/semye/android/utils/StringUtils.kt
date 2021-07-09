package com.semye.android.utils

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningTaskInfo
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Html
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Xml
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.xmlpull.v1.XmlPullParser
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.OutputStreamWriter
import java.io.UnsupportedEncodingException
import java.lang.reflect.Field
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

/**
 * String工具类
 */
object StringUtils {
    /**
     * email的正则表达式
     */
    private val email = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")

    /**
     * 手机号的正则表达式
     */
    private val phone = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0
    }

    /**
     * Returns true if the string is numeric
     *
     * @param str string
     * @return
     */
    fun isNumeric(str: String?): Boolean {
        val pattern = Pattern.compile("[0-9]*")
        val isNum = pattern.matcher(str)
        return isNum.matches()
    }

    fun renamePhoto(filename: String): String {
        return getPictureName(filename) + ".png"
    }

    fun getPictureName(filename: String): String {
        return filename.substring(0, filename.indexOf("."))
    }

    /**
     * 生成uuid
     *
     * @return 处理后的uuid
     */
    fun createUUID(): String {
        val `val`: String
        val uuid = UUID.randomUUID()
        `val` = uuid.toString().replace("-", "")
        return `val`
    }

    /**
     * 判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
     */
    private val idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])")
    private val dateFormater: ThreadLocal<SimpleDateFormat> =
        object : ThreadLocal<SimpleDateFormat>() {
            @SuppressLint("SimpleDateFormat")
            override fun initialValue(): SimpleDateFormat {
                return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            }
        }
    private val dateFormater2: ThreadLocal<SimpleDateFormat> =
        object : ThreadLocal<SimpleDateFormat>() {
            @SuppressLint("SimpleDateFormat")
            override fun initialValue(): SimpleDateFormat {
                return SimpleDateFormat("yyyy-MM-dd")
            }
        }

    /**
     * 毫秒值转换为mm:ss
     *
     * @param ms
     */
    fun timeFormat(ms: Int): String {
        var ms = ms
        val time = StringBuilder()
        time.delete(0, time.length)
        ms /= 1000
        val s = ms % 60
        val min = ms / 60
        if (min < 10) {
            time.append(0)
        }
        time.append(min).append(":")
        if (s < 10) {
            time.append(0)
        }
        time.append(s)
        return time.toString()
    }

    /**
     * 将字符串转位日期类型
     *
     * @return
     */
    fun toDate(sdate: String?): Date? {
        return try {
            dateFormater.get().parse(sdate)
        } catch (e: ParseException) {
            null
        }
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input) return true
        for (i in 0 until input.length) {
            val c = input[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    fun isEmail(email: String?): Boolean {
        return !(email == null || email.trim { it <= ' ' }.length == 0) && StringUtils.email.matcher(
            email
        ).matches()
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    fun isPhone(phoneNum: String?): Boolean {
        return if (phoneNum == null || phoneNum.trim { it <= ' ' }.length == 0) false else phone.matcher(
            phoneNum
        ).matches()
    }

    /**
     * 判断是不是一个合法的身份证号
     *
     * @param idnum 身份证号
     * @return 如果是合法的身份证号返回true,
     */
    fun isIDcard(idnum: String?): Boolean {
        return !(idnum == null || idnum.trim { it <= ' ' }.length == 0) && idNumPattern.matcher(
            idnum
        ).matches()
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    fun toInt(str: String, defValue: Int): Int {
        try {
            return str.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return defValue
    }

    /**
     * 对象转整
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toInt(obj: Any?): Int {
        return if (obj == null) 0 else toInt(
            obj.toString(),
            0
        )
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toLong(obj: String): Long {
        try {
            return obj.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * String转double
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toDouble(obj: String): Double {
        try {
            return obj.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0.0
    }

    /**
     * 字符串转布尔
     *
     * @param b
     * @return 转换异常返回 false
     */
    fun toBool(b: String?): Boolean {
        try {
            return java.lang.Boolean.parseBoolean(b)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 判断一个字符串是不是数字
     */
    fun isNumber(str: String): Boolean {
        try {
            str.toInt()
        } catch (e: Exception) {
            return false
        }
        return true
    }

    /**
     * MD5加密
     */
    fun md5(string: String): String {
        val hash: ByteArray
        hash = try {
            MessageDigest.getInstance("MD5").digest(string.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("Huh, MD5 should be supported?", e)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("Huh, UTF-8 should be supported?", e)
        }
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
//            if (b and (0xFF < 0x10)){ hex.append("0")}
//            hex.append(Integer.toHexString((b and 0xFF.toByte()).toInt()))
        }
        return hex.toString()
    }

    /**
     * KJ加密
     */
    fun KJencrypt(str: String): String {
        val cstr = str.toCharArray()
        val hex = StringBuilder()
        for (c in cstr) {
            hex.append((c.toInt() + 5).toChar())
        }
        return hex.toString()
    }

    /**
     * KJ解密
     */
    fun KJdecipher(str: String): String {
        val cstr = str.toCharArray()
        val hex = StringBuilder()
        for (c in cstr) {
            hex.append((c.toInt() - 5).toChar())
        }
        return hex.toString()
    }

    /**
     * 将对象序列化为字符串
     *
     * @param obj
     * @return
     * @throws IOException
     */
    fun getStrFromObj(obj: Any?): String? {
        var serStr: String? = null
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(obj)
            serStr = byteArrayOutputStream.toString("ISO-8859-1")
            serStr = URLEncoder.encode(serStr, "UTF-8")
            objectOutputStream.close()
            byteArrayOutputStream.close()
        } catch (e: IOException) {
            e.toString()
        }
        return serStr
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
    fun getObjFromStr(serStr: String?): Any? {
        var result: Any? = null
        try {
            val redStr = URLDecoder.decode(serStr, "UTF-8")
            val byteArrayInputStream =
                ByteArrayInputStream(redStr.toByteArray(charset("ISO-8859-1")))
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            result = objectInputStream.readObject()
            objectInputStream.close()
            byteArrayInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return result
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
    fun parse(
        `is`: InputStream?,
        clazz: Class<*>,
        fields: List<String>,
        elements: List<String?>,
        itemElement: String
    ): List<Any?> {
        Log.v("rss", "开始解析XML.")
        val list: MutableList<Any?> = ArrayList()
        try {
            val xmlPullParser = Xml.newPullParser()
            xmlPullParser.setInput(`is`, "UTF-8")
            var event = xmlPullParser.eventType
            var obj: Any? = null
            while (event != XmlPullParser.END_DOCUMENT) {
                when (event) {
                    XmlPullParser.START_TAG -> {
                        if (itemElement == xmlPullParser.name) {
                            obj = clazz.newInstance()
                        }
                        if (obj != null && elements.contains(xmlPullParser.name)) {
                            setFieldValue(
                                obj,
                                fields[elements.indexOf(xmlPullParser.name)],
                                xmlPullParser.nextText()
                            )
                        }
                    }
                    XmlPullParser.END_TAG -> if (itemElement == xmlPullParser.name) {
                        list.add(obj)
                        obj = null
                    }
                }
                event = xmlPullParser.next()
            }
        } catch (e: Exception) {
            Log.e("rss", "解析XML异常：" + e.message)
            throw RuntimeException("解析XML异常：" + e.message)
        }
        return list
    }

    /**
     * 设置字段值
     *
     * @param propertyName 字段名
     * @param obj          实例对象
     * @param value        新的字段值
     * @return
     */
    fun setFieldValue(obj: Any, propertyName: String, value: Any?) {
        try {
            val field = obj.javaClass.getDeclaredField(propertyName)
            field.isAccessible = true
            field[obj] = value
        } catch (ex: Exception) {
            throw RuntimeException()
        }
    }

    /**
     * 比较两个String是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    fun equalStr(str1: String?, str2: String?): Boolean {
        return !(null == str1 || null == str2) && str1 == str2
    }

    /**
     * 将流保存到指定文件
     *
     * @param fileName 要保存的文件路径
     * @param in       要写入文件的流
     */
    fun saveToFile(fileName: String?, `in`: InputStream?) {
        var fos: FileOutputStream? = null
        var bis: BufferedInputStream? = null
        val BUFFER_SIZE = 1024
        val buf = ByteArray(BUFFER_SIZE)
        var size = 0
        bis = BufferedInputStream(`in`) // 获取网络输入流
        try {
            fos = FileOutputStream(fileName) // 建立文件
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try { // 保存文件
            while (bis.read(buf).also { size = it } != -1) {
                fos!!.write(buf, 0, size)
            }
            fos!!.close()
            bis.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }









    fun encodeHex(data: ByteArray): String {
        val DIGITS = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
        )
        val l = data.size
        val out = CharArray(l shl 1)

        // two characters form the hex value.
        var i = 0
        var j = 0
        while (i < l) {
            out[j++] = DIGITS[0xF0 and data[i].toInt() ushr 4]
            out[j++] = DIGITS[0x0F and data[i].toInt()]
            i++
        }
        return String(out)
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun toLocalDate(date: Date?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(date)
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 要判断的字符串
     * @return 是否为空
     */
    fun isNull(str: String?): Boolean {
        return str == null || str == ""
    }

    /**
     * Utf8URL编码
     *
     * @param text 文字
     * @return
     */
    @SuppressLint("DefaultLocale")
    fun Utf8URLencode(text: String): String {
        val result = StringBuffer()
        for (i in 0 until text.length) {
            val c = text[i]
            var b = ByteArray(0)
            try {
                b = Character.toString(c).toByteArray(charset("UTF-8"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
            for (j in b.indices) {
                var k = b[j].toInt()
                if (k < 0) k += 256
                result.append("%" + Integer.toHexString(k).toUpperCase())
            }
        }
        return result.toString()
    }

    /**
     * 获得图片
     *
     * @param url      图片地址
     * @param fileName 文件名
     * @return 0 文件存在, 1 正常创建文件, -1 创建文件失败
     * @throws Exception
     */
    fun getImage(url: String?, fileName: String?): Int {
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
        return -1
    }

    /**
     * 像素转换为dip
     *
     * @param scale
     * @param px
     * @return
     */
    fun pxToDip(scale: Float, px: Int): Int {
        return (px / scale + 0.5f).toInt()
    }

    /**
     * dip转换为像素
     *
     * @param scale
     * @param dip
     * @return
     */
    fun dipToPx(scale: Float, dip: Int): Int {
        return (dip * scale + 0.5f).toInt()
    }

    /**
     * 判断是否有SD卡
     *
     * @return
     */
    fun hasSdcard(): Boolean {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            true
        } else false
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(pxValue: Float, fontScale: Float): Int {
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(spValue: Float, fontScale: Float): Int {
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun htmlToContent(str: String): String {
        var str = str
        if (isNull(str)) {
            return str
        }
        str = str.replace("\n".toRegex(), "<br />")
        str = str.replace("\r\r".toRegex(), "<br />")
        val span = Html.fromHtml(str)
        return span.toString()
    }

    /**
     * SD卡路径
     *
     * @return SD卡路径
     */
    val sdPath: String?
        get() = if (hasSdcard()) {
            val file = Environment.getExternalStorageDirectory()
            file.absolutePath
        } else {
            null
        }

    /**
     * 读取ＳＤ卡中文件
     *
     * @return str
     */
    fun getDataFromSD(path: String?): String? {
        var partJson: String? = ""
        if (hasSdcard()) {
            val file = File(path)
            if (file.exists()) {
                try {
                    var input: InputStream? = BufferedInputStream(FileInputStream(file))
                    var reader: BufferedReader? = BufferedReader(InputStreamReader(input))
                    var temp: String?
                    while (reader!!.readLine().also { temp = it } != null) {
                        partJson += temp
                    }
                    reader.close()
                    reader = null
                    input!!.close()
                    input = null
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return partJson
    }

    /**
     * MD5加密
     *
     * @param plainText 要加密的字符串
     * @return 经过MD5加密的字符串
     */
    fun Md5(plainText: String): String? {
        var temp: String? = null
        try {
            val md = MessageDigest.getInstance("MD5")
            md.update(plainText.toByteArray())
            val b = md.digest()
            var i: Int
            val buf = StringBuffer("")
            for (offset in b.indices) {
                i = b[offset].toInt()
                if (i < 0) i += 256
                if (i < 16) buf.append("0")
                buf.append(Integer.toHexString(i))
            }
            temp = buf.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return temp
    }

    /**
     * 去除list中重复数据
     *
     * @param list
     */
    fun removeDuplicate(list: List<String>?): List<String> {
        val h = HashSet(list)
        val mList: MutableList<String> = ArrayList()
        mList.addAll(h)
        return mList
    }

    /**
     * 抛出异常退出程序
     */
    fun exit() {
        Thread.currentThread().uncaughtExceptionHandler =
            Thread.UncaughtExceptionHandler { thread, ex ->
                // 遇到不可抓取的异常会到这里来,就不会弹出对话框了,完美结局
                // 在这里最好让所有的activity全finish了，也另加入关闭进程的方法
            }
        val meIsNull: String? = null
        // 在这里肯定是空指针异常，遇到异常之后，执行上面的回调代码，就不会弹出对话框了
        meIsNull == "空指针"
    }

    /**
     * 计算textview宽度
     *
     * @param tv
     * @return
     */
    fun getTextWidth(tv: TextView?): Float {
        return if (null == tv) 0f else getCharacterWidth(
            tv.text.toString(),
            tv.textSize
        )
    }

    /**
     * 获取手机信息
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    fun phoneInfo(context: Context) {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        // 手机制造商
        val phoneModle = Build.MANUFACTURER
        // 手机型号
        val phoneName = Build.MODEL
        // SDK号
        val sdk = Build.VERSION.SDK_INT
        // android系统版本号
        val release = Build.VERSION.RELEASE
        // 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID.
        // Return null if device ID is not available.
        tm.deviceId
        println(phoneModle)
        println(phoneName)
        println(sdk.toString())
        println(release)
        println(tm.deviceId)
    }

    /**
     * 判断软件是否第一次开启
     */
    fun theFirstEnter(context: Context) {
        var info: PackageInfo? = null
        try {
            info = context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        val currentVersion = info!!.versionCode
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val lastVersion = prefs.getInt("VERSION_KEY", 0)
        if (currentVersion != lastVersion) {
            // saveIcon(context);
            inputSP(context, "FirstEnter", "isFirst", true)
            inputSP(context, "FirstEnter", "FirstPortraitView", true)
            inputSP(context, "FirstEnter", "FirstLandView", true)
            prefs.edit().putInt("VERSION_KEY", currentVersion).commit()
        } else {
            inputSP(context, "FirstEnter", "isFirst", false)
            return
        }
    }

    /**
     * SharedPreferences input
     */
    fun inputSP(mContext: Context, fileName: String?, Key: String?, value: Boolean?) {
        val preferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(Key, value!!)
        editor.commit()
    }

    fun inputSP(mContext: Context, fileName: String?, Key: String?, value: Long) {
        val preferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putLong(Key, value)
        editor.commit()
    }

    /**
     * SharedPreferences get
     */
    fun getSP(mContext: Context?, fileName: String?, Key: String?, value: Boolean?): Boolean {
        return if (mContext != null) {
            val preferences =
                mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            preferences.getBoolean(Key, value!!)
        } else {
            true
        }
    }

    /**
     * 写漫画路径
     */
    fun newFileInSdcard(path: String): File? {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) { // 创建一个文件夹对象，赋值为外部存储器的目录
            val sdcardDir = Environment.getExternalStorageDirectory()
            val mypath = sdcardDir.path + "/" + path
            Log.d("test", "comic path = $mypath")
            val file = File(mypath)
            if (!file.exists()) {
                file.mkdirs()
            }
            file
        } else {
            null
        }
    }

    /**
     * SharedPreferences input
     */
    fun inputSP(mContext: Context, fileName: String?, Key: String?, value: String?) {
        val preferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(Key, value)
        editor.commit()
    }

    /**
     * SharedPreferences get
     */
    fun getSP(mContext: Context?, fileName: String?, Key: String?, value: String?): String? {
        return if (mContext != null) {
            val preferences =
                mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            preferences.getString(Key, value)
        } else {
            null
        }
    }

    /**
     * SharedPreferences get
     */
    fun getSP(mContext: Context?, fileName: String?, Key: String?, value: Long): Long {
        return if (mContext != null) {
            val preferences =
                mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            preferences.getLong(Key, value)
        } else {
            null as Long
        }
    }

    fun isTopApp(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks: List<*>? = activityManager.getRunningTasks(5)
        if (tasks == null || tasks.isEmpty()) {
            return false
        }
        val task = tasks[0] as RunningTaskInfo
        val taskPackageName = task.topActivity!!.packageName
        val taskClassName = task.topActivity!!.className
        // Log.d("uuu","taskClassName = "+ taskClassName);
        // Log.d("uuu","isTop = "+
        // context.getClass().getName().equals(taskClassName));
        Log.d("uuu", "taskPackageName = $taskPackageName")
        Log.d("uuu", "isTop = " + (context.packageName == taskPackageName))
        Log.d("uuu", "context.getPackageName() = " + context.packageName)
        return context.packageName == taskPackageName
    }

    /**
     * 判断桌面是否存在应用的快捷方式
     *
     * @param cx
     * @return
     */
    fun hasShortcut(cx: Context): Boolean {
        var result = false
        // 获取当前应用名称
        var title: String? = null
        try {
            val pm = cx.packageManager
            title = pm.getApplicationLabel(
                pm.getApplicationInfo(
                    cx.packageName,
                    PackageManager.GET_META_DATA
                )
            ).toString()
        } catch (e: Exception) {
        }
        val uriStr: String
        uriStr = if (Build.VERSION.SDK_INT < 8) {
            "content://com.android.launcher.settings/favorites?notify=true"
        } else {
            "content://com.android.launcher2.settings/favorites?notify=true"
        }
        val CONTENT_URI = Uri.parse(uriStr)
        val c = cx.contentResolver.query(CONTENT_URI, null, "title=?", arrayOf(title), null)
        if (c != null && c.count > 0) {
            result = true
        }
        return result
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    fun deleteFile(file: File) {
        if (!file.exists()) {
            Log.d("test", "文件可能不存在")
            return
        } else {
            if (file.isFile) {
                file.delete()
                return
            }
            if (file.isDirectory) {
                val childFile = file.listFiles()
                if (childFile == null || childFile.size == 0) {
                    file.delete()
                    return
                }
                for (f in childFile) {
                    deleteFile(f)
                }
                file.delete()
            }
        }
    }

    private val TAG = StringUtils::class.java.name

    /**
     * 检测是否有网络链接
     *
     * @param context 上下文
     * @return 是否有网络链接
     */
    fun isConnect(context: Context): Boolean {
        try { // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
            val connectivity =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.activeNetworkInfo // 获取网络连接管理的对象
                if (info != null && info.isConnected) {
                    if (info.state == NetworkInfo.State.CONNECTED) { // 判断当前网络是否已经连接
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
        return false
    }





    fun hmacSha1(value: String, key: String): String {
        return try {
            // Get an hmac_sha1 key from the raw key bytes
            val keyBytes = key.toByteArray()
            val signingKey = SecretKeySpec(keyBytes, "HmacSHA1")

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            val mac = Mac.getInstance("HmacSHA1")
            mac.init(signingKey)

            // Compute the hmac on input data bytes
            val rawHmac = mac.doFinal(value.toByteArray())
            Log.i("async", "rawHmac==" + rawHmac.size)
            Base64.encodeToString(rawHmac, Base64.DEFAULT)
            // return Base64.encode(hexBytes);
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * 与小数位精度(四舍五入等)相关的一些常用工具方法.
     *
     *
     *
     *
     *
     *
     * float/double的精度取值方式分为以下几种: <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_UP <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_DOWN <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_CEILING <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_FLOOR <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_HALF_UP<br></br>
     *
     *
     * java.math.BigDecimal.ROUND_HALF_DOWN <br></br>
     *
     *
     * java.math.BigDecimal.ROUND_HALF_EVEN <br></br>
     *
     *
     *
     *
     * 对double数据进行取精度.
     *
     *
     *
     *
     *
     *
     * For example: <br></br>
     *
     *
     * double value = 100.345678; <br></br>
     *
     *
     * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br></br>
     *
     *
     * ret为100.3457 <br></br>
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */
    fun round(value: Double, scale: Int, roundingMode: Int): Double {
        var bd: BigDecimal? = BigDecimal(value)
        bd = bd!!.setScale(scale, roundingMode)
        val d = bd.toDouble()
        bd = null
        return d
    }

    /**
     * 解码并缩小图片尺寸，以减少内存消耗
     *
     * @param filePath
     * @return
     */
    fun decodeFile(filePath: String?): Bitmap? {
        // decodes image and scales it to reduce memory consumption
        val f = File(filePath)
        try {
            // Decode image size
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(FileInputStream(f), null, o)

            // The new size we want to scale to
            val REQUIRED_SIZE = 120

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) scale *= 2

            // Decode with inSampleSize
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            // Log.i(TAG, "scale:" + scale);
            return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
        } catch (e: FileNotFoundException) {
        }
        return null
    }

    /**
     * 将路径中的图片转换为Bitmap
     *
     * @param filePath
     * @return
     */
    fun fileToBitmap(filePath: String?): Bitmap {
        return BitmapFactory.decodeFile(filePath)
    }

    fun computeInitialSampleSize(
        options: BitmapFactory.Options,
        minSideLength: Int,
        maxNumOfPixels: Int
    ): Int {
        val w = options.outWidth.toDouble()
        val h = options.outHeight.toDouble()
        val lowerBound =
            if (maxNumOfPixels == -1) 1 else Math.ceil(Math.sqrt(w * h / maxNumOfPixels))
                .toInt()
        val upperBound = if (minSideLength == -1) 128 else Math.min(
            Math.floor(w / minSideLength),
            Math.floor(h / minSideLength)
        ).toInt()
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound
        }
        return if (maxNumOfPixels == -1 && minSideLength == -1) {
            1
        } else if (minSideLength == -1) {
            lowerBound
        } else {
            upperBound
        }
    }

    fun computeSampleSize(
        options: BitmapFactory.Options,
        minSideLength: Int,
        maxNumOfPixels: Int
    ): Int {
        val initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels)
        var roundedSize: Int
        if (initialSize <= 8) {
            roundedSize = 1
            while (roundedSize < initialSize) {
                roundedSize = roundedSize shl 1
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8
        }
        return roundedSize
    }

    fun makeSuitableDrawable(context: Context?, file: File): Bitmap? {
        try {
            val opts = BitmapFactory.Options()
            opts.inJustDecodeBounds = true
            BitmapFactory.decodeFile(file.absolutePath, opts)
            val rect = Rect(0, 0, opts.outWidth, opts.outHeight)
            opts.inJustDecodeBounds = false
            val fis = FileInputStream(file)
            if (file.length() > 1024 * 500) {
            }
            opts.inSampleSize = 4
            val decodeStream = BitmapFactory.decodeStream(fis, rect, opts)
            fis.close()
            return decodeStream
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun computeSampleSize(options: BitmapFactory.Options, target: Int): Int {
        val w = options.outWidth
        val h = options.outHeight
        val candidateW = w / target
        val candidateH = h / target
        var candidate = Math.max(candidateW, candidateH)
        if (candidate == 0) return 1
        if (candidate > 1) {
            if (w > target && w / candidate < target) candidate -= 1
        }
        if (candidate > 1) {
            if (h > target && h / candidate < target) candidate -= 1
        }
        return candidate
    }

    fun makeSuitableDrawable(context: Context, ResId: Int): Drawable? {
        var opts = BitmapFactory.Options()
        opts.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, ResId, opts)

        // opts.inSampleSize = BitmapUtil.computeSampleSize(opts, -1, 128 *
        // 128)-2;
        try {
            val IMAGE_MAX_SIZE = 500000 // 200k
            var scale = 1
            while (opts.outWidth * opts.outHeight * (1 / Math.pow(
                    scale.toDouble(),
                    2.0
                )) > IMAGE_MAX_SIZE
            ) {
                scale++
            }
            var temp: Bitmap? = null
            if (scale > 1) {
                scale--
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                opts = BitmapFactory.Options()
                opts.inSampleSize = scale
                temp = BitmapFactory.decodeResource(context.resources, ResId, opts)

                // resize to desired dimensions
                val height = temp.height
                val width = temp.width
                val y = Math.sqrt(IMAGE_MAX_SIZE / (width.toDouble() / height))
                val x = y / height * width
                val scaledBitmap = Bitmap.createScaledBitmap(temp, x.toInt(), y.toInt(), true)
                temp.recycle()
                temp = scaledBitmap
            } else {
                temp = BitmapFactory.decodeResource(context.resources, ResId, opts)
            }
            return BitmapDrawable(temp)
        } catch (err: OutOfMemoryError) {
            err.printStackTrace()
        }
        return null
    }

    fun makeSuitableDrawable(context: Context?, pathName: String?): Bitmap? {
        if (pathName == null) {
            throw RuntimeException("路径为空")
        }
        var opts = BitmapFactory.Options()
        opts.inJustDecodeBounds = true
        // BitmapFactory.decodeResource(context.getResources(), ResId, opts);
        BitmapFactory.decodeFile(pathName, opts)
        // opts.inSampleSize = BitmapUtil.computeSampleSize(opts, -1, 128 *
        // 128)-2;
        try {
            val IMAGE_MAX_SIZE = 500000 // 200k
            var scale = 1
            while (opts.outWidth * opts.outHeight * (1 / Math.pow(
                    scale.toDouble(),
                    2.0
                )) > IMAGE_MAX_SIZE
            ) {
                scale++
            }
            var temp: Bitmap? = null
            if (scale > 1) {
                scale--
                // scale to max possible inSampleSize that still yields an image
                // larger than target
                opts = BitmapFactory.Options()
                opts.inSampleSize = scale
                temp = BitmapFactory.decodeFile(pathName, opts)

                // resize to desired dimensions
                val height = temp.height
                val width = temp.width
                val y = Math.sqrt(IMAGE_MAX_SIZE / (width.toDouble() / height))
                val x = y / height * width
                val scaledBitmap = Bitmap.createScaledBitmap(temp, x.toInt(), y.toInt(), true)
                temp.recycle()
                temp = scaledBitmap
            } else {
                temp = BitmapFactory.decodeFile(pathName, opts)
            }
            return temp
        } catch (err: OutOfMemoryError) {
            err.printStackTrace()
        }
        return null
    }

    fun toRoundCorner(bitmap: Bitmap?, pixels: Int): Bitmap? {
        var bitmap = bitmap
        if (bitmap == null) {
            return null
        }
        // 这里经常内存溢出
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        bitmap.recycle() // 内存溢出啊亲
        bitmap = null
        return output
    }

    fun compressBitmap(bitmap: Bitmap?, width: Int): Bitmap? {
        var bitmap = bitmap
        if (bitmap == null) {
            return null
        }
        val output =
            Bitmap.createScaledBitmap(bitmap, width, bitmap.height * width / bitmap.width, true)
        if (bitmap != output) {
            bitmap.recycle()
            bitmap = null
        }
        return output
    }

    fun isErrMsg(jsonString: String): Boolean {
        return jsonString.contains("err_msg")
    }

    fun <T> json2Object(jsonStr: String?, className: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(jsonStr, className)
    }

    fun <T> json2List(jsonStr: String?, t: Class<T>?): ArrayList<T> {
        val listType = object : TypeToken<ArrayList<T>?>() {}.type
        val gson = Gson()
        return gson.fromJson(jsonStr, listType)
    }

    @Throws(IOException::class)
    fun Stream2byte(inputStream: InputStream): ByteArray {
        val baos = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var len = 0
        while (inputStream.read(buffer).also { len = it } > 0) {
            baos.write(buffer, 0, len)
        }
        return baos.toByteArray()
    }

    fun getTwoBitFloatString(src: String): String {
        if (!TextUtils.isEmpty(src)) {
            val pointIndex = src.indexOf("")
            if (pointIndex != -1 && pointIndex + 2 <= src.length - 1) {
                return src.substring(0, pointIndex + 2 + 1)
            }
        }
        return src
    }

    fun showToast(context: Context?, stringId: Int) {
        val msg = Toast.makeText(context, stringId, Toast.LENGTH_SHORT)
        msg.setGravity(Gravity.CENTER, msg.xOffset / 2, msg.xOffset / 2)
        msg.show()
    }

    fun showToast(context: Context?, stringId: Int, type: Int) {
        var type = type
        if (type != Toast.LENGTH_LONG || type != Toast.LENGTH_SHORT) {
            type = Toast.LENGTH_SHORT
        }
        val msg = Toast.makeText(context, stringId, type)
        msg.show()
    }// 检查命令是否执行失败。
    // p.exitValue()==0表示正常结束，1：非正常结束
// 获得命令执行后在控制台的输出信息// 启动另一个进程来执行命令// 返回与当前 Java 应用程序相关的运行时对象
    /**
     * 获取外置SD卡路径
     *
     * @return
     */
    private val sDCardPath: String
        private get() {
            val cmd = "cat /proc/mounts"
            val run = Runtime.getRuntime() // 返回与当前 Java 应用程序相关的运行时对象
            try {
                val p = run.exec(cmd) // 启动另一个进程来执行命令
                val `in` = BufferedInputStream(p.inputStream)
                val inBr = BufferedReader(InputStreamReader(`in`))
                var lineStr: String
                while (inBr.readLine().also { lineStr = it } != null) {
                    // 获得命令执行后在控制台的输出信息
                    Log.i(TAG, lineStr)
                    if (lineStr.contains("sdcard") && lineStr.contains(".android_secure")) {
                        val strArray = lineStr.split(" ").toTypedArray()
                        if (strArray != null && strArray.size >= 5) {
                            return strArray[1].replace("/.android_secure", "")
                        }
                    }
                    if (p.waitFor() != 0 && p.exitValue() == 1) { // 检查命令是否执行失败。
                        // p.exitValue()==0表示正常结束，1：非正常结束
                        Log.e(TAG, "命令执行失败!")
                    }
                }
                inBr.close()
                `in`.close()
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
                return Environment.getExternalStorageDirectory().path
            }
            return Environment.getExternalStorageDirectory().path
        }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        var c: Class<*>? = null
        var obj: Any? = null
        var field: Field? = null
        var x = 0
        var statusBarHeight = 0
        try {
            c = Class.forName("com.android.internal.R\$dimen")
            obj = c.newInstance()
            field = c.getField("status_bar_height")
            x = field[obj].toString().toInt()
            statusBarHeight = context.resources.getDimensionPixelSize(x)
            return statusBarHeight
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusBarHeight
    }

    /**
     * 根据ＵＲＬ从网络下载图片
     *
     * @return bitmap
     */
    fun getBitmap(url: String?): Bitmap? {
        var bitmap: Bitmap? = null
        var data: ByteArray? = null
        try {
            data = readInputStream(URL(url).openStream())
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    /**
     * 将Bitmap图片保存到本地
     *
     * @param bitmap
     * @param mPath
     * @return path
     */
    fun saveBitmaptoSD(bitmap: Bitmap, mPath: String?, ratio: Int): String? {
        var path: String? = null
        val tempfile = File(mPath)
        if (tempfile.exists()) { // 如果文件存在删除重建
            tempfile.delete()
        }
        try {
            tempfile.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var fOut: FileOutputStream? = null
        try {
            fOut = FileOutputStream(tempfile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, ratio, fOut)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            fOut!!.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            path = mPath
            fOut!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return path
    }

    /**
     * 流转换为byte[]
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun readInputStream(inStream: InputStream): ByteArray {
        val outSteam = ByteArrayOutputStream()
        val buffer = ByteArray(4096)
        var len = 0
        while (inStream.read(buffer).also { len = it } != -1) {
            outSteam.write(buffer, 0, len)
        }
        outSteam.close()
        inStream.close()
        return outSteam.toByteArray()
    }

    /**
     * 保存文件到ＳＤ卡，如果文件存在覆盖原来文件
     *
     * @param mPath
     * @param fileName
     * @param partJson
     * @return
     */
    fun saveFileToSD(mPath: String?, fileName: String, partJson: String?) {
        if (TextUtils.isEmpty(partJson)) {
            return
        }
        // 路径
        val path = File(mPath)
        // 文件
        val file = File(path, "$fileName.txt")
        if (path.exists()) { // 路径已存在
            try {
                val writer = OutputStreamWriter(FileOutputStream(file))
                writer.write(partJson)
                writer.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else { // 路径不存在创建路径写入文件
            path.mkdirs()
            try {
                val writer = OutputStreamWriter(FileOutputStream(file))
                writer.write(partJson)
                writer.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 删除文件夹,及其所有文件
     *
     *
     * param folderPath 文件夹完整绝对路径
     *
     * @param folderPath
     */
    fun delFolder(folderPath: String) {
        try {
            delAllFile(folderPath) // 删除完里面所有内容
            var filePath = folderPath
            filePath = filePath
            val myFilePath = File(filePath)
            myFilePath.delete() // 删除空文件夹
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    fun delAllFile(path: String): Boolean {
        var flag = false
        val file = File(path)
        if (!file.exists()) {
            return flag
        }
        if (!file.isDirectory) {
            return flag
        }
        val tempList = file.list()
        var temp: File? = null
        for (i in tempList.indices) {
            temp = if (path.endsWith(File.separator)) {
                File(path + tempList[i])
            } else {
                File(path + File.separator + tempList[i])
            }
            if (temp.isFile) {
                temp.delete()
            }
            if (temp.isDirectory) {
                delAllFile(path + "/" + tempList[i]) // 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]) // 再删除空文件夹
                flag = true
            }
        }
        return flag
    }

    /**
     * 从网络获取json数据
     *
     * @param urlStr url
     * @return
     */
    fun getJsonFromNet(urlStr: java.lang.String?): java.lang.String? {
        var josn: java.lang.String? = null
        var urlConn: HttpURLConnection? = null
        try {
            val url = URL(urlStr.toString())
            urlConn = url.openConnection() as HttpURLConnection
            urlConn!!.connectTimeout = 5 * 1000
            urlConn.connect()
            if (urlConn.responseCode == 200) {
                // 获取返回的数据
                val data = readStream(urlConn.inputStream)
                josn = java.lang.String(data, "UTF-8")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // 关闭连接
            urlConn?.disconnect()
        }
        return josn
    }

    /**
     * 流转换为byte
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun readStream(inputStream: InputStream): ByteArray {
        val buffer = ByteArray(1024)
        var len = -1
        val baos = ByteArrayOutputStream()
        while (inputStream.read(buffer).also { len = it } != -1) {
            baos.write(buffer, 0, len)
        }
        val data = baos.toByteArray()
        inputStream.close()
        baos.close()
        return data
    }

    /**
     * 获得锁屏时间 毫秒
     */
    fun getScreenOffTime(context: Context): Int {
        var screenOffTime = 0
        try {
            screenOffTime =
                Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
        } catch (localException: Exception) {
            //
        }
        return screenOffTime
    }

    /**
     * 设置背光时间 毫秒
     *
     *
     * 需要加入权限　android.permission.WRITE_SETTINGS
     *
     * @param context
     * @param paramInt
     * @return
     */
    fun setScreenOffTime(context: Context, paramInt: Int): Boolean {
        var set = false
        try {
            set = Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                paramInt
            )
        } catch (localException: Exception) {
            localException.printStackTrace()
        }
        return set
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    fun getLoacalBitmap(url: String?): Bitmap? {
        return try {
            val fis = FileInputStream(url)
            BitmapFactory.decodeStream(fis) // /把流转化为Bitmap图片
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    fun readBitMap(url: String?): Bitmap? {
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.RGB_565
        opt.inPurgeable = true
        opt.inInputShareable = true
        var `is`: FileInputStream? = null
        return try {
            `is` = FileInputStream(url)
            BitmapFactory.decodeStream(`is`, null, opt)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    fun compressImage(image: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 70, baos) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 100) {    //循环判断如果压缩后图片是否大于50kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            image.compress(
                Bitmap.CompressFormat.JPEG,
                options,
                baos
            ) //这里压缩options%，把压缩后的数据存放到baos中
            //options -= 10;//每次都减少10
            options = options - options / 10
        }
        val isBm =
            ByteArrayInputStream(baos.toByteArray()) //把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null) //把ByteArrayInputStream数据生成图片
    }

    /**
     * 取得bitmap占用内存大小
     *
     * @param data
     * @return
     */
    @SuppressLint("NewApi")
    fun sizeOf(data: Bitmap): Int {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            data.rowBytes * data.height
        } else {
            data.byteCount
        }
    }

    /**
     * 压缩图片到指定大小
     *
     * @param bitmap
     */
    fun imageZoom(bitmap: Bitmap, size: Double): Bitmap {
        // 图片允许最大空间 单位：KB
        var bitmap = bitmap
        // 将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        // 将字节换成KB
        val mid = (b.size / 1024).toDouble()
        // 判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
        if (mid > size) {
            // 获取bitmap大小 是允许最大大小的多少倍
            val i = mid / size
            // 开始压缩 此处用到平方根 将宽带和高度压缩掉对应的平方根倍
            // （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitmap = zoomImage(
                bitmap,
                bitmap.width / Math.sqrt(i),
                bitmap.height / Math.sqrt(i)
            )
        }
        return bitmap
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage
     * ：源图片资源
     * @param newWidth
     * ：缩放后宽度
     * @param newHeight
     * ：缩放后高度
     * @return
     */
    fun zoomImage(bgimage: Bitmap, newWidth: Double, newHeight: Double): Bitmap {
        // 获取这个图片的宽和高
        val width = bgimage.width.toFloat()
        val height = bgimage.height.toFloat()
        // 创建操作图片用的matrix对象
        val matrix = Matrix()
        // 计算宽高缩放率
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bgimage, 0, 0, width.toInt(), height.toInt(), matrix, true)
    }

    /**
     * 取得当前网络名称
     */
    fun getNetName(context: Context): String {
        val netName: String
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // 获得当前活动的网络信息
        val activeNetInfo = connectivityManager.activeNetworkInfo
        netName = if (activeNetInfo == null) {
            "无网"
        } else {
            activeNetInfo.typeName
        }
        return netName
    }

    /**
     * 判断过去的某时间是否在当天
     *
     * @param time yyyy-MM-dd HH:mm
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun isToday(time: String?): Boolean {
        try {
            // 当前时间毫秒数
            val currTime = System.currentTimeMillis()
            // 某时间的下一天的0点
            val nextTime = getSpecifiedDayAfter(time)
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date = sdf.parse(nextTime)
            val next = date.time
            if (currTime > next) {
                return false
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return true
    }

    @SuppressLint("SimpleDateFormat")
    fun getData(time: String): String {
        // 当前日期
        val format = SimpleDateFormat("yyyy-MM-dd")
        val currDate = format.format(Date())
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date = sdf.parse(time)
            val old = date.time
            val currTime = System.currentTimeMillis()
            val spanTime = currTime - old
            // 传入日期
            val timeFormat = SimpleDateFormat("yyyy-MM-dd")
            val timeDate = timeFormat.format(date)
            //当前日期的前一天
            val beforeDate = specifiedDayBefore
            if (spanTime < 3600000) { // xx分钟前
                return spanTime.div( 60000).toString() + "分钟前"
            } else if (equalStr(currDate, timeDate)) {
                val sdf2 = SimpleDateFormat("HH:mm")
                return "今天 " + sdf2.format(date)
            } else if (equalStr(timeDate, beforeDate)) {
                val sdf2 = SimpleDateFormat("HH:mm")
                return "昨天" + sdf2.format(date)
            } else if (3600000 * 24 * 2 < spanTime) {
                val sdf2 = SimpleDateFormat("MM-dd HH:mm")
                return sdf2.format(date)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time
    }

    /**
     * 判断当前日期是否在某段时间范围内
     *
     * @param starttime yyyy-MM-dd HH:mm
     * @param endtime   yyyy-MM-dd HH:mm
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun compareDate(starttime: String?, endtime: String?): Boolean {
        var flag = false
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
        try {
            val startdate = sdf.parse(starttime)
            val enddate = sdf.parse(endtime)
            val olddate1 = startdate.time
            val olddate2 = enddate.time
            // 获得系统的当前时间
            val currTime = System.currentTimeMillis()
            flag = if (currTime > olddate1 && currTime < olddate2) {
                true
            } else {
                false
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return flag
    }

    /**
     * 获得当前日期的前一天
     *
     * @return
     * @throws Exception
     */
    @get:SuppressLint("SimpleDateFormat")
    val specifiedDayBefore: String
        get() {
            val dft = SimpleDateFormat("yyyy-MM-dd")
            val beginDate = Date()
            val date = Calendar.getInstance()
            date.time = beginDate
            date[Calendar.DATE] = date[Calendar.DATE] - 1
            try {
                val endDate = dft.parse(dft.format(date.time))
                return dft.format(endDate)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return dft.format(Date())
        }

    /**
     * 获得某时间的下一天的0点
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     * @throws Exception
     */
    @Throws(ParseException::class)
    fun getSpecifiedDayAfter(time: String?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val deletedate = sdf.parse(time)
        val dft = SimpleDateFormat("yyyy-MM-dd")
        val date = Calendar.getInstance()
        date.time = deletedate
        date[Calendar.DATE] = date[Calendar.DATE] + 1
        try {
            val endDate = dft.parse(dft.format(date.time))
            return sdf.format(endDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dft.format(Date())
    }

    /**
     * Html-encode the string.
     *
     * @param s the string to be encoded
     * @return the encoded string
     */
    fun htmlEncode(s: String): String {
        if (TextUtils.isEmpty(s)) {
            return ""
        }
        val sb = StringBuilder()
        var c: Char
        for (i in 0 until s.length) {
            c = s[i]
            when (c) {
                '<' -> sb.append("&lt;") //$NON-NLS-1$
                '>' -> sb.append("&gt;") //$NON-NLS-1$
                '&' -> sb.append("&amp;") //$NON-NLS-1$
                '\'' -> sb.append("&apos;") //$NON-NLS-1$
                '"' -> sb.append("&quot;") //$NON-NLS-1$
                else -> sb.append(c)
            }
        }
        return sb.toString()
    }

    /**
     * 将特殊字符替换
     *
     * @param str
     * @return
     */
    fun replaceStr(str: String): String {
        var str = str
        str = str.replace("&lt;", "<")
        str = str.replace("&gt;", ">")
        str = str.replace("&amp;", "&")
        str = str.replace("&apos;", "\'")
        str = str.replace("&quot;", "\"")
        return str
    }

    /**
     * 第一个参数是要计算的字符串，第二个参数是字提大
     *
     * @param text
     * @param size
     * @return
     */
    fun getCharacterWidth(text: String?, size: Float): Float {
        if (null == text || "" == text) return 0f
        val paint = Paint()
        paint.textSize = size
        return paint.measureText(text) //得到总体长度
    }
}