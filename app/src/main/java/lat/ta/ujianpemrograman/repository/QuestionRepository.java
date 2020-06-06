package lat.ta.ujianpemrograman.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.database.QuestionDao;
import lat.ta.ujianpemrograman.model.Question;
import retrofit2.Response;

public class QuestionRepository extends Repository<Question> {

    private static final String TAG = QuestionRepository.class.getSimpleName();

    private QuestionDao dao;

    private ApiService service = ApiHelper.getInstance();

    public QuestionRepository(Context context) {
        super(context);
        dao = database.getQuestionDao();
    }

    public LiveData<List<Question>> getAll(int course) {
        MutableLiveData<List<Question>> liveData = new MutableLiveData<>();
        executor.submit(() -> liveData.postValue(dao.get(course)));
        return liveData;
    }

    public Future<List<Question>> updateQuestion(int packet) {
        if (! isOnline()) return null;

        return executor.submit(() -> {
            Response<List<Question>> response = service.getUpdateSoal(packet).execute();
            return response.body();
        });
    }

    public Future save(List<Question> list) {
        return executor.submit(() -> {
            dao.clear();
            for (Question question: list) save(question);
        });
    }

    public Future save(Question question) {
        Log.i(TAG, "save: Id Paket="+ question.getIdPacket());
        Log.i(TAG, "save: Id Pertanyaan="+ question.getId());
        Log.i(TAG, "save: Kategori="+ question.getCategory());
        return executor.submit(() -> dao.add(question));
    }
}
