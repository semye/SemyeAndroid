package com.semye.android.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by yesheng on 2017/5/7.
 */
public class JsonUtils {

    @Nullable
    public static String getJsonValueByName(@Nullable String jsonStr, @Nullable String name) {
        if (TextUtils.isEmpty(jsonStr) || TextUtils.isEmpty(name)) {
            return null;
        }
        String str = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (jsonObject.has(name))
                str = jsonObject.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    @Nullable
    public static String convertToString(Object object) {
        Gson gson = new Gson();
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static <T> T convertToObject(String gsonString, @NonNull Class<T> cls) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(gsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public static <T> T convertToObject(@NonNull Gson gson, String gsonString, @NonNull Class<T> cls) {
        try {
            return gson.fromJson(gsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Nullable
    public static <T> List<T> convertToObjectList(String gsonString, Class<T> clazz) {
        Gson gson = new Gson();
        try {
            List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonString
     * @param cls
     * @param <T>
     * @return
     */
    @NonNull
    public static <T> List<T> getObjectList(@NonNull String jsonString, @NonNull Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            JsonArray jsonArray = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static <T> List<T> newchangeGsonToList(String gsonString, @NonNull Class<T[]> clazz) {
        Gson gson = new Gson();
        try {
            T[] arr = gson.fromJson(gsonString, clazz);
            return Arrays.asList(arr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        try {
            list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
            }.getType());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        try {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object json2Object(String jsonStr, @NonNull Class<T> className) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, className);
    }

    public static <T> ArrayList<T> json2List(String jsonStr, Class<T> t) {
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, listType);
    }

    @Nullable
    public static String getJsonStr(String JsonStr, String name) {
        String str = null;
        try {
            JSONObject jsonObject = new JSONObject(JsonStr);
            str = jsonObject.getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }
}
