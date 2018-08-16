package com.android.ourkonkani;

import android.text.TextUtils;
import android.util.Log;

import com.android.ourkonkani.ui.model.CategoryItemModel;
import com.android.ourkonkani.ui.model.CategoryModel;
import com.android.ourkonkani.utils.OKUtils;
import com.google.gson.Gson;

import java.util.List;

public class CategoryManager {

    private static CategoryManager kCategoryManager;
    private CategoryModel mCategoryModel;
    private List<CategoryItemModel> mCategoryList;
    private CategoryItemModel mSelectedSectionInfo;

    private CategoryManager() {
        readCategoriesJSONFromAssets();
    }

    public static CategoryManager getInstance() {
        if (kCategoryManager == null) {
            synchronized (CategoryManager.class) {
                if (kCategoryManager == null) {
                    kCategoryManager = new CategoryManager();
                }
            }
        }
        return kCategoryManager;
    }

    private void readCategoriesJSONFromAssets() {
        String fileContents = OKUtils.loadJSONFromAsset(OKApplication.getAppContext());
        try {
            if (!TextUtils.isEmpty(fileContents)) {
                mCategoryModel = new Gson().fromJson(fileContents, CategoryModel.class);
                parseSections();
            }
        } catch (Exception e) {
            Log.i("OKApplication", "Exception while parsing json. Error: " + e);
        }
    }

    private void parseSections() {
        if (mCategoryModel != null && mCategoryModel.data != null && !mCategoryModel.data.isEmpty()) {
            CategoryItemModel sectionModel = mCategoryModel.data.get(0);
            mCategoryList = sectionModel != null ? sectionModel.list : null;
        }
    }

    public CategoryModel getCategoryModel() {
        return mCategoryModel;
    }

    public List<CategoryItemModel> getAllSections() {
        return mCategoryList;
    }

    public void setSelectedSectionInfo(CategoryItemModel selectedSectionInfo) {
        mSelectedSectionInfo = selectedSectionInfo;
    }

    public CategoryItemModel getSelectedSectionInfo() {
        return mSelectedSectionInfo;
    }
}
