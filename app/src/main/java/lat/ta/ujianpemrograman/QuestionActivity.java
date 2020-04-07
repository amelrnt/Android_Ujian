package lat.ta.ujianpemrograman;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class QuestionActivity extends AppCompatActivity {

    public static String EXTRA_QUESTION = "EXTRA_QUESTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_about);

        if (getIntent() == null) {
            finish();
            return;
        }

        String extra = getIntent().getStringExtra(EXTRA_QUESTION);
    }
}
