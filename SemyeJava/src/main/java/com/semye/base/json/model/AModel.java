package com.semye.base.json.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class AModel extends AbsModel {

    @JSONField(name = "field_a")
    @SerializedName(value = "field_a")
    public String fieldA;
}
