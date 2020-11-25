package com.test.ecommerce.UtilInt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import static com.test.ecommerce.UtilInt.InternetUtils.isNetworkConnected;


public class NetworkChangeReceiver extends BroadcastReceiver {

    OnNetworkListener onNetworkListener;

    public void setOnNetworkListener(OnNetworkListener onNetworkListener) {

        this.onNetworkListener = onNetworkListener;
    }

    @Override

    public void onReceive(Context context, Intent intent) {

        if (!isNetworkConnected(context)) {
            onNetworkListener.onNetworkDisconnected();

        } else {

            onNetworkListener.onNetworkConnected();

        }

    }


    public static class Utils {
        public static void shareProduct(Context context, String productName , String url) {

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);

            sharingIntent.setType("text/plain");

            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Hey, Check out this amazing item '"
                    + productName +"' with its photo at "+ url);

            context.startActivity(Intent.createChooser(sharingIntent, "Share using"));

        }
    }
}
