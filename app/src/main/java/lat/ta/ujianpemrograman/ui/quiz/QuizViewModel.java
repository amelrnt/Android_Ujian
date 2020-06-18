package lat.ta.ujianpemrograman.ui.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.ScoreModel;
import lat.ta.ujianpemrograman.repository.NilaiRepository;
import lat.ta.ujianpemrograman.repository.QuestionRepository;
import lat.ta.ujianpemrograman.utils.Utils;

public class QuizViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private NilaiRepository nilaiRepository;

    ScoreModel scoreModel;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
        nilaiRepository = new NilaiRepository(application);
        scoreModel = new ScoreModel();
    }

    void save() {
        String datetime = Utils.getDateTime();
        scoreModel.setDateTime(datetime);
        nilaiRepository.save(scoreModel);
    }

    void save(int questions, int... ids) {

    }

    void save(String name) {
        App.setSharedPreferences(App.KEY_USERNAME, name);
    }

    LiveData<List<Question>> getQuestions(int course) {
        return questionRepository.getAll(course);
    }

    void calcCorrectAnswer(List<Question> mQuestionList, Map<Integer, Integer> mAnswer) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry: mAnswer.entrySet()) {
            int key = mQuestionList.get(entry.getKey()).getCorrectAnswer();
            if (entry.getValue().equals(key)) {
                result++;
            }
        }

        scoreModel.setCorrects(result);
    }
}
