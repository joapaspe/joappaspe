package joan.pastor.joappaspe;

import joan.pastor.joappaspe.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class QuestionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_question);

		final View controlsView = findViewById(R.id.response_buttons);
		final TextView  contentView = (TextView) findViewById(R.id.question);
        // Set up the user interaction to manually show or hide the system UI.
		
		
		// My staff
		Intent intent = getIntent();
		String question_message = intent.getStringExtra(OnDinem.QUESTION_MESSAGE);
		contentView.setText(question_message);
	}

	public void onAnswer(View v) {
		
		Intent intent = getIntent();
		switch(v.getId()) {
			case R.id.yes_button:
				intent.putExtra(OnDinem.ANSWER_MESSAGE, "YES");
				break;
			case R.id.no_button:
				intent.putExtra(OnDinem.ANSWER_MESSAGE, "NO");
				break;
		}
		
		setResult(RESULT_OK, intent);
		finish();
	}
}
