package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PilihactionActivity extends Activity {

    Button btn1, btn2, btn3;
    final Context context = this;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihaction);
        btn1 = (Button)findViewById(R.id.bt_mulai);
        btn2 = (Button)findViewById(R.id.bt_nilaiakhir);
        btn3 = (Button)findViewById(R.id.bt_materi);
        btn1.setOnClickListener(mulai);
        btn2.setOnClickListener(nilaiAkhir);
        btn3.setOnClickListener(materi);

    }

//    public void btn_OnClick(View v) {
//
//    if (v.getId() == R.id.bt_mulai) {
//        Intent i = new Intent(pilihaction.this, pilihpelajaran.class);
//        startActivity(i);
//        System.out.println();
//    }else if (v.getId() == R.id.bt_nilaiakhir){
//     Intent iN = new Intent(pilihaction.this , LihatNilaiActivity.class);
//     startActivity(iN);
//        System.out.println(iN);
//
//
//        }
//    }

    private View.OnClickListener mulai = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_mulai:
                    intent = new Intent(context, pilihpelajaran.class);
                    startActivity(intent);
                    break;

            }
        }
    };
    private View.OnClickListener nilaiAkhir = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_nilaiakhir:
                    intent = new Intent(context, NilaiActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    private View.OnClickListener materi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_materi:
                    intent = new Intent(context, Pilihmateri.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}