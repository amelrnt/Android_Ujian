package lat.ta.ujianpemrograman.repository;

import android.util.Log;

import java.util.List;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.model.Version;

public class Repo {

    private static String TAG = Repo.class.getSimpleName();

    private ApiService service = ApiHelper.getInstance();

    public void checkUpdate() {
        service.getVersion().enqueue(new ApiHelper.EnQueue<>((res) -> {
            if (res.getStatus() != 200) return;

            Log.d(TAG, "checkUpdate: "+ res.getMessage());

            List<Version> versions = (List<Version>) res.getData();
            for (Version version: versions) {
                Log.d(TAG, "checkUpdate: "+ version.getVersion());
                Log.d(TAG, "checkUpdate: "+ version.getDetail());
                Log.d(TAG, "checkUpdate: "+ version.getIdVersion());
            }
        }));
    }
}
