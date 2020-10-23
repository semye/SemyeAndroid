package com.semye.android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * Created by yesheng on 2017/1/17.
 * get info of devices
 */
public class DevicesUtils {

    /**
     * 大于等于2.2
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * 大于等于2.3
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * 大于等于3.0 LEVEL:11
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 大于等于3.1
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * 大于等于4.0 14
     */
    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }


    /**
     * 大于等于6.0
     */
    public static boolean hasM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    @SuppressWarnings("deprecation")
    public static String getSDKVersion() {
        return Build.VERSION.SDK;
    }

    /**
     * 判断是否是平板电脑
     *
     * @param context
     * @return
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isHoneycombTablet(@NonNull Context context) {
        return hasHoneycomb() && isTablet(context);
    }

    /**
     * get the screen width
     *
     * @param context context
     * @return screen width
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(@NonNull Context context) {
        Point point = getPoint(context);
        return point.x;
    }

    /**
     * get the screen height
     *
     * @param context context
     * @return screen height
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(@NonNull Context context) {
        Point point = getPoint(context);
        return point.y;
    }


    /**
     * 获得设备屏幕密度
     */
    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        return metrics.density;
    }


    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getPoint(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }


    public static int[] getScreenSize(int w, int h, @NonNull Context context) {
        int phoneW = getScreenWidth(context);
        int phoneH = getScreenHeight(context);

        if (w * phoneH > phoneW * h) {
            phoneH = phoneW * h / w;
        } else if (w * phoneH < phoneW * h) {
            phoneW = phoneH * w / h;
        }

        return new int[]{phoneW, phoneH};
    }

    public static int[] getScreenSize(int w, int h, int phoneW, int phoneH) {
        if (w * phoneH > phoneW * h) {
            phoneH = phoneW * h / w;
        } else if (w * phoneH < phoneW * h) {
            phoneW = phoneH * w / h;
        }
        return new int[]{phoneW, phoneH};
    }

    /**
     * 设置屏幕亮度
     */
    public static void setBrightness(final Activity context, float f) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = f;
        if (lp.screenBrightness > 1.0f)
            lp.screenBrightness = 1.0f;
        else if (lp.screenBrightness < 0.01f)
            lp.screenBrightness = 0.01f;
        context.getWindow().setAttributes(lp);
    }

    // private static final long NO_STORAGE_ERROR = -1L;
    private static final long CANNOT_STAT_ERROR = -2L;

    /**
     * 检测磁盘状态
     */
    public static long getAvailableStorage() {
        try {
            String storageDirectory = Environment.getExternalStorageDirectory()
                    .toString();
            StatFs stat = new StatFs(storageDirectory);
            return (long) stat.getAvailableBlocks()
                    * (long) stat.getBlockSize();
        } catch (RuntimeException ex) {
            // if we can't stat the filesystem then we don't know how many
            // free bytes exist. It might be zero but just leave it
            // blank since we really don't know.
            return CANNOT_STAT_ERROR;
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(Context ctx, View v) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // 这个方法可以实现输入法在窗口上切换显示，如果输入法在窗口上已经显示，则隐藏，如果隐藏，则显示输入法到窗口上
        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInput(Context ctx) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);// (v,
        // InputMethodManager.SHOW_FORCED);
    }

    /**
     * 软键盘是否已经打开
     *
     * @return
     */
    protected boolean isHardKeyboardOpen(@NonNull Context ctx) {
        return ctx.getResources().getConfiguration().hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO;
    }

    /**
     * 获取手机的CPU信息
     *
     * @return info of cpu
     */
    public static String getCpuInfo() {
        String cpuInfo = "";
        try {
            if (new File("/proc/cpuinfo").exists()) {
                FileReader fr = new FileReader("/proc/cpuinfo");
                BufferedReader localBufferedReader = new BufferedReader(fr,
                        8192);
                cpuInfo = localBufferedReader.readLine();
                localBufferedReader.close();

                if (cpuInfo != null) {
                    cpuInfo = cpuInfo.split(":")[1].trim().split(" ")[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuInfo;
    }

    public static void startApkActivity(final Context ctx, @NonNull String packageName) {
        PackageManager pm = ctx.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(packageName, 0);
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setPackage(pi.packageName);

            List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String className = ri.activityInfo.name;
                intent.setComponent(new ComponentName(packageName, className));
                ctx.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the mobilephone IMEI
     *
     * @param context context
     * @return IMEI
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getPhoneIMEI(Context context) {
        //需要权限 android.permission.READ_PHONE_STATE
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * get the android unique id
     *
     * @param context context
     * @return SSAID
     */
    public static String getSSAID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    /**
     * get the mobilephone system version
     *
     * @return android sdk version
     */
    public static int getAndroidVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * get the mmobilephone model
     *
     * @return mobilephone model
     */
    public static String getMobilePhoneInfo() {
        return Build.MODEL;
    }

    /**
     * get the mobilephone brand
     *
     * @return brand
     */
    public static String getMobilePhoneBrand() {
        return Build.BRAND;
    }


    public static final String TAG = "getEmuiVersion";


    @NonNull
    public static String getEmuiVersion() {
        String emuiVerion = "";
        Class<?>[] clsArray = new Class<?>[]{String.class};
        Object[] objArray = new Object[]{"ro.build.version.emui"};
        try {
            @SuppressLint("PrivateApi")
            Class<?> SystemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method get = SystemPropertiesClass.getDeclaredMethod("get",
                    clsArray);
            String version = (String) get.invoke(SystemPropertiesClass,
                    objArray);
            Log.d(TAG, "get EMUI version is:" + version);
            if (!TextUtils.isEmpty(version)) {
                return version;
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, " getEmuiVersion wrong, ClassNotFoundException");
        } catch (LinkageError e) {
            Log.e(TAG, " getEmuiVersion wrong, LinkageError");
        } catch (NoSuchMethodException e) {
            Log.e(TAG, " getEmuiVersion wrong, NoSuchMethodException");
        } catch (NullPointerException e) {
            Log.e(TAG, " getEmuiVersion wrong, NullPointerException");
        } catch (Exception e) {
            Log.e(TAG, " getEmuiVersion wrong");
        }
        return emuiVerion;
    }


}
