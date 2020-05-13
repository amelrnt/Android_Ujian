package lat.ta.ujianpemrograman;

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
import lat.ta.ujianpemrograman.ui.Adapter;

public class PilihMateriActivity extends AppCompatActivity implements
        Adapter.OnBinding<String> {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_materi);
        ButterKnife.bind(this);

        if (getIntent() == null) {
            finish();
        }

        String[] strings = getResources().getStringArray(R.array.pilihan_pelajaran);
        List<String> data = Arrays.asList(strings);
        Adapter<String> adapter = new Adapter<>(this, R.layout.item_button, data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBindViewHolder(View itemView, String obj) {
        Button button = itemView.findViewById(R.id.btn);
        button.setText(obj);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(PilihMateriActivity.this,
                    QuestionActivity.class);
            intent.putExtra(LihatMateriActivity.EXTRA_MATERI, obj);
            startActivity(intent);
        });
    }
}
