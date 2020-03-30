package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import lat.ta.ujianpemrograman.model.Version;
import retrofit2.Response;

public class VersionRepository extends Repository<Version> {

    private String TAG = VersionRepository.class.getSimpleName();

    public VersionRepository(Context context) {
        super(context);
    }

    public Future<Version> checkVersionSync() {
        AtomicReference<Version> atomicReference = new AtomicReference<>(null);
        if (isOnline()) {
            return Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    Response<Version> response = service.getVersion().execute();
                    return response.body();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            });
        } else {
            return null;
        }
    }

}
