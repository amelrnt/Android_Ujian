package lat.ta.ujianpemrograman.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "version")
public class Version {

    @SerializedName("id_ver")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id_version")
    private int idVersion;

    @SerializedName("detail")
    @Expose
    @ColumnInfo(name = "detail")
    private int detail;

    @SerializedName("version")
    @Expose
    @ColumnInfo(name = "version")
    private int version;

    public Version(int idVersion, int detail, int version) {
        this.idVersion = idVersion;
        this.detail = detail;
        this.version = version;
    }

    public Version() {
    }

    public int getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(int idVersion) {
        this.idVersion = idVersion;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
