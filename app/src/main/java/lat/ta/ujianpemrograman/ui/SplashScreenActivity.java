package lat.ta.ujianpemrograman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import lat.ta.ujianpemrograman.App;
import lat.ta.ujianpemrograman.R;
import lat.ta.ujianpemrograman.model.Packet;
import lat.ta.ujianpemrograman.model.Question;
import lat.ta.ujianpemrograman.repository.PacketRepository;
import lat.ta.ujianpemrograman.repository.QuestionRepository;
import lat.ta.ujianpemrograman.repository.VersionRepository;

import static lat.ta.ujianpemrograman.utils.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.utils.Utils.showMessage;

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

    private String TAG = SplashScreenActivity.class.getSimpleName();
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
        setContentView(R.layout.activity_splashscreen);

        /* Run New Thread At Background */
        new Handler().postDelayed(run, 3000);
    }

    private int checkUpdate() throws ExecutionException, InterruptedException {
        VersionRepository repository = new VersionRepository(this);
        Future<Integer> future = repository.checkVersionSync();
        if (future != null) {
            Integer version = future.get();
            Log.i(TAG, "checkUpdate: App version="+ App.getVersion());
            Log.i(TAG, "checkUpdate: Database version="+ version);

            if (App.getVersion() != version) {
                updatingPacket();
                App.setSharedPreferences(App.KEY_VERSION, version);
                showMessage(SplashScreenActivity.this, getResources().getString(R.string.info_version_updating));
            }

            return UP_TO_DATE;
        }

        if (App.getVersion() != -1) {
            return UP_TO_DATE;
        }

        return NETWORK_ERROR;
    }

    private void updatingPacket() throws ExecutionException, InterruptedException  {
        PacketRepository repository = new PacketRepository(this);
        Future<List<Packet>> future = repository.getAllSync();
        if (future != null) {
            List<Packet> packets = future.get();
            Log.i(TAG, "updatingPacket: Size Paket="+ packets.size());

            repository.save(packets).get();
            for (Packet packet: packets) {
                Log.i(TAG, "updatingPacket: Id Paket="+ packet.getId());
                Log.i(TAG, "updatingPacket: Nama Paket="+ packet.getId());
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
            Log.i(TAG, "updatingQuestion: Size Pertanyaan="+ questions.size());
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