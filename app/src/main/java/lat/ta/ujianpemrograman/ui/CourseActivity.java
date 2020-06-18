package lat.ta.ujianpemrograman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.ui.quiz.QuizActivity;

import static lat.ta.ujianpemrograman.ui.ActionActivity.EXTRA_ID_PACKET;
import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;

public class CourseActivity extends AppCompatActivity implements Adapter.OnBinding<String> {

    public static final String EXTRA_ACTION_PACKET = "EXTRA_ACTION_PACKET";

    private String[] mStrings;

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);

        if (getIntent() == null) {
            finish();
        }

        mStrings = getResources().getStringArray(R.array.pilihan_pelajaran);
        List<String> data = Arrays.asList(mStrings);
        Adapter<String> adapter = new Adapter<>(this, R.layout.item_button, data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBindViewHolder(View itemView, String obj) {
        Button button = itemView.findViewById(R.id.btn);
        button.setText(obj);
        button.setOnClickListener(v -> {
            int position = 1;
            for (String s: mStrings) {
                if (s.equals(obj)) break;
                position++;
            }

            int idPacket = getIntent().getIntExtra(EXTRA_ID_PACKET, 0);
            String action = getIntent().getStringExtra(EXTRA_ACTION_PACKET);
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra(QuizActivity.EXTRA_COURSE, position);
            intent.putExtra(EXTRA_ACTION_PACKET, action);
            intent.putExtra(EXTRA_ID_PACKET, idPacket);

            startActivity(intent);
        });
    }
}
