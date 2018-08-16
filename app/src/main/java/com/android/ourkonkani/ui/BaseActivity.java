package com.android.ourkonkani.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                handleBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void handleBack() {
        finish();
    }
}
