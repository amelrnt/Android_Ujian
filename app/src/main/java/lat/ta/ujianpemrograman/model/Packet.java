package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tbl_paket")
public class Packet {

    @SerializedName("paket")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "paket")
    private int id;

    @SerializedName("nama_paket")
    @Expose
    @ColumnInfo(name = "nama_paket")
    private String name;

    public Packet() {
    }

    @Ignore
    public Packet(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
