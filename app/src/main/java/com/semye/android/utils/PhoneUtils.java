package com.semye.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import androidx.annotation.NonNull;

public class PhoneUtils {



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param context （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(@NonNull Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


    /**
     * 判断EMUI版本是否大于等于3.0
     *
     * @return 如果大于3.0返回true 否则返回false
     */
    public static boolean isOverEmui3() {
        String version = PhoneUtils.getEmuiVersion();
        if (!TextUtils.isEmpty(version)) {
            String[] array = version.split("_");
            if (array.length >= 2) {
                try {
                    return isOver3(array[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断华为手机的版本号是否大于3.0,这里有两种情况
     * 第一种情况是版本号为3个数时，比如5.0.1 将这个版本号转为501，然后与300比较大小
     * 另一种情况是版本号为2个数是，比如4.1 将这个版本后转换成41在末尾添加0，然后再与300比较大小
     *
     * @param number 截取到版本号
     * @return 是否大于3.0
     */
    public static boolean isOver3(String number) {
        String versioncode;
        int flag = number.split("\\.").length;
        if (flag == 2) {
            versioncode = number.replace(".", "") + "0";
        } else if (flag == 3) {
            versioncode = number.replace(".", "");
        } else {
            return false;
        }
        return Integer.valueOf(versioncode) >= 300;
    }


    public static final String TAG = "HUAWEI";

    /**
     * @return 只要返回不是""，则是EMUI版本
     */
    @NonNull
    private static String getEmuiVersion() {
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


//=================================判断手机标识==========================================/

    //小米标识
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    //华为标识
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    //魅族标识
    private static final String KEY_FLYME_ID_FALG_KEY = "ro.build.display.id";
    private static final String KEY_FLYME_ID_FALG_VALUE_KEYWORD = "Flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";

    //vivo标识
    private static final String KEY_VIVO_VERSION = "ro.vivo.os.version";

    //oppo标识
    private static final String KEY_OPPO_VERSION_OPPOROM = "ro.build.version.opporom";

    //乐视手机的标识
    private static final String KEY_LETV_PRODUCT_MANUFACTURER = "ro.product.manufacturer";


    /**
     * 判断是否是小米手机
     *
     * @return true 是小米手机  false 不是小米手机
     */
    public static boolean isMIUI() {
        String[] systemProperty = new String[]{KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE};
        return checkProperty(systemProperty);
    }

    /**
     * 判断是否为华为系统
     *
     * @return true 是华为手机  false 不是华为手机
     */
    public static boolean isEMUI() {
        String[] systemProperty = new String[]{KEY_EMUI_VERSION_CODE, KEY_EMUI_API_LEVEL, KEY_EMUI_CONFIG_HW_SYS_VERSION};
        return checkProperty(systemProperty);
    }

    /**
     * 判断是否为魅族系统
     *
     * @return
     */
    public static boolean isFlyme() {
        String[] systemProperty = new String[]{KEY_FLYME_ID_FALG_VALUE_KEYWORD, KEY_FLYME_ICON_FALG, KEY_FLYME_SETUP_FALG, KEY_FLYME_PUBLISH_FALG};
        return checkProperty(systemProperty);
    }

    /**
     * 判断是否是vivo手机
     *
     * @return
     */
    public static boolean isFuntouchOS() {
        String[] systemProperty = new String[]{KEY_VIVO_VERSION};
        return checkProperty(systemProperty);
    }

    /**
     * 判断是否是oppo手机
     *
     * @return
     */
    public static boolean isColorOS() {
        String[] systemProperty = new String[]{KEY_OPPO_VERSION_OPPOROM};
        return checkProperty(systemProperty);
    }

    /**
     * 判断是否是乐视手机
     *
     * @return
     */
    public static boolean isLeshi() {
        String[] systemProperty = new String[]{KEY_LETV_PRODUCT_MANUFACTURER};
        return checkProperty(systemProperty);
    }

    /**
     * 检查build.pro
     *
     * @param systemProperty
     * @return
     */
    private static boolean checkProperty(@NonNull String[] systemProperty) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            for (String string : systemProperty) {
                if (!TextUtils.isEmpty(getSystemProperty(string))) {
                    return true;
                }
            }
            return false;
        } else {
            File file = new File(Environment.getRootDirectory(), "build.prop");
            if (file.exists()) {
                FileInputStream is = null;
                try {
                    is = new FileInputStream(file);
                    Properties prop = new Properties();
                    prop.load(is);

                    for (String string : systemProperty) {
                        if (!TextUtils.isEmpty(prop.getProperty(string))) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            // ignore all exception
                        }

                    }
                }
            }
        }
        return false;
    }

    private static String getSystemProperty(String key) {
        try {
            @SuppressLint("PrivateApi")
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class);
            return (String) get.invoke(clz, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
