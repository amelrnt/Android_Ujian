package lat.ta.ujianpemrograman.ui.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Map;

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

    void save(List<Question> mQuestionList, Map<Integer, Integer> mAnswer) {
        String datetime = Utils.getDateTime();
        int result = 0;
        for (Map.Entry<Integer, Integer> entry: mAnswer.entrySet()) {
            int key = mQuestionList.get(entry.getKey() - 1).getCorrectAnswer();
            if (entry.getValue().equals(key)) {
                result++;
            }
        }

        scoreModel.setDateTime(datetime);
        scoreModel.setCorrects(result);
        nilaiRepository.save(scoreModel);
    }

    LiveData<List<Question>> getQuestions(boolean isRandom) {
        return questionRepository.get(scoreModel.getCategory(), scoreModel.getPacket(), !isRandom);
    }
}
