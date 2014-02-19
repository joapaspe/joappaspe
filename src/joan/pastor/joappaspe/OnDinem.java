package joan.pastor.joappaspe;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class OnDinem extends Activity {

	public enum RESTAURANTS {
		NONE, VELLA, CONSERVA, BURGUER_KING, KEBAB
	};

	public class DecisionNode {

		String questionId;
		boolean final_node;
		RESTAURANTS result;
		DecisionNode yes_answer;
		DecisionNode no_answer;

		public DecisionNode(String q) {
			// TODO Auto-generated constructor stub
			questionId = q;
			final_node = false;
			result = RESTAURANTS.NONE;

		}

		public DecisionNode(RESTAURANTS result) {
			questionId = null;
			final_node = true;
			this.result = result;
		}

		public void setChildren(DecisionNode yes, DecisionNode no) {
			yes_answer = yes;
			no_answer = no;

		}
		
		public boolean isFinal() {
			return final_node;
			
		}

	}

	private DecisionNode actual;
	public final static String QUESTION_MESSAGE = "question";
	public final static String ANSWER_MESSAGE = "answer";
	public final static String RESULT_MESSAGE = "result";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		// TextView question = (TextView) findViewById(R.id.question);
		// question.setText("Ojete");

		// Build the tree
		DecisionNode node1 = new DecisionNode(getString(R.string.outside));
		DecisionNode node2 = new DecisionNode(getString(R.string.marcos));
		DecisionNode node3 = new DecisionNode(getString(R.string.marcos));
		DecisionNode node4 = new DecisionNode(getString(R.string.marcos_vella));
		DecisionNode node5 = new DecisionNode(getString(R.string.pinxos));
		DecisionNode kebab = new DecisionNode(RESTAURANTS.KEBAB);
		DecisionNode vella = new DecisionNode(RESTAURANTS.VELLA);
		DecisionNode bk = new DecisionNode(RESTAURANTS.BURGUER_KING);
		DecisionNode conserva = new DecisionNode(RESTAURANTS.CONSERVA);

		node1.setChildren(node3, node2);
		node2.setChildren(node4, node5);
		node3.setChildren(kebab, bk);
		node4.setChildren(conserva, vella);
		node5.setChildren(vella, conserva);

		actual = node1;

		Intent intent = new Intent(this, QuestionActivity.class);
		intent.putExtra(QUESTION_MESSAGE, actual.questionId);

		
		int requestCode = 0;
		startActivityForResult(intent, requestCode);

		Log.i("JOAN", "BIEEEN");
	}
	
	
	
	
	// Listen for results.
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		super.onActivityResult(requestCode, resultCode, data);
	    // See which child activity is calling us back.
	    Log.i("JOAN", "FINISH");
	    
	    String answer = data.getStringExtra(ANSWER_MESSAGE);
	    
	    if(answer.equals("YES")	   )
	    		actual = actual.yes_answer;
	    else
	    		actual = actual.no_answer;
	    
	    if (actual.isFinal()) {
	    	RESTAURANTS decision = actual.result;
	    	
	    	Intent intent = new Intent(this, OnDinemResult.class);
	    	intent.putExtra(RESULT_MESSAGE, decision.toString());
	    	startActivity(intent);
	    	Log.i("DECISION", decision.toString());
	    	finish();
	    }
	    else {
	    	Intent intent = new Intent(this, QuestionActivity.class);
			intent.putExtra(QUESTION_MESSAGE, actual.questionId);

			int rc = 1;
			startActivityForResult(intent, rc);
	    	}
	    }
	    

}
