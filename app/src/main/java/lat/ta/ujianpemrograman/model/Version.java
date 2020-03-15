package lat.ta.ujianpemrograman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {

    @SerializedName("id_ver")
    @Expose
    private int idVersion;

    @SerializedName("detail")
    @Expose
    private String detail;

    @SerializedName("version")
    @Expose
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
