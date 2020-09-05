package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public LiveData<List<Question>> get(int course, int packet, boolean random) {
        MutableLiveData<List<Question>> liveData = new MutableLiveData<>();
        executor.submit(() -> {
            List<Question> questions = dao.get(course, packet);
            if (random && !questions.isEmpty()) {
                List<Question> _questions = new ArrayList<>();
                for (int i=0; i < 5; i++) {
                    Random nRandom = new Random();
                    int range = (questions.size() - 1) + 1;
                    int x = nRandom.nextInt(range);
                    _questions.add(questions.get(x));
                }
                liveData.postValue(_questions);
            } else {
                liveData.postValue(questions);
            }
        });
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
        return executor.submit(() -> dao.add(question));
    }
}
