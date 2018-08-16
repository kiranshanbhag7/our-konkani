package com.android.ourkonkani.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {
    @SerializedName("data")
    public List<CategoryItemModel> data;
}
