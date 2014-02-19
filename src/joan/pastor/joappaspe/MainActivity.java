package joan.pastor.joappaspe;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;



public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onDinem(View v) {
            // Perform action on click
		Intent intent = new Intent(this, OnDinem.class);
		startActivity(intent);
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
	    // See which child activity is calling us back.
	    Log.i("JOAN", "MAIN");

	    
//	    if (current.isFinal()) {
//	    	RESTAURANTS decision = current.result;
//	    	Log.i("DECISION", decision.toString());
//	    }
//	    else {
//	    	Intent intent = new Intent(this, QuestionActivity.class);
//			intent.putExtra(QUESTION_MESSAGE, current.questionId);
//
//			int rc = 1;
//			startActivityForResult(intent, rc);
//
//	    	
//	    	}
	    }
	    
	public void exit(View v) {
	 // TODO Auto-generated method stub
		finish();
		System.exit(0);
	}
}
