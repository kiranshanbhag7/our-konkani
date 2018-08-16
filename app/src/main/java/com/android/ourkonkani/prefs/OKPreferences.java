package com.android.ourkonkani.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class OKPreferences {
    private final String KEY_INSTANT_RUN_INFO = "key_instant_run_info";
    private final String KEY_DAILY_RUN_INFO = "key_daily_run_info";
    private final String KEY_DAILY_RUN_SCHEDULED = "key_daily_run_scheduled";
    private final String KEY_DEVICE_FOLDERS_FOR_CLEANUP = "key_device_folders_for_cleanup";
    private final String KEY_DAILY_RUN_TIME = "key_daily_run_time";
    private final String KEY_APP_DISCLAIMER_READ_STATUS = "key_app_disclaimer_read_status";
    private final String KEY_USER_UNIQUEID = "key_user_uniqueid";

    private static OKPreferences kOKPreferences;
    private SharedPreferences mSharedPreferences;
    private final Object mLockImei;

    private OKPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(OKPreferences.class.getSimpleName(), Context.MODE_PRIVATE);
        mLockImei = new Object();
    }

    public static OKPreferences getInstance(Context context) {
        if (kOKPreferences == null) {
            synchronized (OKPreferences.class) {
                if (kOKPreferences == null) {
                    kOKPreferences = new OKPreferences(context);
                }
            }
        }
        return kOKPreferences;
    }
}

