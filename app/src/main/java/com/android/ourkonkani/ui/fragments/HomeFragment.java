package com.android.ourkonkani.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.ourkonkani.CategoryManager;
import com.android.ourkonkani.R;
import com.android.ourkonkani.constants.Constants;
import com.android.ourkonkani.ui.CategoryDetailActivity;
import com.android.ourkonkani.ui.adapter.CategoriesViewAdapter;
import com.android.ourkonkani.ui.interfaces.RVListener;
import com.android.ourkonkani.ui.model.CategoryItemModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements RVListener {

    @BindView(R.id.rv_categories)
    RecyclerView mCategoriesRV;

    private CategoriesViewAdapter mCategoriesViewAdapter;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        mCategoriesRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mCategoriesViewAdapter = new CategoriesViewAdapter(getActivity(), this,
                CategoryManager.getInstance().getAllSections());
        mCategoriesRV.setAdapter(mCategoriesViewAdapter);
    }

    @Override
    public void onRVItemClick(View view, Object object, int position) {
        if (isAdded() && object != null && object instanceof CategoryItemModel) {
            CategoryItemModel model = (CategoryItemModel)object;
            CategoryManager.getInstance().setSelectedSectionInfo(model);
            Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
            intent.putExtra(Constants.EXTRAS_ACTIVITY_TITLE, model.name);
            startActivity(intent);
        }
    }
}
