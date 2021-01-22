package com.semye.base.json.gson;

import com.google.gson.*;
import com.semye.base.json.model.AModel;
import com.semye.base.json.model.AbsFormModel;
import com.semye.base.json.model.AbsModel;
import com.semye.base.json.model.BModel;

import java.lang.reflect.Type;

public class AbsModelTypeAdapter implements JsonDeserializer<AbsModel> {
    private Gson gson;

    public AbsModelTypeAdapter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(AbsFormModel.class, new AbsFormModelTypeAdapter())
                .create();
    }

    @Override
    public AbsModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        if (type.equals("a")) {
            return gson.fromJson(json, AModel.class);
        } else if (type.equals("b")) {
            return gson.fromJson(json, BModel.class);
        }
        return null;
    }
}
