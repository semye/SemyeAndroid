package com.semye.base.json.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public abstract class AbsFormModel {

    @JSONField(name = "type")
    @SerializedName(value = "type")
    public String type = "";


}
