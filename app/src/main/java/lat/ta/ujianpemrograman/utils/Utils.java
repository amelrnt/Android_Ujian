package lat.ta.ujianpemrograman.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;

public class Utils {

    private static String TAG = Utils.class.getSimpleName();

    public static boolean isConnected(Context context) {
        try {
            Future<Boolean> newThread =  Executors.newSingleThreadExecutor().submit(() -> {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                URL myUrl = new URL(ApiHelper.URL);

                connectivityManager.getActiveNetworkInfo();
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(20000);
                connection.connect();
                Log.i(TAG, "isConnected: status=TRUE");
                return true;
            });

            return newThread.get();
        } catch (ExecutionException | InterruptedException | NullPointerException e) {
            Log.i(TAG, "isConnected: status=FALSE");
            e.printStackTrace();
            return false;
        }
    }

    public static void showMessage(Context context, String message) {
        View view = ((Activity) context).findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public static void setFullScreen(Window window) {
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateTime() {
        Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("dd-MM:yyyy HH:mm:ss")
                .format(date);
    }
}
