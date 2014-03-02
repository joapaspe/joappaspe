package joan.pastor.joappaspe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import joan.pastor.joappaspe.Restaurants.RESTAURANTS;
import joan.pastor.joappaspe.Restaurants;

public class OnDinemResult extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_on_dinem_result);
		
		// My staff
		Intent intent = getIntent();
		RESTAURANTS eDecision = (RESTAURANTS) intent.getSerializableExtra(OnDinem.RESULT_MESSAGE);
		
		Restaurants rDecision;
		try {
			rDecision = Restaurants.restaurants_resources.get(eDecision);
		}
		catch(Exception e){
			rDecision = Restaurants.restaurants_resources.get(RESTAURANTS.VELLA);
		}
		
		TextView result = (TextView) findViewById(R.id.result);
		result.setText(rDecision.idName);
		
		ImageView img = (ImageView) findViewById(R.id.picture);
		img.setImageResource(rDecision.idImg);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.on_dinem_result, menu);
		return true;
	}

	
}
