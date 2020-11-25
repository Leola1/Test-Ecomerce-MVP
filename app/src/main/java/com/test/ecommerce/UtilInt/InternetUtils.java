package com.test.ecommerce.UtilInt;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetUtils {
    public static boolean isNetworkConnected(Context context) {

        // Get a reference to the ConnectivityManager to check state of network connectivity

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default database network

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }

    public static interface RequestCallback {
        void onCallBack();
    }
}
