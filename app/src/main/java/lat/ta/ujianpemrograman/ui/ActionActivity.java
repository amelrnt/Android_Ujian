package lat.ta.ujianpemrograman.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lat.ta.ujianpemrograman.R;

import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;

public class ActionActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PACKET = "EXTRA_ID_PACKET";

    private int paket = -1;

    @OnClick(R.id.bt_mulai) void handleStart() {
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);
        intent.putExtra(CourseActivity.EXTRA_ACTION_PACKET, CourseActivity.KEY_TAKE_QUIZ);

        startActivity(intent);
    }

    @OnClick(R.id.bt_nilaiakhir) void handleResult() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);

        startActivity(intent);
    }

    @OnClick(R.id.bt_materi) void handleShowMateri() {
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);
        intent.putExtra(CourseActivity.EXTRA_ACTION_PACKET, CourseActivity.KEY_EXAMPLE);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_action);
        ButterKnife.bind(this);

        if (getIntent() == null) finish();
        paket = getIntent().getIntExtra(EXTRA_ID_PACKET, -1);
    }
}