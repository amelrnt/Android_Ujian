package lat.ta.ujianpemrograman.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

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

    public static AlertDialog createDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ya", (dialog, id) -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        builder.setNegativeButton("Tidak", ((dialog, which) -> {
            dialog.cancel();
        }));

        return builder.create();
    }

    public static void createDialog(Context context, String message, Runnable runnable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (message != null) {
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            builder.setPositiveButton("Yes", (dialog, id) -> {
                if (runnable != null) {
                    runnable.run();
                } else {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    }

    public static void createDialog(Context context, int layout, DialogViewBinding viewBinding) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(layout, null);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        viewBinding.bind(dialogView, alertDialog);
        alertDialog.show();
    }

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

    public interface DialogViewBinding {
        void bind(View view, AlertDialog dialog);
    }
}
