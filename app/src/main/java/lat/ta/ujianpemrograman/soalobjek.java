package lat.ta.ujianpemrograman;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class soalobjek extends Activity{

    public TextView txtnama;
    public static int score = 0;
    private DBHelper db;
    private TextView  txtno, txttanggal, txtwaktu, txtsoal;
    private ImageView img;
    private RadioGroup rg;
    private RadioButton rdA, rdB, rdC, rdD;
    private List<Soal1> Section6;
    private CounterClass mCountDownTimer;
    private int detik = 1500000; // --> 25 menit
    private ImageButton btnPrev, btnNext, btnSelesai;
    int jawabanYgDiPilih[] = null;
    int jawabanYgBenar[] = null;
    boolean cekPertanyaan = false;
    int urutanPertanyaan = 0;
    String noSalah = "";

    final Context context = this;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soalobject);
        db = new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        txtnama = (TextView) findViewById(R.id.textViewNama);

        txtno = (TextView) findViewById(R.id.textViewHalaman);
        txttanggal = (TextView) findViewById(R.id.textViewTanggal);
        txtwaktu = (TextView) findViewById(R.id.textViewWaktu);
        txtsoal = (TextView) findViewById(R.id.textViewSoal2);
        img = (ImageView) findViewById(R.id.gambarKuis);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rdA = (RadioButton) findViewById(R.id.radio0);
        rdB = (RadioButton) findViewById(R.id.radio1);
        rdC = (RadioButton) findViewById(R.id.radio2);
        rdD = (RadioButton) findViewById(R.id.radio3);
        btnPrev = (ImageButton) findViewById(R.id.buttonPrev);
        btnNext = (ImageButton) findViewById(R.id.buttonNext);
        btnSelesai = (ImageButton) findViewById(R.id.buttonSelesai);
        final Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        txttanggal.setText(Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year));

        Section6 = new ArrayList<Soal1>();
        Section6 = db.getSoalDinamis(ListPaketSoal.paket, "6");

        btnSelesai.setOnClickListener(klikSelesai);
        btnPrev.setOnClickListener(klikSebelum);
        btnNext.setOnClickListener(klikBerikut);
        //new GetSoal().execute();
        jawabanYgDiPilih = new int[Section6.size()];
        java.util.Arrays.fill(jawabanYgDiPilih, -2);
        jawabanYgBenar = new int[Section6.size()];
        java.util.Arrays.fill(jawabanYgBenar, -1);

        if(Section6.isEmpty()){
            AlertDialog dialog = noquestions();
            dialog.show();
        }
        else{
            showInputUser();
        }
    }

    private void showInputUser() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View v = mInflater.inflate(R.layout.nama, null);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setView(v);
        dialog.setTitle("Ketikkan Nama Anda");
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setCancelable(false);

        final Button btnOk = (Button) v.findViewById(R.id.buttonOK);
        final EditText inputUser = (EditText) v.findViewById(R.id.editTextNama);

        btnOk.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if(inputUser.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Isi dulu", Toast.LENGTH_LONG).show();
                }else{
                    txtnama.setText(inputUser.getText().toString());
                    String username = inputUser.getText().toString();
                    SQLiteDatabase dbh = db.getWritableDatabase();
                    dbh.execSQL("update tb_nilai " +
                            "set username = '" + username +"' where no='6'");
                    mulaiKuis();
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    protected void mulaiKuis() {
        setUpWaktu();
        setUpSoal();
    }

    private void setUpWaktu() {
        mCountDownTimer = new CounterClass(detik, 1000);
        mCountDownTimer.start();
    }

    private void setUpSoal() {

        this.tunjukanPertanyaan(0, cekPertanyaan);


    }

    private void tunjukanPertanyaan(int urutan_soal_soal, boolean review) {
        btnSelesai.setEnabled(false);
        try {
            rg.clearCheck();
            Soal1 soal = new Soal1();
            soal = Section6.get(urutan_soal_soal);
            String pertanyaan = soal.getSoal();
            if (jawabanYgBenar[urutan_soal_soal] == -1) {
                jawabanYgBenar[urutan_soal_soal] = soal.getJwban();
            }

            int gambar = soal.getGambar();
            txtsoal.setText(pertanyaan.toCharArray(), 0, pertanyaan.length());
            img.setImageResource(gambar);
            rg.check(-1);
            String jwb_a = soal.getPil_a();
            rdA.setText(jwb_a.toCharArray(), 0,
                    jwb_a.length());
            String jwb_b = soal.getPil_b();
            rdB.setText(jwb_b.toCharArray(), 0,
                    jwb_b.length());
            String jwb_c = soal.getPil_c();
            rdC.setText(jwb_c.toCharArray(), 0,
                    jwb_c.length());
            String jwb_d = soal.getPil_d();
            rdD.setText(jwb_d.toCharArray(), 0,
                    jwb_d.length());


            Log.d("", jawabanYgDiPilih[urutan_soal_soal] + "");
            if (jawabanYgDiPilih[urutan_soal_soal] == 0)
                rg.check(R.id.radio0);
            if (jawabanYgDiPilih[urutan_soal_soal] == 1)
                rg.check(R.id.radio1);
            if (jawabanYgDiPilih[urutan_soal_soal] == 2)
                rg.check(R.id.radio2);
            if (jawabanYgDiPilih[urutan_soal_soal] == 3)
                rg.check(R.id.radio3);


            pasangLabelDanNomorUrut();

            if (urutan_soal_soal == (Section6.size() - 1)){
                btnNext.setEnabled(false);
                btnSelesai.setEnabled(true);
            }

            if (urutan_soal_soal == 0)
                btnPrev.setEnabled(false);

            if (urutan_soal_soal > 0)
                btnPrev.setEnabled(true);

            if (urutan_soal_soal < (Section6.size() - 1))
                btnNext.setEnabled(true);

        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
        }
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            aturJawaban_nya();
            // hitung berapa yg benar
            int jumlahJawabanYgBenar = 0;
            for (int i = 0; i < jawabanYgBenar.length; i++) {
                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i])){
                    jumlahJawabanYgBenar++;
                    if(jumlahJawabanYgBenar <=20 ){
                        int scorearr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
                        int a = jumlahJawabanYgBenar - 1;
                        score = scorearr[a];
                    }if(jumlahJawabanYgBenar==0){
                        score = 0;
                    }
                }
                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
                    noSalah = noSalah+" " + Integer.toString(i+1);
            }
            if(noSalah == ""){
                noSalah = "Benar semua";
            }
            else{
                noSalah = "No yang salah"+noSalah;
            }
            AlertDialog tampilKotakAlert;
            tampilKotakAlert = new AlertDialog.Builder(soalobjek.this).create();
            tampilKotakAlert.setMessage("Time is over!" +
                    "\nDo you want to the next section?");

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            intent = new Intent(context, soalobjek.class);
                            startActivity(intent);

                        }
                    });

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            cekPertanyaan = false;
                            closeContextMenu();
                        }
                    });

            tampilKotakAlert.show();
        }

        @SuppressLint("NewApi")
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            txtwaktu.setText(hms);
        }
    }

    private OnClickListener klikSelesai = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            // hitung berapa yg benar
            int jumlahJawabanYgBenar = 0;
            for (int i = 0; i < jawabanYgBenar.length; i++) {
                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i])){
                    jumlahJawabanYgBenar++;
                    if(jumlahJawabanYgBenar <=20 ){
                        int scorearr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,1,8,19,20};
                        int a = jumlahJawabanYgBenar - 1;
                        score = scorearr[a];
                    }if(jumlahJawabanYgBenar==0){
                        score = 0;
                    }
                }
                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
                    noSalah = noSalah+" " + Integer.toString(i+1);
            }
            if(noSalah == ""){
                noSalah = "Benar semua";
            }
            else{
                noSalah = "No yang salah"+noSalah;
            }
            final int nilai = (100/20)*jumlahJawabanYgBenar;
            AlertDialog tampilKotakAlert;
            tampilKotakAlert = new AlertDialog.Builder(soalobjek.this).create();
            tampilKotakAlert.setMessage("Jawaban yang benar : " + jumlahJawabanYgBenar +
                    " dari 20 soal. nilai = "+nilai);
            final int finalJumlahJawabanYgBenar = jumlahJawabanYgBenar;

            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Lihat Nilai",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            final Calendar c = Calendar.getInstance();
                            int day = c.get(Calendar.DAY_OF_MONTH);
                            int month = c.get(Calendar.MONTH);
                            int year = c.get(Calendar.YEAR);
                            String tglNow = Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year);
                            SQLiteDatabase dbh = db.getWritableDatabase();
                            dbh.execSQL("update tb_nilai " +
                                    "set tgl='"+ tglNow+
                                    "', jml_benar='"+ finalJumlahJawabanYgBenar +
                                    "', nilai_akhir='" +String.valueOf(nilai) +
                                    "' where no='6'");
                            intent = new Intent(context, LihatNilaiActivity.class);
                            intent.putExtra("nama_matkul", "Pemrograman Berorientasi Object");
                            startActivity(intent);

                        }
                    });

           // tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
             //       new DialogInterface.OnClickListener() {

               //         public void onClick(DialogInterface dialog, int which) {
                 //           cekPertanyaan = false;
                   //         closeContextMenu();
                     //   }
                   // });
            //Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
            tampilKotakAlert.show();

        }
    };

    private void aturJawaban_nya() {
        if (rdA.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 0;
        if (rdB.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 1;
        if (rdC.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 2;
        if (rdD.isChecked())
            jawabanYgDiPilih[urutanPertanyaan] = 3;


        Log.d("", Arrays.toString(jawabanYgDiPilih));
        Log.d("", Arrays.toString(jawabanYgBenar));

    }

    private OnClickListener klikBerikut = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan++;
            if (urutanPertanyaan >= Section6.size())
                urutanPertanyaan = Section6.size() - 1;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private OnClickListener klikSebelum = new OnClickListener() {
        public void onClick(View v) {
            aturJawaban_nya();
            urutanPertanyaan--;
            if (urutanPertanyaan < 0)
                urutanPertanyaan = 0;

            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
        }
    };

    private void pasangLabelDanNomorUrut() {
        txtno.setText("Question number-" + (urutanPertanyaan + 1) + " to "
                + Section6.size());
    }

    private AlertDialog noquestions() {
        AlertDialog builder = new AlertDialog.Builder(this)
                .setMessage("tidak ada pertanyaan .\napakah anda ingin ke soal lainnya ")
                .setCancelable(false)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, ListPaketSoal.class);
                        startActivity(intent);
                    }
                })
                //jika pilih no
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create();
        return builder;

    }

    private AlertDialog exitmenu() {
        AlertDialog builder = new AlertDialog.Builder(this)
                .setMessage("Apakah anda ingin keluar dari pemrograman berorientasi objek?")
                .setCancelable(false)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, ListPaketSoal.class);
                        startActivity(intent);
                    }
                })
                //jika pilih no
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create();
        return builder;

    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        AlertDialog dialog = exitmenu();
        dialog.show();

    }


}
