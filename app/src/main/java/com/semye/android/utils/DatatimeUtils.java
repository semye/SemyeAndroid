package com.semye.android.utils;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yesheng on 2017/5/7.
 */
public class DatatimeUtils {


    Calendar calendar;
    SimpleDateFormat simpleDateFormat;


    public static String getCurrentDataTime() {
        return getCurrentDataTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
        return df.format(new Date());
    }


    @NonNull
    public String changeToTime(@NonNull String time) {
        String returnTime = "";
        String tempTime = time.substring(time.length() - 1);
        if (tempTime.equals("分")) {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        }
        try {
            Date timeDate = simpleDateFormat.parse(time);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        returnTime = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                + calendar.get(Calendar.MINUTE) + "分";
        return returnTime;
    }

    @NonNull
    public String changeToColon(@NonNull String time) {
        String returnTime = "";
        String tempTime = time.substring(time.length() - 1);
        if (tempTime.equals("分")) {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA);
        } else {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        }
        try {
            Date timeDate = simpleDateFormat.parse(time);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        returnTime = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE);
        return returnTime;
    }


    /**
     * @param time1
     * @param time2
     * @return
     */
    public static boolean compareTime(String time1, String time2) {
        boolean flag;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        try {
            Date timeDateold = sdf.parse(time1);
            Date timeDateControl = sdf.parse(time2);
            flag = timeDateold.after(timeDateControl);
        } catch (ParseException e) {
            return false;
        }
        return flag;

    }


    public static String addHour(String text, int chagetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(text);
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, chagetime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + new DecimalFormat("00").format(calendar.get(Calendar.MINUTE));
    }

    public static String addMinute(String timeString, int minute) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
            Date timeDate = sdf.parse(timeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            calendar.add(Calendar.MINUTE, minute);
            return sdf.format(calendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param calendar
     * @param datePicker
     * @param timePicker
     * @param dateTime   绑定dialog的时间
     */
    public static void BindDateTime(Calendar calendar, DatePicker datePicker,
                                    TimePicker timePicker, String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);

        try {
            Date timeDate = sdf.parse(dateTime);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            datePicker.updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String ChangeTime(Calendar calendar, @NonNull DatePicker datePicker,
                                    @NonNull TimePicker timePicker, int addtime) {
        String val;
        val = getCurrentTime(calendar, datePicker, timePicker);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        try {
            Date timeDate = sdf.parse(val);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            calendar.add(Calendar.MINUTE, addtime);
            datePicker.updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        val = getCurrentTime(calendar, datePicker, timePicker);
        return val;
    }

    /**
     * @param calendar
     * @param datePicker
     * @param timePicker
     * @return 获得当前的年月日时间
     */
    @NonNull
    public static String getCurrentTime(Calendar calendar, DatePicker datePicker,
                                        TimePicker timePicker) {
        String dateTime = datePicker.getYear() + "年" + (datePicker.getMonth() + 1)
                + "月" + datePicker.getDayOfMonth() + "日 "
                + timePicker.getCurrentHour() + ":"
                + new DecimalFormat("00").format(timePicker.getCurrentMinute());
        return dateTime;

    }

    /**
     * @param calendar
     * @param datePicker
     * @param timePicker
     * @param dateTime   绑定dialog的时间
     */
    public static void bindDateTime(Calendar calendar, DatePicker datePicker,
                                    TimePicker timePicker, String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);

        try {
            Date timeDate = sdf.parse(dateTime);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            datePicker.updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String changeTime(Calendar calendar, @NonNull DatePicker datePicker,
                                    @NonNull TimePicker timePicker, int addtime) {
        String val;
        val = getCurrentTime(calendar, datePicker, timePicker);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        try {
            Date timeDate = sdf.parse(val);
            calendar = Calendar.getInstance();
            calendar.setTime(timeDate);
            calendar.add(Calendar.MINUTE, addtime);
            datePicker.updateDate(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        val = getCurrentTime(calendar, datePicker, timePicker);
        return val;
    }


    /**
     * @param timeString time
     * @return 格式化时间为HH-mm
     */
    public static String formatTime(String timeString) {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH-mm", Locale.CHINA);
        try {
            Date date = sdf.parse(timeString);
            time = sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * @param timeString
     * @return 格式化日期为yyyy-MM-dd格式
     */
    public static String formatDate(String timeString) {
        String dates = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            Date date = sdf.parse(timeString);
            dates = sdf2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;

    }

    /**
     * @param timeString time
     * @return 格式化日期为yyyy年MM月dd日
     */
    public static String formatDate2(String timeString) {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        try {
            Date date = sdf.parse(timeString);
            time = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    /**
     * 获得明天的日期
     *
     * @param date 今天的日期
     * @return 明天的日期
     */
    public static String getTomorrowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return formatter.format(date);
    }


    /**
     * 保存明天的日期
     *
     * @param context context
     */
    public static void saveNewDate(Context context) {
        String tomorrow = getTomorrowDate(new Date());
        //        SharedPreferencesUtil.saveDateTime(context, tomorrow);
    }
}
