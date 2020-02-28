package lat.ta.ujianpemrograman;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wenchao.jsql.JSQLite;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("ShowToast") public class MainActivity extends Activity {
	private static final ProgressBar ProgressBar = null;
	public static String paket ="";
	ImageButton btnstart;
	TextView txtAbout;
	Button btnver;
	ArrayList<HashMap<String, String>> list;
	HashMap<String, String> dr;
	private JSONParser jsonParser;
	private List<Soal1> dbver;
	private DBHelper db;
	private ProgressBar pb;
	private ProgressDialog pd;
	private String serverUrl = "http://10.0.32.150:8080/TA/";
	final Context context = this;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DBHelper(this);
		try {
			db.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		list = new ArrayList<HashMap<String, String>>();
		jsonParser = new JSONParser();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.activity_main);
		btnstart = (ImageButton) findViewById(R.id.buttonstart);
		btnstart.setOnClickListener(mulaiListener);
		txtAbout = (TextView) findViewById(R.id.textViewAbout);
		txtAbout.setOnClickListener(hlmnAbout);
		if(isNetworkAvailable(getApplicationContext())){
			Toast.makeText(getApplicationContext(), "Menyambungkan Ke Server...", Toast.LENGTH_LONG).show();
			checkUpdate();
		}else{
			Toast.makeText(getApplicationContext(), "Tidak Terhubung Dengan Server", Toast.LENGTH_LONG).show();
		}

	}

	private View.OnClickListener mulaiListener = new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.buttonstart:
					intent = new Intent(context, ListPaketSoal.class);
					startActivity(intent);
					break;

			}

		}
	};

	private View.OnClickListener hlmnAbout = new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
				case R.id.textViewAbout:
					intent = new Intent(context, About.class);
					startActivity(intent);
					break;

			}

		}
	};
	private AlertDialog exitmenu() {
		AlertDialog builder = new AlertDialog.Builder(this)
				.setMessage("Apakah anda ingin keluar dari aplikasi ini?")
				.setCancelable(false)//tidak bisa tekan tombol back
				//jika pilih yess
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_HOME);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				})
				//jika pilih no
				.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				})
				.create();
		return builder;

	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog dialog = exitmenu();
		dialog.show();

	}

	private void checkUpdate(){
		String url = serverUrl + "index.php/wservice/checkVersion";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		String ver;

		try {
			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
			JSONArray arr = jObj.getJSONArray("versi");
			JSONObject a = arr.getJSONObject(0);
			ver = a.getString("version");
			String pkt = a.getString("detail");
			Log.d("pkt",pkt);
			String sqlver = db.getdbversion();
			if(ver.equals(sqlver)){
				Toast.makeText(getApplicationContext(), "Soal Telah Diperbarui", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(getApplicationContext(), "Memperbarui Soal", Toast.LENGTH_LONG).show();
				db.updatedbver(ver);
				updatePaket();
				updateSoal(pkt);
				String urlfile = serverUrl + "index.php/wservice/getFile/"+pkt;
				JSONObject JOfile = jsonParser.getJSONFromUrl(urlfile, params);
				JSONArray fnarr = JOfile.getJSONArray("");
//				for (int i = 0; i < fnarr.length(); i++) {
//					JSONObject row = fnarr.getJSONObject(i);
////					String fname = row.getString("audio");
//					try {
////						downloadFile(fname);
//					} catch (Exception e) {
//						Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//					}
//				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private void updateSoal(String paket){
		String url = serverUrl + "index.php/wservice/updatesoal/"+paket;
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
			SQLiteDatabase sqdb = db.getWritableDatabase();
			JSQLite jsql = new JSQLite(jObj, sqdb);
			jsql.persist();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void updatePaket(){
		String url = serverUrl + "index.php/wservice/updatepaket";
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			JSONObject jObj = jsonParser.getJSONFromUrl(url, params);
			SQLiteDatabase sqdb = db.getWritableDatabase();
			JSQLite jsql = new JSQLite(jObj, sqdb);
			jsql.persist();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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

	public static boolean isNetworkAvailable(Context context) {
		return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
	}

}



