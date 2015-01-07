package com.cyc.processguard.utils;

import android.util.Log;

/**
 * Created by Administrator on 2015/1/7.
 */
public class LogUtils {
    private static String TAG = "ProcessGuard";

    public static void info(String content) {
        Log.i(TAG, content);
    }

    public static void error(String content) {
        Log.e(TAG, content);
    }
}
