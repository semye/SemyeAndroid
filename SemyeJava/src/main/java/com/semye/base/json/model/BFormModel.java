package com.semye.base.json.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class BFormModel extends AbsFormModel {

    @JSONField(name = "form_b")
    @SerializedName(value = "form_b")
    public String formB;
}
