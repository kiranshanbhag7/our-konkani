package com.android.ourkonkani.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.ourkonkani.CategoryManager;
import com.android.ourkonkani.R;
import com.android.ourkonkani.constants.Constants;
import com.android.ourkonkani.ui.adapter.TranslationsViewAdapter;
import com.android.ourkonkani.ui.interfaces.RVListener;
import com.android.ourkonkani.ui.model.CategoryItemModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends BaseActivity implements RVListener {

    @BindView(R.id.rv_trans_item)
    RecyclerView mTranslationsRV;

    @BindView(R.id.tv_translated_text)
    TextView mTranslatedTextTV;

    private CategoryItemModel mSelectedCategoryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            setTitle(bundle.getString(Constants.EXTRAS_ACTIVITY_TITLE, getString(R.string.app_name)));
        }
        mSelectedCategoryItem = CategoryManager.getInstance().getSelectedSectionInfo();
        if (mSelectedCategoryItem != null) {
            mTranslationsRV.setLayoutManager(new LinearLayoutManager(this));
            mTranslationsRV.setAdapter(new TranslationsViewAdapter(this, this, mSelectedCategoryItem.list));
        }

    }

    @Override
    public void onRVItemClick(View view, Object object, int position) {
        if (object != null && object instanceof CategoryItemModel) {
            CategoryItemModel model = (CategoryItemModel)object;
            if (mTranslatedTextTV != null) {
                mTranslatedTextTV.setText(model.translation);
            }
        }
    }
}
