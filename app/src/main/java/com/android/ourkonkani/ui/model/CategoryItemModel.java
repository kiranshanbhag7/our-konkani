package com.android.ourkonkani.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryItemModel {
    @SerializedName("title")
    public String title;

    @SerializedName("name")
    public String name;

    @SerializedName("key")
    public String key;

    @SerializedName("translation")
    public String translation;

    @SerializedName("id")
    public Integer id;

    @SerializedName("list")
    public List<CategoryItemModel> list;
}
