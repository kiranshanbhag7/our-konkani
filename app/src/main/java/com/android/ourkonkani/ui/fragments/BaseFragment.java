package com.android.ourkonkani.ui.fragments;


import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    protected Map<String, String> mAnalyticsParams = new HashMap<>();
    protected Unbinder mUnbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null) {
            mUnbinder.unbind();
        }

    }
}
