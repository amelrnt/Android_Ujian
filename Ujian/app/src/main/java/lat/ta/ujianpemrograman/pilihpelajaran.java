package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pilihpelajaran extends Activity {
Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    final Context context = this;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihpelajaran);

        btn1 = (Button) findViewById(R.id.bt_mulai);
        btn2 = (Button) findViewById(R.id.bt_nilaiakhir);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);



    }

    public void btn_OnClick(View v){
        Button x = (Button) v;
        if (x == btn1){
            Intent i = new Intent(getApplicationContext(), soalweb.class);
            startActivity(i);
        }else if(x == btn2){
           Intent i = new Intent(getApplicationContext(), soalmobile.class);
           startActivity(i);
        }else if(x == btn3){
            Intent i = new Intent(getApplicationContext(), soaljarkom.class);
            startActivity(i);
        }else if (x == btn4){
            Intent i = new Intent(getApplicationContext(), soalalgoritma.class);
            startActivity(i);
        }else if (x == btn5){
            Intent i = new Intent(getApplicationContext(), soalbasisdata.class);
            startActivity(i);
        }else if (x == btn6){
            Intent i = new Intent(getApplicationContext(), soalobjek.class);
            startActivity(i);
        }else if (x == btn7){
            Intent i = new Intent(getApplicationContext(), soalgui.class);
            startActivity(i);
        }
//        else if (x == btn8){
//            Intent i = new Intent(getApplicationContext(), NilaiActivity.class);
//            startActivity(i);
//}
    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(context, ListPaketSoal.class);
        startActivity(intent);

    }
}
