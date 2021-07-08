package com.semye.android.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*

object PhoneUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    fun getStatusBarHeight(context: Context): Int {
        var statusHeight = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = clazz.getField("status_bar_height")[`object`].toString().toInt()
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }

    /**
     * 判断EMUI版本是否大于等于3.0
     *
     * @return 如果大于3.0返回true 否则返回false
     */
    val isOverEmui3: Boolean
        get() {
            val version = emuiVersion
            if (!TextUtils.isEmpty(version)) {
                val array = version.split("_").toTypedArray()
                if (array.size >= 2) {
                    return try {
                        isOver3(array[1])
                    } catch (e: Exception) {
                        e.printStackTrace()
                        false
                    }
                }
            }
            return false
        }

    /**
     * 判断华为手机的版本号是否大于3.0,这里有两种情况
     * 第一种情况是版本号为3个数时，比如5.0.1 将这个版本号转为501，然后与300比较大小
     * 另一种情况是版本号为2个数是，比如4.1 将这个版本后转换成41在末尾添加0，然后再与300比较大小
     *
     * @param number 截取到版本号
     * @return 是否大于3.0
     */
    fun isOver3(number: String): Boolean {
        val versioncode: String
        val flag = number.split("\\.").toTypedArray().size
        versioncode = if (flag == 2) {
            number.replace(".", "") + "0"
        } else if (flag == 3) {
            number.replace(".", "")
        } else {
            return false
        }
        return Integer.valueOf(versioncode) >= 300
    }

    const val TAG = "HUAWEI"

    /**
     * @return 只要返回不是""，则是EMUI版本
     */
    private val emuiVersion: String
        private get() {
            val emuiVerion = ""
            val clsArray = arrayOf<Class<*>>(String::class.java)
            val objArray = arrayOf<Any>("ro.build.version.emui")
            try {
                @SuppressLint("PrivateApi") val SystemPropertiesClass =
                    Class.forName("android.os.SystemProperties")
                val get = SystemPropertiesClass.getDeclaredMethod(
                    "get",
                    *clsArray
                )
                val version = get.invoke(
                    SystemPropertiesClass,
                    *objArray
                ) as String
                Log.d(TAG, "get EMUI version is:$version")
                if (!TextUtils.isEmpty(version)) {
                    return version
                }
            } catch (e: ClassNotFoundException) {
                Log.e(TAG, " getEmuiVersion wrong, ClassNotFoundException")
            } catch (e: LinkageError) {
                Log.e(TAG, " getEmuiVersion wrong, LinkageError")
            } catch (e: NoSuchMethodException) {
                Log.e(TAG, " getEmuiVersion wrong, NoSuchMethodException")
            } catch (e: NullPointerException) {
                Log.e(TAG, " getEmuiVersion wrong, NullPointerException")
            } catch (e: Exception) {
                Log.e(TAG, " getEmuiVersion wrong")
            }
            return emuiVerion
        }

    //=================================判断手机标识==========================================/
    //小米标识
    private const val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

    //华为标识
    private const val KEY_EMUI_VERSION_CODE = "ro.build.version.emui"
    private const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"
    private const val KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion"

    //魅族标识
    private const val KEY_FLYME_ID_FALG_KEY = "ro.build.display.id"
    private const val KEY_FLYME_ID_FALG_VALUE_KEYWORD = "Flyme"
    private const val KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon"
    private const val KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme"
    private const val KEY_FLYME_PUBLISH_FALG = "ro.flyme.published"

    //vivo标识
    private const val KEY_VIVO_VERSION = "ro.vivo.os.version"

    //oppo标识
    private const val KEY_OPPO_VERSION_OPPOROM = "ro.build.version.opporom"

    //乐视手机的标识
    private const val KEY_LETV_PRODUCT_MANUFACTURER = "ro.product.manufacturer"

    /**
     * 判断是否是小米手机
     *
     * @return true 是小米手机  false 不是小米手机
     */
    val isMIUI: Boolean
        get() {
            val systemProperty =
                arrayOf(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE)
            return checkProperty(systemProperty)
        }

    /**
     * 判断是否为华为系统
     *
     * @return true 是华为手机  false 不是华为手机
     */
    val isEMUI: Boolean
        get() {
            val systemProperty =
                arrayOf(KEY_EMUI_VERSION_CODE, KEY_EMUI_API_LEVEL, KEY_EMUI_CONFIG_HW_SYS_VERSION)
            return checkProperty(systemProperty)
        }

    /**
     * 判断是否为魅族系统
     *
     * @return
     */
    val isFlyme: Boolean
        get() {
            val systemProperty = arrayOf(
                KEY_FLYME_ID_FALG_VALUE_KEYWORD,
                KEY_FLYME_ICON_FALG,
                KEY_FLYME_SETUP_FALG,
                KEY_FLYME_PUBLISH_FALG
            )
            return checkProperty(systemProperty)
        }

    /**
     * 判断是否是vivo手机
     *
     * @return
     */
    val isFuntouchOS: Boolean
        get() {
            val systemProperty = arrayOf(KEY_VIVO_VERSION)
            return checkProperty(systemProperty)
        }

    /**
     * 判断是否是oppo手机
     *
     * @return
     */
    val isColorOS: Boolean
        get() {
            val systemProperty = arrayOf(KEY_OPPO_VERSION_OPPOROM)
            return checkProperty(systemProperty)
        }

    /**
     * 判断是否是乐视手机
     *
     * @return
     */
    val isLeshi: Boolean
        get() {
            val systemProperty = arrayOf(KEY_LETV_PRODUCT_MANUFACTURER)
            return checkProperty(systemProperty)
        }

    /**
     * 检查build.pro
     *
     * @param systemProperty
     * @return
     */
    private fun checkProperty(systemProperty: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            for (string in systemProperty) {
                if (!TextUtils.isEmpty(getSystemProperty(string))) {
                    return true
                }
            }
            return false
        } else {
            val file = File(Environment.getRootDirectory(), "build.prop")
            if (file.exists()) {
                var `is`: FileInputStream? = null
                try {
                    `is` = FileInputStream(file)
                    val prop = Properties()
                    prop.load(`is`)
                    for (string in systemProperty) {
                        if (!TextUtils.isEmpty(prop.getProperty(string))) {
                            return true
                        }
                    }
                    return false
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (`is` != null) {
                        try {
                            `is`.close()
                        } catch (e: IOException) {
                            // ignore all exception
                        }
                    }
                }
            }
        }
        return false
    }

    private fun getSystemProperty(key: String): String? {
        try {
            @SuppressLint("PrivateApi") val clz = Class.forName("android.os.SystemProperties")
            val get = clz.getMethod("get", String::class.java)
            return get.invoke(clz, key) as String
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}