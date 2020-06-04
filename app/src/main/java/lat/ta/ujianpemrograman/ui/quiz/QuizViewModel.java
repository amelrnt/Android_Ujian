package lat.ta.ujianpemrograman.ui.quiz;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.model.Nilai;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.repository.NilaiRepository;
import lat.ta.ujianpemrograman.repository.QuestionRepository;

public class QuizViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private NilaiRepository nilaiRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
        nilaiRepository = new NilaiRepository(application);
    }

    void menyimpanNilai(float nilai, int paket, int category) {
        Nilai param = new Nilai();
        param.setNilai(nilai);
        param.setPaket(paket);
        param.setCategory(category);

        nilaiRepository.save(param);
    }

    void savingName(String name) {
        App.setSharedPreferences(App.KEY_USERNAME, name);
    }

    LiveData<List<Question>> getQuestions(int course) {
        return questionRepository.getAll(course);
    }
}
