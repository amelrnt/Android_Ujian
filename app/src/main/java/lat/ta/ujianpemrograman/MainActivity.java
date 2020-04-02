package lat.ta.ujianpemrograman;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Model.ListPaketSoal;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static lat.ta.ujianpemrograman.Utils.createDialog;
import static lat.ta.ujianpemrograman.Utils.showMessage;

/**
 * @class MainActivity merupakan sebuah activity
 * yang akan tampil setelah [SplashScreen].
 *
 * Proses yang dilakukan :
 *
 */

public class MainActivity extends AppCompatActivity {

	@OnClick(R.id.btn_start) void start() {
		Intent intent = new Intent(getApplicationContext(), ListPaketSoal.class);
		startActivity(intent);
	}

	@OnClick(R.id.tv_about) void about () {
		Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		showMessage(this, "Version "+ App.getVersion());
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		createDialog(getApplicationContext(), "Apakah anda ingin keluar dari aplikasi ini ?")
				.show();
	}


// String fname = row.getString("audio");
// downloadFile(fname);

//	private void downloadFile(String filename){
//		String fileurl = "http://toefl.alatkimia.com/index.php/uploads/"+filename+".mp3";
//		Ion.with(getApplicationContext()).load(fileurl)
//				.progressBar(ProgressBar).progressDialog(pd).progress(new ProgressCallback() {
//
//			@Override
//			public void onProgress(long downloaded, long total) {
//				System.out.println("" + downloaded + " / " + total);
//			}
//		})
//				.write(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),filename+".mp3"))
//				.setCallback(new FutureCallback<File>() {
//
//					@Override
//					public void onCompleted(Exception e, File file) {
//						// TODO Auto-generated method stub
//
//					}
//				});
//
//	}

}



