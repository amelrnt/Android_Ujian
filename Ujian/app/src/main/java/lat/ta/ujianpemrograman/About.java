package lat.ta.ujianpemrograman;


import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;



public class About extends Activity {

	final Context context = this;
	Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
        
    }
    
  	    @Override
	    public void onBackPressed() {
	        // TODO Auto-generated method stub
	    	Intent intent = new Intent(context, MainActivity.class);
        	startActivity(intent);
	    	
	    }
	
	
	}

   

