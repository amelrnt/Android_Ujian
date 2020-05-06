package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lat.ta.ujianpemrograman.ui.Adapter;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class PilihPelajaranActivity extends AppCompatActivity implements
    Adapter.OnBinding<String> {

    private int paket = -1;

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_pilihpelajaran);
        ButterKnife.bind(this);

        if (getIntent() == null) finish();

        paket = getIntent().getIntExtra(PilihActionActivity.EXTRA_ID_PACKET, -1);
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
            Intent intent = new Intent(PilihPelajaranActivity.this,
                    QuestionActivity.class);
            intent.putExtra(QuestionActivity.EXTRA_QUESTION, obj);
            intent.putExtra(PilihActionActivity.EXTRA_ID_PACKET, paket);
            startActivity(intent);
        });
    }
}
