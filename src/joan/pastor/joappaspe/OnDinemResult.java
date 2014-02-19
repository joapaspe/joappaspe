package joan.pastor.joappaspe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class OnDinemResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_on_dinem_result);
		
		// My staff
		Intent intent = getIntent();
		String question_message = intent.getStringExtra(OnDinem.RESULT_MESSAGE);
		
		TextView result = (TextView) findViewById(R.id.result);
		result.setText(question_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.on_dinem_result, menu);
		return true;
	}

	
}
