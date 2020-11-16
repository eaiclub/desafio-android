package com.lucasdonato.nasa.mechanism;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Utilitary class for generally application used methods.
 */
public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    /**
     * Gets the application version.
     * @param context Application context.
     * @return Application version.
     */
    public static String version(@NonNull final Context context) {
        String result = "Unknown";

        try {
            // Get app version
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            result = packageInfo.versionName;
        } catch (Exception e) {
            Log.e(TAG, "Error getting app version", e);
        }
        return result;
    }

}
