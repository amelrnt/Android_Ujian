package lat.ta.ujianpemrograman;

import android.app.Activity;

public class NilaiActivity extends Activity {
//    String[] daftar;
//    ListView ListView01;
//    Menu menu;
//    protected Cursor cursor;
//    DBHelper dbcenter;
//    public static NilaiActivity ma;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nilai);
//
//        ma = this;
//        dbcenter = new DBHelper(this);
//        RefreshList();
//    }
//    public void RefreshList(){
//        SQLiteDatabase db = dbcenter.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM tb_nilai",null);
//        daftar = new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int cc=0; cc < cursor.getCount(); cc++){
//            cursor.moveToPosition(cc);
//            daftar[cc] = cursor.getString(2).toString();
//        }
//        ListView01 = (ListView)findViewById(R.id.listview1);
//        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
//        ListView01.setSelected(true);
//        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
//                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
//                final CharSequence[] dialogitem = {"Lihat Nilai"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(NilaiActivity.this);
//                builder.setTitle("Nilai Akhir");
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch(item){
//                            case 0 :
//                                Intent i = new Intent(getApplicationContext(), LihatNilaiActivity.class);
//                                i.putExtra("nama_matkul", selection);
//                                startActivity(i);
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
//            }});
//        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
//    }
}
