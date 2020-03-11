package Model;

public class Version {
    int idVersion;
    String Detail;
    String Version;

    public Version(int idVersion, String detail, String version) {
        this.idVersion = idVersion;
        Detail = detail;
        Version = version;
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
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
