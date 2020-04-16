package lat.ta.ujianpemrograman;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.repository.QuestionRepository;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
    }

    void savingName(String name) {
        App.setSharedPreferences(App.KEY_USERNAME, name);
    }

    LiveData<List<Question>> getQuestions(String extra) {
        int course = 1;
        String[] courses = getApplication().getResources()
                .getStringArray(R.array.pilihan_pelajaran);

        for (int i=0; i < courses.length-1; i++) {
            if (extra.equals(courses[i+1])) {
                course = i;
            }
        }

        return questionRepository.getAll(course);
    }
}
