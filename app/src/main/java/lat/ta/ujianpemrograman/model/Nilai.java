package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "nilai",
        primaryKeys = {"kategori", "paket"})
public class Nilai {

    @ColumnInfo(name = "paket")
    private int paket;

    @ColumnInfo(name = "nilai")
    private float nilai;

    @ColumnInfo(name = "kategori")
    private int category;

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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
