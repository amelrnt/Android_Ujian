package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import lat.ta.ujianpemrograman.database.NilaiDao;
import lat.ta.ujianpemrograman.model.ScoreModel;

public class NilaiRepository extends Repository {

    private NilaiDao dao;

    public NilaiRepository(Context ctx) {
        super(ctx);
        dao = database.getNilaiDao();
    }

    public LiveData<List<ScoreModel>> getNilai(int paket) {
        MutableLiveData<List<ScoreModel>> liveData = new MutableLiveData<>();
        executor.submit(() -> liveData.postValue(dao.get(paket)));
        return liveData;
    }

    public void save(ScoreModel scoreModel) {
        executor.submit(() -> dao.save(scoreModel));
    }
}
