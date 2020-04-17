package lat.ta.ujianpemrograman;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import lat.ta.ujianpemrograman.model.Question;

import static lat.ta.ujianpemrograman.Utils.createDialog;
import static lat.ta.ujianpemrograman.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.Utils.showMessage;

public class QuestionActivity extends AppCompatActivity {

    public static String EXTRA_QUESTION = "EXTRA_QUESTION";

    private QuestionViewModel viewModel;

    private int position = 1;
    private List<Question> questionList = new ArrayList<>();
    private Map<Integer, String> answer = new HashMap<>();

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_no) TextView tvNo;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_time) TextView tvTime;
    @BindView(R.id.tv_question) TextView tvQuestion;
    @BindView(R.id.rg_answer) RadioGroup rgAnswer;
    @BindView(R.id.btn_prev) ImageButton btnPrev;
    @BindView(R.id.btn_next) ImageButton btnNext;

    @OnClick(R.id.btn_prev) void btnPrev() {
        if (position > 1) {
            savingAnswer();
            position -= 1;
            setQuestionAndChoices();
            btnPrev.setClickable(true);
            btnNext.setClickable(true);
        } else {
            btnPrev.setClickable(false);
        }
    }

    @OnClick(R.id.btn_done) void btnDone() {
        done();
    }

    @OnClick(R.id.btn_next) void btnNext() {
        if (position < questionList.size() - 1) {
            savingAnswer();
            position += 1;
            setQuestionAndChoices();
            btnNext.setClickable(true);
            btnPrev.setClickable(true);
        } else {
            btnNext.setClickable(false);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        if (getIntent() == null) {
            finish();
            return;
        }

        String extra = getIntent().getStringExtra(EXTRA_QUESTION);
        viewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        viewModel.getQuestions(extra).observe(this, questions -> {
            questionList.addAll(questions);
            setQuestionAndChoices();
        });

        if (App.getUsername().isEmpty()) {
            createDialog(this, R.layout.nama, (dialogView, alertDialog) -> {
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
                        showMessage(QuestionActivity.this, emptyInput);
                    }
                });
            });
        }

        start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint("SimpleDateFormat")
    private void start() {
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

    private void savingAnswer() {
        int resId = rgAnswer.getCheckedRadioButtonId();
        if (resId != -1) {
            String checked = ((RadioButton) rgAnswer.findViewById(resId)).getText().toString();
            answer.remove(position);
            answer.put(position, checked);
        }
    }

    private void done() {
        // TODO : Calculate and show result
        for (Question q: questionList) {

        }
    }

    @SuppressLint("SetTextI18n")
    private void setQuestionAndChoices() {
        Question question = questionList.get(position - 1);
        String labelNoQuestion = getResources().getString(R.string.label_no_question);
        tvNo.setText(labelNoQuestion + " " + position);
        tvQuestion.setText(question.getQuestion());

        String[] choices = new String[]{
                question.getOptionA(), question.getOptionB(),
                question.getOptionC(), question.getOptionD()
        };
        for (int i=0; i < rgAnswer.getChildCount(); i++) {
            ((RadioButton) rgAnswer.getChildAt(i)).setText(choices[i]);
        }
    }
}
