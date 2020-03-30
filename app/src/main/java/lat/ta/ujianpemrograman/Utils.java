package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;

public class Utils {

    public static String TAG = Utils.class.getSimpleName();

    public static AlertDialog createDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);

        // tidak bisa tekan tombol back
        builder.setCancelable(false);

        //jika pilih yess
        builder.setPositiveButton("Ya", (dialog, id) -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        //jika pilih no
        builder.setNegativeButton("Tidak", ((dialog, which) -> {
            dialog.cancel();
        }));

        return builder.create();
    }

    public static boolean isConnected(Context context) {
        try {
            Future<Boolean> newThread =  Executors.newSingleThreadExecutor().submit(() -> {
                ConnectivityManager connectivityManager = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                URL myUrl = new URL(ApiHelper.URL);

                connectivityManager.getActiveNetworkInfo();
                URLConnection connection = myUrl.openConnection();
                connection.setConnectTimeout(3000);
                connection.connect();
                Log.i(TAG, "isConnected: status=TRUE");
                return true;
            });

            return newThread.get();
        } catch (ExecutionException | InterruptedException | NullPointerException e) {
            Log.i(TAG, "isConnected: status=FALSE");
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
}
