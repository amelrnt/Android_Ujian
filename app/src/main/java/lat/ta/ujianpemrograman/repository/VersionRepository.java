package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.model.Version;
import retrofit2.Response;

public class VersionRepository extends Repository<Version> {

    public VersionRepository(Context context) {
        super(context);
    }

    public Future<Integer> checkVersionSync() {
        if (isOnline()) return executor.submit(() -> {
            Response<Version> response = service.getVersion().execute();
            return response.body().getVersion();
        });

        return null;
    }

}
