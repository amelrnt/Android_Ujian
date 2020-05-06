package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import lat.ta.ujianpemrograman.database.NilaiDao;
import lat.ta.ujianpemrograman.model.Nilai;

public class NilaiRepository extends Repository {

    private NilaiDao dao;

    public NilaiRepository(Context ctx) {
        super(ctx);
        dao = database.getNilaiDao();
    }

    public void save(Nilai nilai) {
        executor.submit(() -> dao.save(nilai));
    }
}
