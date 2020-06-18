package lat.ta.ujianpemrograman.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.utils.Utils;

public class ExampleActivity extends Activity {

    private int mPacket = -1;
    private int mCategory = 0;
    private int mPosition = 1;

    private List<Question> mQuestionList = new ArrayList<>();

    @OnClick(R.id.btn_prev)
    void btnPrev() {
        if (mPosition > 1) {
            mPosition -= 1;
        }
    }

    @OnClick(R.id.btn_next)
    void btnNext() {
        if (mPosition < mQuestionList.size() - 1) {
            mPosition += 1;
        }
    }

    @OnClick(R.id.btn_done)
    void btnDone() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        Utils.setFullScreen(getWindow());

        if (getIntent() == null) {
            finish();
        }
    }
}
