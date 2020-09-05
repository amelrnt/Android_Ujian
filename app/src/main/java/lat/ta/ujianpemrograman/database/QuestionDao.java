package lat.ta.ujianpemrograman.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import lat.ta.ujianpemrograman.model.Question;

@Dao
public interface QuestionDao {

    @Insert
    void add(Question question);

    @Query("SELECT * FROM tbl_soal")
    List<Question> getAll();

    @Query("SELECT * FROM tbl_soal WHERE kategory = :category")
    List<Question> get(int category);

    @Query("SELECT * FROM tbl_soal WHERE kategory = :category AND paket = :packet")
    List<Question> get(int category, int packet);

    @Query("DELETE FROM tbl_soal")
    void clear();

}
