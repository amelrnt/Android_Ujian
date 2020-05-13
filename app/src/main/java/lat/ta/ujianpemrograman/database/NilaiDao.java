package lat.ta.ujianpemrograman.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import lat.ta.ujianpemrograman.model.Nilai;

@Dao
public interface NilaiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Nilai nilai);

    @Query("SELECT * FROM nilai WHERE paket = :paket")
    List<Nilai> get(int paket);

    @Delete
    void delete(Nilai nilai);

}
