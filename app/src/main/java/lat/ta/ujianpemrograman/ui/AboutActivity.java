package lat.ta.ujianpemrograman.ui;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lat.ta.ujianpemrograman.R;

import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_about);
    }
}

   

