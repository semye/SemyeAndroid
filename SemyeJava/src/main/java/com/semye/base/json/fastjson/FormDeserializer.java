package com.semye.base.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.semye.base.json.model.AFormModel;
import com.semye.base.json.model.BFormModel;

import java.lang.reflect.Type;

public class FormDeserializer implements ObjectDeserializer {

    public final static FormDeserializer instance = new FormDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (object instanceof JSONObject) {
            String strType = ((JSONObject) object).getString("type");
            if (strType.equals("1")) {
                return (T) JSON.parseObject(((JSONObject) object).toJSONString(), AFormModel.class);
            } else if (strType.equals("2")) {
                return (T) JSON.parseObject(((JSONObject) object).toJSONString(), BFormModel.class);
            }
        }
        return (T) parser.parse(fieldName);
    }
}
