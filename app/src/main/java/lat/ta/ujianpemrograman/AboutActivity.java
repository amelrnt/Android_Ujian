package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.os.Bundle;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

   

