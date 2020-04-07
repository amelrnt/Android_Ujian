package lat.ta.ujianpemrograman;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_about);
    }
}

   

