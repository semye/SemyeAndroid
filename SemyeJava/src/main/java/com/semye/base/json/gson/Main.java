package com.semye.base.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.semye.base.json.model.AbsModel;

import java.util.ArrayList;

/**
 * gson 解析多层多态数据
 */
public class Main {

    public static void main(String[] args) {
        String json = "[\n" +
                "    {\n" +
                "        \"type\": \"a\",\n" +
                "        \"field_a\": \"model a\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"type\": \"b\",\n" +
                "        \"field_b\": \"model b\",\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"type\": \"1\",\n" +
                "                \"form_a\": \"a\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\": \"2\",\n" +
                "                \"form_b\": \"b\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AbsModel.class, new AbsModelTypeAdapter())
                .create();

        ArrayList<AbsModel> list = gson.fromJson(json, new TypeToken<ArrayList<AbsModel>>() {
        }.getType());
        System.out.println(list.toString());
    }
}
