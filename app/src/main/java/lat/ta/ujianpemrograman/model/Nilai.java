package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

// TODO : Buat Tabel

public class Nilai {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

}
