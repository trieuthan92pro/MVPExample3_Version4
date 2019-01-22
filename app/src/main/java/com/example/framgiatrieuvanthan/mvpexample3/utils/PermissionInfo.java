package com.example.framgiatrieuvanthan.mvpexample3.utils;

import android.Manifest;

public class PermissionInfo {
    public static String[] permissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static final int PERMISSION_READ_PHONE_STATE = 900;
    public static final int PERMISSION_READ_EXTERNAL_STORAGE = 901;

    public static int[] PERMISSION_REQUESCODE={
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_READ_EXTERNAL_STORAGE
    };

    public static int PERMISSION_COUNT = 0;
    public static final int TOTAL_PERMISSION = 2;
}
