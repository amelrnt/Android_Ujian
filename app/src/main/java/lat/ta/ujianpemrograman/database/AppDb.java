package lat.ta.ujianpemrograman.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lat.ta.ujianpemrograman.model.Version;

@Database(entities = Version.class, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    abstract VersionDao getVersionDao();
}
