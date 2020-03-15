package lat.ta.ujianpemrograman.api;

import java.util.List;

import lat.ta.ujianpemrograman.model.Version;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("check_version")
    Call<Response<List<Version>>> getVersion();

}
