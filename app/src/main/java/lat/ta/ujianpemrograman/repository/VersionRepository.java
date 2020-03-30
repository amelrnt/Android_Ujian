package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import lat.ta.ujianpemrograman.model.Version;

public class VersionRepository extends Repository<Version> {

    private String TAG = VersionRepository.class.getSimpleName();

    public VersionRepository(Context context) {
        super(context);
    }

}
