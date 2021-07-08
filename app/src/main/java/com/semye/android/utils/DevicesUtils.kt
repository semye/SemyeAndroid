package com.semye.android.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Point
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
 * Created by yesheng on 2017/1/17.
 * get info of devices
 */
class DevicesUtils {
    /**
     * 软键盘是否已经打开
     *
     * @return
     */
    protected fun isHardKeyboardOpen(ctx: Context): Boolean {
        return ctx.resources.configuration.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO
    }

    companion object {
        /**
         * 大于等于2.2
         */
        fun hasFroyo(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO
        }

        /**
         * 大于等于2.3
         */
        fun hasGingerbread(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD
        }

        /**
         * 大于等于3.0 LEVEL:11
         */
        fun hasHoneycomb(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
        }

        /**
         * 大于等于3.1
         */
        fun hasHoneycombMR1(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1
        }

        /**
         * 大于等于4.0 14
         */
        fun hasICS(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
        }

        /**
         * 大于等于6.0
         */
        fun hasM(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        val sDKVersionInt: Int
            get() = Build.VERSION.SDK_INT
        val sDKVersion: String
            get() = Build.VERSION.SDK

        /**
         * 判断是否是平板电脑
         *
         * @param context
         * @return
         */
        fun isTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
        }

        fun isHoneycombTablet(context: Context): Boolean {
            return hasHoneycomb() && isTablet(context)
        }

        /**
         * get the screen width
         *
         * @param context context
         * @return screen width
         */
        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
        fun getScreenWidth(context: Context): Int {
            val point = getPoint(context)
            return point.x
        }

        /**
         * get the screen height
         *
         * @param context context
         * @return screen height
         */
        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
        fun getScreenHeight(context: Context): Int {
            val point = getPoint(context)
            return point.y
        }

        /**
         * 获得设备屏幕密度
         */
        fun getScreenDensity(context: Context): Float {
            val metrics = context.applicationContext.resources.displayMetrics
            return metrics.density
        }

        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
        fun getPoint(context: Context): Point {
            val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            val point = Point()
            display.getSize(point)
            return point
        }

        fun getScreenSize(w: Int, h: Int, context: Context): IntArray {
            var phoneW = getScreenWidth(context)
            var phoneH = getScreenHeight(context)
            if (w * phoneH > phoneW * h) {
                phoneH = phoneW * h / w
            } else if (w * phoneH < phoneW * h) {
                phoneW = phoneH * w / h
            }
            return intArrayOf(phoneW, phoneH)
        }

        fun getScreenSize(w: Int, h: Int, phoneW: Int, phoneH: Int): IntArray {
            var phoneW = phoneW
            var phoneH = phoneH
            if (w * phoneH > phoneW * h) {
                phoneH = phoneW * h / w
            } else if (w * phoneH < phoneW * h) {
                phoneW = phoneH * w / h
            }
            return intArrayOf(phoneW, phoneH)
        }

        /**
         * 设置屏幕亮度
         */
        fun setBrightness(context: Activity, f: Float) {
            val lp = context.window.attributes
            lp.screenBrightness = f
            if (lp.screenBrightness > 1.0f) lp.screenBrightness = 1.0f else if (lp.screenBrightness < 0.01f) lp.screenBrightness = 0.01f
            context.window.attributes = lp
        }

        // private static final long NO_STORAGE_ERROR = -1L;
        private const val CANNOT_STAT_ERROR = -2L// if we can't stat the filesystem then we don't know how many
        // free bytes exist. It might be zero but just leave it
        // blank since we really don't know.
        /**
         * 检测磁盘状态
         */
        val availableStorage: Long
            get() = try {
                val storageDirectory = Environment.getExternalStorageDirectory()
                        .toString()
                val stat = StatFs(storageDirectory)
                (stat.availableBlocks.toLong()
                        * stat.blockSize.toLong())
            } catch (ex: RuntimeException) {
                // if we can't stat the filesystem then we don't know how many
                // free bytes exist. It might be zero but just leave it
                // blank since we really don't know.
                CANNOT_STAT_ERROR
            }

        /**
         * 隐藏软键盘
         */
        fun hideSoftInput(ctx: Context, v: View) {
            val imm = ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // 这个方法可以实现输入法在窗口上切换显示，如果输入法在窗口上已经显示，则隐藏，如果隐藏，则显示输入法到窗口上
            imm.hideSoftInputFromWindow(v.applicationWindowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }

        /**
         * 显示软键盘
         */
        fun showSoftInput(ctx: Context) {
            val imm = ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED) // (v,
            // InputMethodManager.SHOW_FORCED);
        }

        /**
         * 获取手机的CPU信息
         *
         * @return info of cpu
         */
        val cpuInfo: String?
            get() {
                var cpuInfo = ""
                try {
                    if (File("/proc/cpuinfo").exists()) {
                        val fr = FileReader("/proc/cpuinfo")
                        val localBufferedReader = BufferedReader(fr,
                                8192)
                        cpuInfo = localBufferedReader.readLine()
                        localBufferedReader.close()
                        if (cpuInfo != null) {
                            cpuInfo = cpuInfo.split(":").toTypedArray()[1].trim { it <= ' ' }.split(" ").toTypedArray()[0]
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return cpuInfo
            }

        fun startApkActivity(ctx: Context, packageName: String) {
            val pm = ctx.packageManager
            val pi: PackageInfo
            try {
                pi = pm.getPackageInfo(packageName, 0)
                val intent = Intent(Intent.ACTION_MAIN, null)
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                intent.setPackage(pi.packageName)
                val apps = pm.queryIntentActivities(intent, 0)
                val ri = apps.iterator().next()
                if (ri != null) {
                    val className = ri.activityInfo.name
                    intent.component = ComponentName(packageName, className)
                    ctx.startActivity(intent)
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

        /**
         * get the mobilephone IMEI
         *
         * @param context context
         * @return IMEI
         */
        @SuppressLint("MissingPermission", "HardwareIds")
        fun getPhoneIMEI(context: Context): String {
            //需要权限 android.permission.READ_PHONE_STATE
            val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return tm.deviceId
        }

        /**
         * get the android unique id
         *
         * @param context context
         * @return SSAID
         */
        fun getSSAID(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        /**
         * get the mobilephone system version
         *
         * @return android sdk version
         */
        val androidVersion: Int
            get() = Build.VERSION.SDK_INT

        /**
         * get the mmobilephone model
         *
         * @return mobilephone model
         */
        val mobilePhoneInfo: String
            get() = Build.MODEL

        /**
         * get the mobilephone brand
         *
         * @return brand
         */
        val mobilePhoneBrand: String
            get() = Build.BRAND
        const val TAG = "getEmuiVersion"
        val emuiVersion: String
            get() {
                val emuiVerion = ""
                val clsArray = arrayOf<Class<*>>(String::class.java)
                val objArray = arrayOf<Any>("ro.build.version.emui")
                try {
                    @SuppressLint("PrivateApi") val SystemPropertiesClass = Class.forName("android.os.SystemProperties")
                    val get = SystemPropertiesClass.getDeclaredMethod("get",
                            *clsArray)
                    val version = get.invoke(SystemPropertiesClass,
                            *objArray) as String
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
    }
}