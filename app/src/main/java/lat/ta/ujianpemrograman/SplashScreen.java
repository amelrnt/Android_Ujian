package lat.ta.ujianpemrograman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import lat.ta.ujianpemrograman.repository.Repo;

public class SplashScreen extends Activity {

    private Repo repository = new Repo();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);
        /*menjalankan splash screen dan menu menggunakan delayed thread*/

        new Handler().post(() -> {
            repository.checkUpdate();
        });

        new Handler().postDelayed(new Thread(){
            @Override
            public void run() {
                super.run();

                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}