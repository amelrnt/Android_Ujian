package lat.ta.ujianpemrograman.ui.quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.ui.ActionActivity;
import lat.ta.ujianpemrograman.ui.CourseActivity;
import lat.ta.ujianpemrograman.ui.InputNameDialog;
import lat.ta.ujianpemrograman.ui.ScoreDialog;
import lat.ta.ujianpemrograman.utils.Timer;
import lat.ta.ujianpemrograman.utils.Utils;

public class QuizActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static final String EXTRA_COURSE = "EXTRA_COURSE";
    public static final String KEY_TAKE_QUIZ = "KEY_TAKE_QUIZ";
    public static final String KEY_EXAMPLE = "KEY_EXAMPLE";

    private static final String TAG = QuizActivity.class.getSimpleName();

    private int mPosition = 1;
    private List<Question> mQuestionList = new ArrayList<>();
    private Map<Integer, Integer> mAnswer = new HashMap<>();

    private QuizViewModel mViewModel;
    private boolean isTakeQuiz = true;

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_no) TextView tvNo;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_time) TextView tvTime;
    @BindView(R.id.tv_question) TextView tvQuestion;
    @BindView(R.id.rg_answer) RadioGroup rgAnswer;

    @OnClick(R.id.btn_prev) void btnPrev() {
        if (mPosition > 1) {
            mPosition -= 1;
            setQuestionAndChoices();
        }
    }

    @OnClick(R.id.btn_next) void btnNext() {
        if (mPosition < mQuestionList.size() - 1) {
            mPosition += 1;
            setQuestionAndChoices();
        }
    }

    @OnClick(R.id.btn_done) void btnDone() {
        done();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setFullScreen(getWindow());
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            isTakeQuiz = getIntent().getStringExtra(CourseActivity.EXTRA_ACTION_PACKET)
                    .equals(KEY_TAKE_QUIZ);
            start();
        } else {
            finish();
        }

        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        int idPacket = getIntent().getIntExtra(ActionActivity.EXTRA_ID_PACKET, -1);
        int idCategory = getIntent().getIntExtra(EXTRA_COURSE, 0);

        rgAnswer.setOnCheckedChangeListener(this);
        mViewModel.scoreModel.setPacket(idPacket);
        mViewModel.scoreModel.setCategory(idCategory);
        mViewModel.getQuestions(isTakeQuiz).observe(this, questions -> {
            if (questions.isEmpty()) {
                String message = "Course Not Found";
                Toast.makeText(QuizActivity.this, message, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                mViewModel.scoreModel.setQuestions(questions.size());
                mQuestionList.addAll(questions);
                setQuestionAndChoices();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!isTakeQuiz) {
            finish();
            return;
        }

        String title = "PERINGATAN";
        String message = "Apakah Anda ingin Keluar dengan menyimpan quiz ini ?";
        String positiveBtn = "Ya";
        String negativeBtn = "Tidak";
        new AlertDialog.Builder(this, R.style.AppThemeRedAccent)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtn, (dialog, which) -> done())
                .setNegativeButton(negativeBtn, ((dialog, which) -> finish()))
                .create()
                .show();
    }

    @SuppressLint("SimpleDateFormat")
    private void start() {
        if (!isTakeQuiz) return;
        if (App.getUsername().isEmpty()) {
            InputNameDialog.display(this, this::start);
        } else {
            Timer timer = new Timer(1500000, 1000);
            timer.setOnChangeListener(tvTime::setText);
            timer.setOnFinish(this::done);
            timer.start();

            tvName.setText(App.getUsername());
            tvDate.setText(Utils.getDateTime());
        }
    }

    private void done() {
        if (isTakeQuiz) {
            mViewModel.save(mQuestionList, mAnswer);
            ScoreDialog.display(this, mViewModel.scoreModel, false);
        } else {
            finish();
        }
    }

    @SuppressLint("SetTextI18n")
    private void setQuestionAndChoices() {
        new Handler().post(() -> {
            Question question = mQuestionList.get(mPosition - 1);
            tvQuestion.setText(question.getQuestion());
            tvNo.setText(isTakeQuiz ? String.valueOf(mPosition) : "Contoh Soal");

            if (mAnswer.containsKey(mPosition)) {
                RadioButton radioButton = (RadioButton) rgAnswer.getChildAt(mAnswer.get(mPosition));
                radioButton.setChecked(true);
            } else {
                rgAnswer.clearCheck();
            }

            for (int i=0; i < rgAnswer.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rgAnswer.getChildAt(i);
                radioButton.setText(question.getOptions(i));
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i=0; i < group.getChildCount(); i++) {
            if (((RadioButton) group.getChildAt(i)).isChecked()) {
                mAnswer.remove(mPosition);
                mAnswer.put(mPosition, i);
            }
        }
    }
}
