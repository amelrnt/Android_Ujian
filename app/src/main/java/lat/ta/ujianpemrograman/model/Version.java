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
    private String detail;

    @SerializedName("version")
    @Expose
    @ColumnInfo(name = "version")
    private String version;

    public Version(int idVersion, String detail, String version) {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
