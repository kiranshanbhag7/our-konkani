package com.android.ourkonkani.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class OKUtils {

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        if (context == null) return null;
        AssetManager assetManager = context.getAssets();
        if (assetManager != null) {
            try {
                InputStream is = assetManager.open("categories.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return json;
    }
}
