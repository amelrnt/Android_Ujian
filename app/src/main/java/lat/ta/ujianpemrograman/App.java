package lat.ta.ujianpemrograman;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class App extends Application {

    private static SharedPreferences sharedPreferences;

    public static String KEY_VERSION = "KEY_VERSION";
    public static String KEY_VERSION_DETAIL = "KEY_VERSION_DETAIL";

    public static int getVersion() {
        return sharedPreferences.getInt(KEY_VERSION, -1);
    }

    public static int getVersionDetail() {
        return sharedPreferences.getInt(KEY_VERSION_DETAIL, -1);
    }

    public static void setSharedPreferences(String key, Object obj) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj).apply();
        } else if (obj instanceof String) {
            editor.putString(key, (String) obj).apply();
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj).apply();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}
