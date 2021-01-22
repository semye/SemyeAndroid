package com.semye.base.json.gson;

import com.google.gson.*;
import com.semye.base.json.model.AFormModel;
import com.semye.base.json.model.AbsFormModel;
import com.semye.base.json.model.AbsModel;
import com.semye.base.json.model.BFormModel;

import java.lang.reflect.Type;

public class AbsFormModelTypeAdapter implements JsonDeserializer<AbsFormModel> {

    public AbsFormModelTypeAdapter() {

    }

    @Override
    public AbsFormModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if (type.equals("1")) {
            return new Gson().fromJson(json, AFormModel.class);
        } else if (type.equals("2")) {
            return new Gson().fromJson(json, BFormModel.class);
        }
        return null;
    }
}
