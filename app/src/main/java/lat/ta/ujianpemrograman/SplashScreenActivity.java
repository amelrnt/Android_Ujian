package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.model.Version;
import lat.ta.ujianpemrograman.repository.PacketRepository;
import lat.ta.ujianpemrograman.repository.QuestionRepository;
import lat.ta.ujianpemrograman.repository.VersionRepository;

import static lat.ta.ujianpemrograman.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.Utils.showMessage;

/**
 * @class SplashScreenActivity merupakan sebuah activity yang berjalan
 * pada saat aplikasi pertama kali dijalankan.
 *
 * Proses yang dilakukan :
 * - [Sync] Check Koneksi.
 * - [Sync] Check Versi.
 * - [Sync] Melakukan check update berdasarkan versi.
 * - [Sync] Melakukan update soal jika versi berbeda.
 */

public class SplashScreenActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    private final static int UP_TO_DATE = 1;
    private final static int NETWORK_ERROR = -1;

    private Runnable run = () -> {
        try {
            switch (checkUpdate()) {
                case UP_TO_DATE:
                    moveToNextActivity();
                    break;

                case NETWORK_ERROR:
                    break;
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_splash);

        /* Run New Thread At Background */
        new Handler().postDelayed(run, 3000);
    }

    private int checkUpdate() throws ExecutionException, InterruptedException {
        VersionRepository repository = new VersionRepository(this);
        Future<Version> future = repository.checkVersionSync();
        if (future != null) {
            Version version = future.get();
            Log.d(TAG, "checkUpdate: version="+ version.getVersion());

            if (App.getVersion() != version.getVersion()) {
                updatingPacket();
                App.setSharedPreferences(App.KEY_VERSION, version.getVersion());
                App.setSharedPreferences(App.KEY_VERSION_DETAIL, version.getDetail());
                showMessage(SplashScreenActivity.this, getResources().getString(R.string.info_version_updating));
            }

            return UP_TO_DATE;
        }

        return NETWORK_ERROR;
    }

    private void updatingPacket() throws ExecutionException, InterruptedException  {
        PacketRepository repository = new PacketRepository(this);
        Future<List<Packet>> future = repository.getAllSync();
        if (future != null) {
            List<Packet> packets = future.get();
            Log.d(TAG, "updatingPacket: packets="+ packets.size());

            repository.save(packets).get();
            for (Packet packet: packets) {
                updatingQuestion(packet.getId());
            }
        }
    }

    private void updatingQuestion(Integer packet) throws ExecutionException, InterruptedException  {
        QuestionRepository repository = new QuestionRepository(this);
        Future<List<Question>> future = repository.updateQuestion(packet);
        if (future == null) return;

        List<Question> questions = future.get();
        if (! questions.isEmpty()) {
            Log.d(TAG, "updatingQuestion: questions="+ questions.size());
            repository.save(questions);
        }
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        startActivity(intent);
        finish();
    }
}