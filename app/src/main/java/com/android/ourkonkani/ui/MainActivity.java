package com.android.ourkonkani.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.android.ourkonkani.R;
import com.android.ourkonkani.ui.fragments.AboutFragment;
import com.android.ourkonkani.ui.fragments.BaseFragment;
import com.android.ourkonkani.ui.fragments.HomeFragment;
import com.android.ourkonkani.ui.fragments.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;

    private HomeFragment mHomeFragment;
    private SettingsFragment mSettingsFragment;
    private AboutFragment mAboutFragment;
    private BaseFragment mCurrentFragment;
    private BaseFragment mDefaultFragment;
    private int mDefaultNavItemSelectionId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mHomeFragment = HomeFragment.newInstance();
        mSettingsFragment = SettingsFragment.newInstance();
        mAboutFragment = AboutFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDefaultView();
    }

    private void setDefaultView() {
        // Setting Home screen as default
        mDefaultFragment = mHomeFragment;
        mDefaultNavItemSelectionId = R.id.navigation_home;

        mNavigationView.setSelectedItemId(mDefaultNavItemSelectionId);
        mCurrentFragment = mDefaultFragment;
        switchFragment();
        setActionBarTitle(mDefaultNavItemSelectionId);
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment != mDefaultFragment) {
            mCurrentFragment = mDefaultFragment;
            if (mNavigationView != null) {
                mNavigationView.setSelectedItemId(mDefaultNavItemSelectionId);
            }
            switchFragment();
            setActionBarTitle(mDefaultNavItemSelectionId);
        } else {
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mCurrentFragment = mHomeFragment;
                    break;
                case R.id.navigation_settings:
                    mCurrentFragment = mSettingsFragment;
                    break;
                case R.id.navigation_about:
                    mCurrentFragment = mAboutFragment;
                    break;
            }
            if (mCurrentFragment != null) {
                switchFragment();
                setActionBarTitle(item.getItemId());
                return true;
            }
            return false;
        }
    };

    private void switchFragment() {
        if (mCurrentFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, mCurrentFragment);
            if (!isFinishing()) {
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

    private void setActionBarTitle(int itemId) {
        String title = getString(R.string.app_name);
        switch(itemId) {
            case R.id.navigation_settings:
                title = getString(R.string.title_settings);
                break;
            case R.id.navigation_about:
                title = getString(R.string.title_about);
                break;
            case R.id.navigation_home:
            default:
                break;
        }
        setTitle(title);
    }
}
