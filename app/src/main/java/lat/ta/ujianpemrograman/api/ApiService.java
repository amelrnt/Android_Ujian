package lat.ta.ujianpemrograman.api;

import java.util.List;

import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.Version;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("check_version")
    Call<Version> getVersion();

    @GET("update_soal/{paket}")
    Call<List<Question>> getUpdateSoal(@Path("paket") int paket);

    @GET("update_paket")
    Call<List<Packet>> getUpdatePacket();

}
