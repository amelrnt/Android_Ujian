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

    @Query("DELETE FROM tbl_soal")
    void clear();

}
