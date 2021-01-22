package com.semye.base.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.semye.base.json.model.AModel;
import com.semye.base.json.model.BModel;

import java.lang.reflect.Type;

public class ComponentDeserializer implements ObjectDeserializer {

    public final static ComponentDeserializer instance = new ComponentDeserializer();

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Object object = parser.parse(fieldName);
        if (object instanceof JSONObject) {
            String strType = ((JSONObject) object).getString("type");
            if (strType.equals("a")) {
                return (T) JSON.parseObject(((JSONObject) object).toJSONString(), AModel.class);
            } else if (strType.equals("b")) {
                return (T) JSON.parseObject(((JSONObject) object).toJSONString(), BModel.class);
            }
        }
        return (T) parser.parse(fieldName);
    }
}
