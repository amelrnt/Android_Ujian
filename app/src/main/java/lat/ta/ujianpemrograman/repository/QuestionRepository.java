package lat.ta.ujianpemrograman.repository;

import android.content.Context;

import lat.ta.ujianpemrograman.api.ApiHelper;
import lat.ta.ujianpemrograman.api.ApiService;
import lat.ta.ujianpemrograman.model.Question;
import retrofit2.Call;
import retrofit2.Response;

public class QuestionRepository extends Repository<Question> {

    private String TAG = QuestionRepository.class.getSimpleName();
    private ApiService service = ApiHelper.getInstance();

    public QuestionRepository(Context context) {
        super(context);
    }

    @Override
    public void onResponse(Call<Question> call, Response<Question> response) {

    }

}
