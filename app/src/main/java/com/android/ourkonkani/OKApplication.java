package com.android.ourkonkani;

import android.app.Application;
import android.content.Context;

import com.android.ourkonkani.constants.Constants;

public class OKApplication extends Application {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        /* Reads json file from assets */
        CategoryManager.getInstance();
    }

    public static boolean isProdFlavor() {
        return Constants.FLAVOR_PRODUCTION.equalsIgnoreCase(BuildConfig.FLAVOR);
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
