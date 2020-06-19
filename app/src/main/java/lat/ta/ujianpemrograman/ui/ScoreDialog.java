package lat.ta.ujianpemrograman.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.ScoreModel;

public class ScoreDialog extends AlertDialog.Builder {

    private boolean mCancelable;
    private ScoreModel mScoreModel;
    private AlertDialog mAlertDialog;

    public static void display(Context context, ScoreModel scoreModel) {
        display(context, scoreModel, true);
    }

    public static void display(Context context, ScoreModel scoreModel, boolean cancelable) {
        ScoreDialog scoreDialog = new ScoreDialog(context, scoreModel, cancelable);
        scoreDialog.setCancelable(cancelable);

        AlertDialog alertDialog = scoreDialog.create();
        scoreDialog.setAlertDialog(alertDialog);
        alertDialog.show();
    }

    private ScoreDialog(@NonNull Context context, ScoreModel scoreModel, boolean cancelable) {
        super(context);

        mCancelable = cancelable;
        mScoreModel = scoreModel;
        setView(R.layout.dialog_score);
    }

    private void setAlertDialog(AlertDialog alertDialog) {
        this.mAlertDialog = alertDialog;
    }

    @Override
    public AlertDialog.Builder setView(View view) {
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvQuestions = view.findViewById(R.id.tv_questions);
        TextView tvCorrects = view.findViewById(R.id.tv_corrects);
        TextView tvDateTime = view.findViewById(R.id.tv_datetime);
        TextView tvScore = view.findViewById(R.id.tv_score);
        Button btnClose = view.findViewById(R.id.btn_close);

        tvName.setText(App.getUsername());
        tvScore.setText(String.valueOf(mScoreModel.getScore()));
        tvCorrects.setText(String.valueOf(mScoreModel.getCorrects()));
        tvDateTime.setText(mScoreModel.getDateTime());
        tvQuestions.setText(String.valueOf(mScoreModel.getQuestions()));
        btnClose.setOnClickListener(v -> {
            if (mCancelable) {
                mAlertDialog.dismiss();
            } else {
                ((AppCompatActivity) getContext()).finish();
            }
        });

        return super.setView(view);
    }
}
