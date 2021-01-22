package com.semye.base.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.semye.base.json.model.AModel;
import com.semye.base.json.model.AbsFormModel;
import com.semye.base.json.model.AbsModel;
import com.semye.base.json.model.BModel;

import java.util.ArrayList;
import java.util.List;

/**
 * fastjson 解析多层多态数据
 */
public class Main {

    static {
        ParserConfig.getGlobalInstance().putDeserializer(AbsModel.class, ComponentDeserializer.instance);
        ParserConfig.getGlobalInstance().putDeserializer(AbsFormModel.class, FormDeserializer.instance);
    }

    public static void main(String[] args) {
        String json = "[{\n" +
                "        \"type\": \"a\",\n" +
                "        \"field_a\": \"model a\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"type\": \"b\",\n" +
                "        \"field_b\": \"model b\",\n" +
                "        \"items\": [{\n" +
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


        List<AbsModel> list = JSON.parseArray(json, AbsModel.class);
        System.out.println(list.toString());
    }
}
