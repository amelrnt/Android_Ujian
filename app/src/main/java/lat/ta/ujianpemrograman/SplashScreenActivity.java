package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
    private final static int ON_UPDATE = 1;
    private final static int UP_TO_DATE = 0;
    private final static int NETWORK_ERROR = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow());
        setContentView(R.layout.activity_splash);

        /* Run New Thread At Background */
        new Handler().postDelayed(() -> {
            try {
                switch (checkUpdate()) {
                    case ON_UPDATE:
                        updatingPacket();
                        moveToNextActivity();
                        break;

                    case UP_TO_DATE:
                        moveToNextActivity();
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
        VersionRepository repository = new VersionRepository(this);
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

    private void updatingPacket() throws ExecutionException, InterruptedException  {
        PacketRepository repository = new PacketRepository(this);
        Future<List<Packet>> packets = repository.getPacket();
        List<Packet> list = packets.get();
        if (! list.isEmpty()) {
            repository.save(list);
            for (Packet packet: list) {
                updatingQuestion(packet.getId());
            }
        }
    }

    private void updatingQuestion(Integer packet) throws ExecutionException, InterruptedException  {
        packet = (packet == null) ? App.getVersionDetail(): packet;
        QuestionRepository repository = new QuestionRepository(this);
        Future<List<Question>> listFuture = repository.updateQuestion(packet);
        List<Question> list = listFuture.get();
        if (! list.isEmpty()) {
            repository.save(list);
        }
    }

    private void moveToNextActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        startActivity(intent);
        finish();
    }
}