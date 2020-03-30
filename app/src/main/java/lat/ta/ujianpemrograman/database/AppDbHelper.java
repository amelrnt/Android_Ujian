package lat.ta.ujianpemrograman.database;

import android.content.Context;

import androidx.room.Room;

/**
 * @class AppDbHelper
 * merupakan klass bantuan untuk memanggil database sqlite
 * menggunakan pattern Singleton.
 */

public class AppDbHelper {

    private static AppDb instance = null;

    public static synchronized AppDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDb.class, "app_database.db").build();
        }

        return instance;
    }
}
