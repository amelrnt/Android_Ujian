package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class PilihActionActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PACKET = "EXTRA_ID_PACKET";

    private int paket = -1;

    @OnClick(R.id.bt_mulai) void handleStart() {
        Intent intent = new Intent(this, PilihPelajaranActivity.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);

        startActivity(intent);
    }

    @OnClick(R.id.bt_nilaiakhir) void handleResult() {
        Intent intent = new Intent(this, NilaiActivity.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);

        startActivity(intent);
    }

    @OnClick(R.id.bt_materi) void handleShowMateri() {
        Intent intent = new Intent(this, Pilihmateri.class);
        intent.putExtra(EXTRA_ID_PACKET, paket);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_pilihaction);
        ButterKnife.bind(this);

        if (getIntent() == null) finish();
        paket = getIntent().getIntExtra(EXTRA_ID_PACKET, -1);
    }
}