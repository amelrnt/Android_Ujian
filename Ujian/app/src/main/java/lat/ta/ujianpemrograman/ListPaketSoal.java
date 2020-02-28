package lat.ta.ujianpemrograman;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.IOException;



public class ListPaketSoal extends Activity {
	private DBHelper dbhelper;
	private SQLiteDatabase db = null;
	public ListView listContent = null; 
	public static String paket ="";
	final Context context = this;
	Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DBHelper(this);
        try {
     	   dbhelper.createDataBase();
     	  } catch (IOException e) {
     	   // TODO Auto-generated catch block
     	   e.printStackTrace();
     	  }
        db = dbhelper.getWritableDatabase(); 
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        	
        	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                       
        setContentView(R.layout.activity_listpaket);
        listContent = (ListView) findViewById(R.id.listSoal); 
        isDataListView();
        
    }
    
    @SuppressWarnings("deprecation")
	private void isDataListView() { 
    	Cursor paketCursor;
    	paketCursor = dbhelper.fetchAllPaket(db);
    	startManagingCursor(paketCursor);
    	String[] from = new String[] {DBHelper.NAMA_PAKET};
    	int[] to = new int[] {R.id.isiList};
    	SimpleCursorAdapter paketAdapter = new SimpleCursorAdapter(this, R.layout.activity_listrow, paketCursor, from, to);  
    	listContent.setAdapter(paketAdapter);	
    	
    	listContent.setOnItemClickListener(new OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                    //GET ID FROM DATABASE
            	 //Toast.makeText(getBaseContext(), id + "", Toast.LENGTH_LONG).show();
            	 paket =  Long.toString(id);
            	 intent = new Intent(context, pilihaction.class);
            	 startActivity(intent);
                    
             }
         });
    	
    }
    
    
    /*private View.OnClickListener mulaiListener = new View.OnClickListener() {
		 
		@Override
		public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonmulai1:
			intent = new Intent(context, soalweb.class);
			paket = "1";
			startActivity(intent);
			break;
		case R.id.buttonmulai2:
			intent = new Intent(context, soalweb.class);
			paket = "2";
			startActivity(intent);
			break;
		
		}
			
		}
	};*/
	
		 
	    @Override
	    public void onBackPressed() {
	        // TODO Auto-generated method stub
	    	Intent intent = new Intent(context, MainActivity.class);
        	startActivity(intent);
	    	
	    }
	
	
	}

   

