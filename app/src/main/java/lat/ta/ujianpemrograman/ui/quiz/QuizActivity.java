package lat.ta.ujianpemrograman.ui.quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.ScoreModel;
import lat.ta.ujianpemrograman.ui.CourseActivity;
import lat.ta.ujianpemrograman.utils.Timer;
import lat.ta.ujianpemrograman.utils.Utils;

import static lat.ta.ujianpemrograman.ui.ActionActivity.EXTRA_ID_PACKET;
import static lat.ta.ujianpemrograman.utils.Utils.createDialog;
import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.utils.Utils.showMessage;

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
        setFullScreen(getWindow());
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            isTakeQuiz = getIntent().getStringExtra(CourseActivity.EXTRA_ACTION_PACKET)
                    .equals(KEY_TAKE_QUIZ);
        } else {
            finish();
        }

        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        int idPacket = getIntent().getIntExtra(EXTRA_ID_PACKET, -1);
        int idCategory = getIntent().getIntExtra(EXTRA_COURSE, 0);

        rgAnswer.setOnCheckedChangeListener(this);
        mViewModel.scoreModel.setPacket(idPacket);
        mViewModel.scoreModel.setCategory(idCategory);
        mViewModel.getQuestions(idCategory).observe(this, questions -> {
            if (!isTakeQuiz) {
                tvNo.setText("Contoh Soal");

                List<Question> _questions = new ArrayList<>();
                for (int i=0; i < 5; i++) {
                    Random random = new Random();
                    int range = (questions.size() - 1) + 1;
                    int x = random.nextInt(range);
                    _questions.add(questions.get(x));
                }
                mQuestionList.addAll(_questions);
            } else {
                mViewModel.scoreModel.setQuestions(questions.size());
                mQuestionList.addAll(questions);
            }

            setQuestionAndChoices();
        });

        start();
    }

    @Override
    public void onBackPressed() {
    }

    @SuppressLint("SimpleDateFormat")
    private void start() {
        if (!isTakeQuiz) {
            return;
        }

        if (App.getUsername().isEmpty()) {
            createDialog(this, R.layout.dialog_input_name, (dialogView, alertDialog) -> {
                EditText edtName = dialogView.findViewById(R.id.editTextNama);
                Button btnOk =  dialogView.findViewById(R.id.buttonOK);
                btnOk.setOnClickListener(view -> {
                    String name = edtName.getText().toString();
                    if (! TextUtils.isEmpty(name)) {
                        mViewModel.save(name);
                        alertDialog.dismiss();
                        start();
                    } else {
                        String emptyInput = getResources().getString(R.string.warning_empty_input);
                        showMessage(QuizActivity.this, emptyInput);
                    }
                });
            });
        }

        String message = getResources().getString(R.string.info_starting);
        Timer timer = new Timer(1500000, 1000);
        timer.setOnChangeListener(tvTime::setText);
        timer.setOnFinish(this::done);
        timer.start();

        String datetime = Utils.getDateTime();
        tvName.setText(App.getUsername());
        tvDate.setText(datetime);
        showMessage(this, message);
    }

    private void done() {
        if (!isTakeQuiz) {
            finish();
            return;
        }

        mViewModel.save();
        ScoreModel scoreModel = mViewModel.scoreModel;

        createDialog(this, R.layout.dialog_score, (view, dialog) -> {
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvQuestions = view.findViewById(R.id.tv_questions);
            TextView tvCorrects = view.findViewById(R.id.tv_corrects);
            TextView tvDateTime = view.findViewById(R.id.tv_datetime);
            TextView tvScore = view.findViewById(R.id.tv_score);
            Button btnClose = view.findViewById(R.id.btn_close);

            tvName.setText(App.getUsername());
            tvQuestions.setText(String.valueOf(scoreModel.getQuestions()));
            tvCorrects.setText(String.valueOf(scoreModel.getCorrects()));
            tvDateTime.setText(scoreModel.getDateTime());
            tvScore.setText(String.valueOf(scoreModel.getScore()));
            btnClose.setOnClickListener(v -> finish());
        });
    }

    @SuppressLint("SetTextI18n")
    private void setQuestionAndChoices() {
        new Handler().post(() -> {
            if (isTakeQuiz) {
                tvNo.setText(String.valueOf(mPosition));
            }

            Question question = mQuestionList.get(mPosition - 1);
            tvQuestion.setText(question.getQuestion());

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
