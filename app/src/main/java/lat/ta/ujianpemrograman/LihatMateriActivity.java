package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LihatMateriActivity extends Activity {

    public static final String EXTRA_MATERI = "EXTRA_MATERI";

    @BindView(R.id.tv_materi) TextView tvMateri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_materi);
        ButterKnife.bind(this);
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lihat_materi);
//        dbHelper = new DBHelper(this);
//        text1 = (TextView) findViewById(R.id.txt_materi);
//        btn1 = (Button)findViewById(R.id.btnkembali);
//        btn1.setOnClickListener(mulai);
//
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM tb_materi WHERE nma_matkul = '" +
//                getIntent().getStringExtra("nma_matkul") + "'",null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0)
//        {
//            cursor.moveToPosition(0);
////            text1.setText(": "+cursor.getString(1).toString());
////            text2.setText(": "+cursor.getString(3).toString());
////            text3.setText(": "+cursor.getString(4).toString());
////            text4.setText(cursor.getString(5).toString());
//            text1.setText(""+cursor.getString(1).toString());
//
//        }
//
//    }
}
