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
		createDialog(getApplicationContext(), "Apakah anda ingin keluar dari aplikasi ini ?")
				.show();
	}

//	private void checkUpdate(){
//		String url = serverUrl + "index.php/wservice/checkVersion";
//		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//		String ver;
//
//		try {
//			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
//			JSONArray arr = jObj.getJSONArray("versi");
//			JSONObject a = arr.getJSONObject(0);
//			ver = a.getString("version");
//			String pkt = a.getString("detail");
//			Log.d("pkt",pkt);
//			String sqlver = db.getdbversion();
//			if(ver.equals(sqlver)){
//				Toast.makeText(getApplicationContext(), "Soal Telah Diperbarui", Toast.LENGTH_LONG).show();
//			}else{
//				Toast.makeText(getApplicationContext(), "Memperbarui Soal", Toast.LENGTH_LONG).show();
//				db.updatedbver(ver);
//				updatePaket();
//				updateSoal(pkt);
//				String urlfile = serverUrl + "index.php/wservice/getFile/"+pkt;
//				JSONObject JOfile = jsonParser.getJSONFromUrl(urlfile, params);
//				JSONArray fnarr = JOfile.getJSONArray("");
////				for (int i = 0; i < fnarr.length(); i++) {
////					JSONObject row = fnarr.getJSONObject(i);
//////					String fname = row.getString("audio");
////					try {
//////						downloadFile(fname);
////					} catch (Exception e) {
////						Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
////					}
////				}
//
//			}
//		} catch (Exception e) {
//			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//		}
//	}

//	private void updateSoal(String paket){
//		String url = serverUrl + "index.php/wservice/updatesoal/"+paket;
//		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//		try {
//			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
//			SQLiteDatabase sqdb = db.getWritableDatabase();
//			JSQLite jsql = new JSQLite(jObj, sqdb);
//			jsql.persist();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

//	private void updatePaket(){
//		String url = serverUrl + "index.php/wservice/updatepaket";
//		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//		try {
//			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
//			SQLiteDatabase sqdb = db.getWritableDatabase();
//			JSQLite jsql = new JSQLite(jObj, sqdb);
//			jsql.persist();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

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



