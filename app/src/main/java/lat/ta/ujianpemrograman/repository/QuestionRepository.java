package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.database.QuestionDao;
import lat.ta.ujianpemrograman.model.Question;
import retrofit2.Response;

public class QuestionRepository extends Repository<Question> {

    private QuestionDao dao;

    private String TAG = QuestionRepository.class.getSimpleName();
    private ApiService service = ApiHelper.getInstance();

    public QuestionRepository(Context context) {
        super(context);
        dao = database.getQuestionDao();
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
            for (Question question: list) {
                try {
                    save(question).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Future save(Question question) {
        return executor.submit(() -> dao.add(question));
    }

}
