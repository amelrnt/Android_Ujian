package lat.ta.ujianpemrograman.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import lat.ta.ujianpemrograman.model.Version;

@Dao
public interface VersionDao {

    @Insert
    void insert(Version version);

    @Query("SELECT * FROM version")
    Version checkUpdate();
}
