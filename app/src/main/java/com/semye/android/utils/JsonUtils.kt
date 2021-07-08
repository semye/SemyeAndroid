package com.semye.android.utils

import android.text.TextUtils
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Created by yesheng on 2017/5/7.
 */
object JsonUtils {
    fun getJsonValueByName(jsonStr: String?, name: String?): String? {
        if (TextUtils.isEmpty(jsonStr) || TextUtils.isEmpty(name)) {
            return null
        }
        var str: String? = null
        try {
            val jsonObject = JSONObject(jsonStr)
            if (jsonObject.has(name)) str = jsonObject.getString(name)
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
        return str
    }

    fun convertToString(`object`: Any?): String? {
        val gson = Gson()
        try {
            return gson.toJson(`object`)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> convertToObject(gsonString: String?, cls: Class<T>): T? {
        val gson = Gson()
        try {
            return gson.fromJson(gsonString, cls)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> convertToObject(gson: Gson, gsonString: String?, cls: Class<T>): T? {
        try {
            return gson.fromJson(gsonString, cls)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> convertToObjectList(gsonString: String?, clazz: Class<T>?): List<T>? {
        val gson = Gson()
        try {
            return gson.fromJson(gsonString, object : TypeToken<List<T>?>() {}.getType())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T> getObjectList(jsonString: String, cls: Class<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        try {
            val gson = Gson()
            val jsonArray: JsonArray = JsonParser().parse(jsonString).getAsJsonArray()
            for (jsonElement in jsonArray) {
                list.add(gson.fromJson(jsonElement, cls))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun <T> newchangeGsonToList(gsonString: String?, clazz: Class<Array<T>?>): List<T>? {
        val gson = Gson()
        try {
            val arr: Array<T> = gson.fromJson(gsonString, clazz)
            return Arrays.asList(*arr)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> changeGsonToListMaps(
        gsonString: String?
    ): List<Map<String, T>>? {
        var list: List<Map<String, T>>? = null
        val gson = Gson()
        try {
            list = gson.fromJson(
                gsonString,
                object : TypeToken<List<Map<String?, T>?>?>() {}.getType()
            )
            return list
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> changeGsonToMaps(gsonString: String?): Map<String, T>? {
        var map: Map<String, T>? = null
        val gson = Gson()
        try {
            map = gson.fromJson(gsonString, object : TypeToken<Map<String?, T>?>() {}.getType())
            return map
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun <T> json2Object(jsonStr: String?, className: Class<T>): Any {
        val gson = Gson()
        return gson.fromJson(jsonStr, className)
    }

    fun <T> json2List(jsonStr: String?, t: Class<T>?): ArrayList<T> {
        val listType: Type = object : TypeToken<ArrayList<T>?>() {}.getType()
        val gson = Gson()
        return gson.fromJson(jsonStr, listType)
    }

    fun getJsonStr(JsonStr: String?, name: String?): String? {
        var str: String? = null
        try {
            val jsonObject = JSONObject(JsonStr)
            str = jsonObject.getString(name)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return str
    }
}