package lat.ta.ujianpemrograman.ui;

import android.app.Activity;

public class ScoreDetailActivity extends Activity {
//    protected Cursor cursor;
//    DBHelper dbHelper;
//    TextView text1, text2, text3, text4;
//    Button btn1;
//    final Context context = this;
//    Intent intent;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_score_details);
//        dbHelper = new DBHelper(this);
//        text1 = (TextView) findViewById(R.id.txt_tgl);
//        text2 = (TextView) findViewById(R.id.txt_benar);
//        text3 = (TextView) findViewById(R.id.txt_nilai);
//        text4 = (TextView) findViewById(R.id.tvusername);
//        btn1 = (Button)findViewById(R.id.btnkembali);
//        btn1.setOnClickListener(mulai);
//
////        text1 = (TextView) findViewById(R.id.tvusername);
////        text2 = (TextView) findViewById(R.id.txt_tgl);
////        text3 = (TextView) findViewById(R.id.txt_benar);
////        text4 = (TextView) findViewById(R.id.txt_nilai);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM tb_nilai WHERE nama_matkul = '" +
//                getIntent().getStringExtra("nama_matkul") + "'",null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0)
//        {
//            cursor.moveToPosition(0);
////            text1.setText(": "+cursor.getString(1).toString());
////            text2.setText(": "+cursor.getString(3).toString());
////            text3.setText(": "+cursor.getString(4).toString());
////            text4.setText(cursor.getString(5).toString());
//            text1.setText(": "+cursor.getString(3).toString());
//            text2.setText(": "+cursor.getString(4).toString());
//            text3.setText(cursor.getString(5).toString());
//            text4.setText(": "+cursor.getString(1).toString());
//        }
//
//    }
//
//    private View.OnClickListener mulai = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.btnkembali:
//                    intent = new Intent(context, PacketActivity.class);
//                    startActivity(intent);
//                    break;
//
//            }
//        }
//    };
//    public void onBackPressed() {
//        Intent intent = new Intent(context, MainActivity.class);
//        startActivity(intent);
//
//    }
}
