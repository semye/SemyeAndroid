package com.semye.base.json.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

public class AFormModel extends AbsFormModel {

    @JSONField(name = "form_a")
    @SerializedName(value = "form_a")
    public String formA;
}
