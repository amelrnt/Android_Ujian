package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static lat.ta.ujianpemrograman.Utils.createDialog;
import static lat.ta.ujianpemrograman.Utils.setFullScreen;
import static lat.ta.ujianpemrograman.Utils.showMessage;

/**
 * @class MainActivity merupakan sebuah activity
 * yang akan tampil setelah [SplashScreen].
 *
 * Proses yang dilakukan :
 * - Handle Button Start
 * - Handle TextView OnClick
 */

public class MainActivity extends AppCompatActivity {

	@OnClick(R.id.btn_start) void start() {
		Intent intent = new Intent(getApplicationContext(), ListPacketActivity.class);
		startActivity(intent);
	}

	@OnClick(R.id.tv_about) void about () {
		Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullScreen(getWindow());
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		showMessage(this, "Version "+ App.getVersion());
	}

	@Override
	public void onBackPressed() {
		String message = getResources().getString(R.string.warning_back);
		createDialog(getApplicationContext(), message).show();
	}
}



