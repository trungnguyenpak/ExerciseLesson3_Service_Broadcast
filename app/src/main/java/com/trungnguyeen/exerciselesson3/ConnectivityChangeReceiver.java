package com.trungnguyeen.exerciselesson3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.trungnguyeen.exerciselesson3.view.UserInfoActivityListener;

/**
 * Created by trungnguyeen on 10/11/17.
 */

public class ConnectivityChangeReceiver extends BroadcastReceiver{

    private UserInfoActivityListener callback;

    public ConnectivityChangeReceiver(UserInfoActivityListener callback) {
        this.callback = callback;
    }


    private static final String TAG = ConnectivityChangeReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        if (checkConnectionFromSystem(context)){
            Log.i(TAG, "Internet connected");
            //Toast.makeText(context, "Internet connected!", Toast.LENGTH_SHORT).show();
            callback.internetConnected();
        }
        else{
            Log.i(TAG, "No internet");
            //Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
            callback.noInternet();
        }
    }


    public boolean checkConnectionFromSystem(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
