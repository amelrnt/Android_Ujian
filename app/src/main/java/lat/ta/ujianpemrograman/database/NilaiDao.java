package lat.ta.ujianpemrograman.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import lat.ta.ujianpemrograman.model.ScoreModel;

@Dao
public interface NilaiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(ScoreModel scoreModel);

    @Query("SELECT * FROM nilai WHERE paket = :paket")
    List<ScoreModel> get(int paket);

    @Delete
    void delete(ScoreModel scoreModel);

}
