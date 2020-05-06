package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nilai")
public class Nilai {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "paket")
    private int paket;

    @ColumnInfo(name = "nilai")
    private float nilai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaket() {
        return paket;
    }

    public void setPaket(int paket) {
        this.paket = paket;
    }

    public float getNilai() {
        return nilai;
    }

    public void setNilai(float nilai) {
        this.nilai = nilai;
    }
}
