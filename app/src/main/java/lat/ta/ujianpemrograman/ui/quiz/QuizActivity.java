package lat.ta.ujianpemrograman.ui.quiz;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.utils.Timer;

import static lat.ta.ujianpemrograman.ui.ActionActivity.EXTRA_ID_PACKET;
import static lat.ta.ujianpemrograman.utils.Utils.createDialog;
import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.utils.Utils.showMessage;

public class QuizActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static String EXTRA_COURSE = "EXTRA_COURSE";

    private QuizViewModel viewModel;

    private static final String TAG = QuizActivity.class.getSimpleName();

    private int paket = -1;
    private int position = 1;
    private List<Question> questionList = new ArrayList<>();
    private Map<Integer, Integer> answer = new HashMap<>();

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_no) TextView tvNo;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_time) TextView tvTime;
    @BindView(R.id.tv_question) TextView tvQuestion;
    @BindView(R.id.rg_answer) RadioGroup rgAnswer;

    @OnClick(R.id.btn_prev) void btnPrev() {
        if (position > 1) {
            position -= 1;
            setQuestionAndChoices();
        }
    }

    @OnClick(R.id.btn_next) void btnNext() {
        if (position < questionList.size() - 1) {
            position += 1;
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

        if (getIntent() == null) {
            finish();
            return;
        }

        int extra = getIntent().getIntExtra(EXTRA_COURSE, 0);
        paket = getIntent().getIntExtra(EXTRA_ID_PACKET, -1);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        viewModel.getQuestions(extra).observe(this, questions -> {
            questionList.addAll(questions);
            setQuestionAndChoices();
        });

        start();
        rgAnswer.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint("SimpleDateFormat")
    private void start() {
        if (App.getUsername().isEmpty()) {
            createDialog(this, R.layout.dialog_input_name, (dialogView, alertDialog) -> {
                EditText edtName = dialogView.findViewById(R.id.editTextNama);
                Button btnOk =  dialogView.findViewById(R.id.buttonOK);
                btnOk.setOnClickListener(view -> {
                    String name = edtName.getText().toString();
                    if (! TextUtils.isEmpty(name)) {
                        viewModel.savingName(name);
                        alertDialog.dismiss();
                        start();
                    } else {
                        String emptyInput = getResources().getString(R.string.warning_empty_input);
                        showMessage(QuizActivity.this, emptyInput);
                    }
                });
            });
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MM - yyyy");
        String message = getResources().getString(R.string.info_starting);
        Timer timer = new Timer(1500000, 1000); // 25 Menit, 1 Detik
        timer.setOnChangeListener(tvTime::setText);
        timer.setOnFinish(this::done);
        timer.start();

        tvName.setText(App.getUsername());
        tvDate.setText(dateFormat.format(new Date()));
        showMessage(this, message);
    }

    private void done() {
        float point = 100f / questionList.size();
        float score = 0f;
        for (Map.Entry<Integer, Integer> entry: answer.entrySet()) {
            int key = questionList.get(entry.getKey()).getCorrectAnswer();
            if (entry.getValue().equals(key)) {
                score += point;
            }
        }

        Log.i(TAG, "done: point="+ point);
        Log.i(TAG, "done: score="+ score);

        viewModel.menyimpanNilai(score, paket, questionList.get(0).getCategory());
        finish();
    }

    @SuppressLint("SetTextI18n")
    private void setQuestionAndChoices() {
        new Handler().post(() -> {
            Question question = questionList.get(position - 1);
            tvNo.setText(String.valueOf(position));
            tvQuestion.setText(question.getQuestion());

            Log.i(TAG, "setQuestionAndChoices: Data ke-"+ (position - 1));
            Log.i(TAG, "setQuestionAndChoices: No Soal="+ position);
            Log.i(TAG, "setQuestionAndChoices: Id Soal="+ question.getId());
            Log.i(TAG, "setQuestionAndChoices: Sudah Dijawab ? "+
                    answer.containsKey(position));

            if (answer.containsKey(position)) {
                Log.i(TAG, "setQuestionAndChoices: Jawaban="+ answer.get(position));
                RadioButton radioButton = (RadioButton) rgAnswer.getChildAt(answer.get(position));
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
                Log.i(TAG, "onCheckedChanged: Data Soal ke-"+ position);
                Log.i(TAG, "onCheckedChanged: Jawaban Yang dipilih="+ i);

                answer.remove(position);
                answer.put(position, i);
            }
        }
    }
}
