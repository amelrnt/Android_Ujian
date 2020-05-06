package lat.ta.ujianpemrograman.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import lat.ta.ujianpemrograman.model.Nilai;

public interface NilaiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Nilai nilai);

    @Query("SELECT * FROM nilai WHERE paket = :paket")
    void get(int paket);

    @Delete
    void delete(Nilai nilai);

}
