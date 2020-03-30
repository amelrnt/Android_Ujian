package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.model.Version;
import lat.ta.ujianpemrograman.repository.VersionRepository;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.Utils.showMessage;

/**
 * @class SplashScreenActivity merupakan sebuah activity yang berjalan
 * pada saat aplikasi pertama kali dijalankan.
 *
 * Proses yang dilakukan :
 * - [Async] Melakukan check update berdasarkan versi.
 * - [Async] Melakukan update soal jika versi berbeda.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private final static int ON_UPDATE = 1;
    private final static int UP_TO_DATE = 0;
    private final static int NETWORK_ERROR = -1;

    private VersionRepository repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_splash);

        repository = new VersionRepository(this);

        /* Run New Thread At Background */
        new Handler().postDelayed(() -> {
            try {
                switch (checkUpdate()) {
                    case ON_UPDATE:
                        break;

                    case UP_TO_DATE:
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        Intent intent = new Intent(
                                SplashScreenActivity.this, MainActivity.class);

                        startActivity(intent);
                        finish();
                        break;

                    case NETWORK_ERROR:
                        break;
                }

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, 3000);
    }

    /**
     * Checking Update
     *
     * @return boolean
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private int checkUpdate() throws ExecutionException, InterruptedException {
        Future<Version> future = repository.checkVersionSync();
        if (future != null) {
            Version version = future.get();

            if (App.getVersion() != version.getVersion()) {
                App.setSharedPreferences(App.KEY_VERSION, version.getVersion());
                App.setSharedPreferences(App.KEY_VERSION_DETAIL, version.getDetail());
                showMessage(this, getResources().getString(R.string.info_version_updating));

                return ON_UPDATE;
            }

            return UP_TO_DATE;
        }

        return NETWORK_ERROR;
    }

    private void updatingQuestion() {

    }

    private void updating() {

    }
}