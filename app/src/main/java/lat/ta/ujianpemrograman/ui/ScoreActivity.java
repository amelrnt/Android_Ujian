package lat.ta.ujianpemrograman.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Nilai;
import lat.ta.ujianpemrograman.repository.NilaiRepository;

import static lat.ta.ujianpemrograman.ui.ActionActivity.EXTRA_ID_PACKET;
import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;

public class ScoreActivity extends AppCompatActivity implements Adapter.OnBinding<Nilai> {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);

        if (getIntent() == null) {
            finish();
        }

        Adapter<Nilai> adapter = new Adapter<>(this, R.layout.item_nilai, null);

        int paket = getIntent().getIntExtra(EXTRA_ID_PACKET, -1);
        NilaiRepository nilaiRepository = new NilaiRepository(this);
        nilaiRepository.getNilai(paket).observe(this, adapter::setNewData);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(View itemView, Nilai nilai) {
        String[] strings = getResources().getStringArray(R.array.pilihan_pelajaran);
        TextView tvCategory = itemView.findViewById(R.id.tv_category);
        tvCategory.setText(strings[nilai.getCategory() - 1]);

        TextView tvScore = itemView.findViewById(R.id.tv_score);
        tvScore.setText(String.format("%.2f", nilai.getNilai()));
    }
}
