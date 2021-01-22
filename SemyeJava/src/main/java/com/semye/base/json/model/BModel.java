package com.semye.base.json.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BModel extends AbsModel {

    @JSONField(name = "field_b")
    @SerializedName(value = "field_b")
    public String fieldB;

    @JSONField(name = "items")
    @SerializedName(value = "items")
    public List<AbsFormModel> items;
}
