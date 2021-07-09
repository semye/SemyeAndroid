package com.semye.android.utils

import android.content.Context
import android.widget.DatePicker
import android.widget.TimePicker
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yesheng on 2017/5/7.
 */
class DatatimeUtils {
    var calendar: Calendar? = null
    var simpleDateFormat: SimpleDateFormat? = null
    fun changeToTime(time: String): String {
        var returnTime = ""
        val tempTime = time.substring(time.length - 1)
        simpleDateFormat = if (tempTime == "分") {
            SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA)
        } else {
            SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
        }
        try {
            val timeDate = simpleDateFormat!!.parse(time)
            calendar = Calendar.getInstance()
            calendar?.setTime(timeDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        returnTime = (calendar!![Calendar.YEAR].toString() + "年"
                + (calendar!![Calendar.MONTH] + 1) + "月"
                + calendar!![Calendar.DAY_OF_MONTH] + "日 "
                + calendar!![Calendar.HOUR_OF_DAY] + "时"
                + calendar!![Calendar.MINUTE] + "分")
        return returnTime
    }

    fun changeToColon(time: String): String {
        var returnTime = ""
        val tempTime = time.substring(time.length - 1)
        simpleDateFormat = if (tempTime == "分") {
            SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA)
        } else {
            SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
        }
        try {
            val timeDate = simpleDateFormat!!.parse(time)
            calendar = Calendar.getInstance()
            calendar?.setTime(timeDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        returnTime = (calendar!![Calendar.YEAR].toString() + "年"
                + (calendar!![Calendar.MONTH] + 1) + "月"
                + calendar!![Calendar.DAY_OF_MONTH] + "日 "
                + calendar!![Calendar.HOUR_OF_DAY] + ":"
                + calendar!![Calendar.MINUTE])
        return returnTime
    }

    companion object {
        val currentDataTime: String
            get() = getCurrentDataTime("yyyy-MM-dd HH:mm:ss")

        fun getCurrentDataTime(format: String?): String {
            val df = SimpleDateFormat(format, Locale.CHINA)
            return df.format(Date())
        }

        /**
         * @param time1
         * @param time2
         * @return
         */
        fun compareTime(time1: String?, time2: String?): Boolean {
            val flag: Boolean
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            flag = try {
                val timeDateold = sdf.parse(time1)
                val timeDateControl = sdf.parse(time2)
                timeDateold.after(timeDateControl)
            } catch (e: ParseException) {
                return false
            }
            return flag
        }

        fun addHour(text: String?, chagetime: Int): String {
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            val calendar = Calendar.getInstance()
            try {
                val date = sdf.parse(text)
                calendar.time = date
                calendar.add(Calendar.HOUR, chagetime)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return (calendar[Calendar.YEAR].toString() + "年"
                    + (calendar[Calendar.MONTH] + 1) + "月"
                    + calendar[Calendar.DAY_OF_MONTH] + "日 "
                    + calendar[Calendar.HOUR_OF_DAY] + ":"
                    + DecimalFormat("00").format(calendar[Calendar.MINUTE].toLong()))
        }

        fun addMinute(timeString: String?, minute: Int): String {
            try {
                val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
                val timeDate = sdf.parse(timeString)
                val calendar = Calendar.getInstance()
                calendar.time = timeDate
                calendar.add(Calendar.MINUTE, minute)
                return sdf.format(calendar.time)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        }

        /**
         * @param calendar
         * @param datePicker
         * @param timePicker
         * @param dateTime   绑定dialog的时间
         */
        fun BindDateTime(calendar: Calendar, datePicker: DatePicker,
                         timePicker: TimePicker, dateTime: String?) {
            var calendar = calendar
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            try {
                val timeDate = sdf.parse(dateTime)
                calendar = Calendar.getInstance()
                calendar.time = timeDate
                datePicker.updateDate(calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH])
                timePicker.currentHour = calendar[Calendar.HOUR_OF_DAY]
                timePicker.currentMinute = calendar[Calendar.MINUTE]
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        fun ChangeTime(calendar: Calendar, datePicker: DatePicker,
                       timePicker: TimePicker, addtime: Int): String {
            var calendar = calendar
            var `val`: String
            `val` = getCurrentTime(calendar, datePicker, timePicker)
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            try {
                val timeDate = sdf.parse(`val`)
                calendar = Calendar.getInstance()
                calendar.time = timeDate
                calendar.add(Calendar.MINUTE, addtime)
                datePicker.updateDate(calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH])
                timePicker.currentHour = calendar[Calendar.HOUR_OF_DAY]
                timePicker.currentMinute = calendar[Calendar.MINUTE]
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            `val` = getCurrentTime(calendar, datePicker, timePicker)
            return `val`
        }

        /**
         * @param calendar
         * @param datePicker
         * @param timePicker
         * @return 获得当前的年月日时间
         */
        fun getCurrentTime(calendar: Calendar?, datePicker: DatePicker,
                           timePicker: TimePicker): String {
            return (datePicker.year.toString() + "年" + (datePicker.month + 1)
                    + "月" + datePicker.dayOfMonth + "日 "
                    + timePicker.currentHour + ":"
                    + DecimalFormat("00").format(timePicker.currentMinute))
        }

        /**
         * @param calendar
         * @param datePicker
         * @param timePicker
         * @param dateTime   绑定dialog的时间
         */
        fun bindDateTime(calendar: Calendar, datePicker: DatePicker,
                         timePicker: TimePicker, dateTime: String?) {
            var calendar = calendar
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            try {
                val timeDate = sdf.parse(dateTime)
                calendar = Calendar.getInstance()
                calendar.time = timeDate
                datePicker.updateDate(calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH])
                timePicker.currentHour = calendar[Calendar.HOUR_OF_DAY]
                timePicker.currentMinute = calendar[Calendar.MINUTE]
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        fun changeTime(calendar: Calendar, datePicker: DatePicker,
                       timePicker: TimePicker, addtime: Int): String {
            var calendar = calendar
            var `val`: String
            `val` = getCurrentTime(calendar, datePicker, timePicker)
            val sdf = SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA)
            try {
                val timeDate = sdf.parse(`val`)
                calendar = Calendar.getInstance()
                calendar.time = timeDate
                calendar.add(Calendar.MINUTE, addtime)
                datePicker.updateDate(calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH])
                timePicker.currentHour = calendar[Calendar.HOUR_OF_DAY]
                timePicker.currentMinute = calendar[Calendar.MINUTE]
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            `val` = getCurrentTime(calendar, datePicker, timePicker)
            return `val`
        }

        /**
         * @param timeString time
         * @return 格式化时间为HH-mm
         */
        fun formatTime(timeString: String?): String {
            var time = ""
            val sdf = SimpleDateFormat("HH:mm", Locale.CHINA)
            val sdf2 = SimpleDateFormat("HH-mm", Locale.CHINA)
            try {
                val date = sdf.parse(timeString)
                time = sdf2.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return time
        }

        /**
         * @param timeString
         * @return 格式化日期为yyyy-MM-dd格式
         */
        fun formatDate(timeString: String?): String {
            var dates = ""
            val sdf = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            val sdf2 = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            try {
                val date = sdf.parse(timeString)
                dates = sdf2.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return dates
        }

        /**
         * @param timeString time
         * @return 格式化日期为yyyy年MM月dd日
         */
        fun formatDate2(timeString: String?): String {
            var time = ""
            val sdf = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            try {
                val date = sdf.parse(timeString)
                time = sdf.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return time
        }

        /**
         * 获得明天的日期
         *
         * @param date 今天的日期
         * @return 明天的日期
         */
        fun getTomorrowDate(date: Date?): String {
            var date = date
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.add(Calendar.DATE, 1) //把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.time //这个时间就是日期往后推一天的结果
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            return formatter.format(date)
        }

        /**
         * 保存明天的日期
         *
         * @param context context
         */
        fun saveNewDate(context: Context?) {
            val tomorrow = getTomorrowDate(Date())
            //        SharedPreferencesUtil.saveDateTime(context, tomorrow);
        }
    }
}