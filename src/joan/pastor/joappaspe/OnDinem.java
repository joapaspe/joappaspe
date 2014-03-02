package joan.pastor.joappaspe;

import java.io.Serializable;
import java.util.HashMap;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import joan.pastor.joappaspe.Restaurants.RESTAURANTS;

public class OnDinem extends Activity {

	
	
  	public class DecisionNode implements Serializable {

		private static final long serialVersionUID = 2253335149150517167L;
		String questionId;
		boolean final_node;
		Restaurants.RESTAURANTS result;
		DecisionNode yes_answer;
		DecisionNode no_answer;

		// Reference to the type of node
		tree_node t;
		public DecisionNode(String q, tree_node t) {
			
			questionId = q;
			final_node = false;
			result = RESTAURANTS.NONE;
			this.t = t;
			
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

	// For serializing the state
	private enum tree_node {NODE1, NODE2, NODE3, NODE4, NODE5, KEBAB, VELLA, BK, CONSERVA };
	
	protected HashMap<tree_node, DecisionNode> tree_state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		
		// Build the tree
		DecisionNode node1 = new DecisionNode(getString(R.string.outside), tree_node.NODE1);
		DecisionNode node2 = new DecisionNode(getString(R.string.marcos), tree_node.NODE2);
		DecisionNode node3 = new DecisionNode(getString(R.string.marcos), tree_node.NODE3);
		DecisionNode node4 = new DecisionNode(getString(R.string.marcos_vella), tree_node.NODE4);
		DecisionNode node5 = new DecisionNode(getString(R.string.pinxos), tree_node.NODE5);
		DecisionNode kebab = new DecisionNode(RESTAURANTS.KEBAB);
		DecisionNode vella = new DecisionNode(RESTAURANTS.VELLA);
		DecisionNode bk = new DecisionNode(RESTAURANTS.BURGER_KING);
		DecisionNode conserva = new DecisionNode(RESTAURANTS.CONSERVA);

		tree_state = new HashMap<tree_node, DecisionNode>();
		tree_state.put(tree_node.NODE1, node1);
		tree_state.put(tree_node.NODE2, node2);
		tree_state.put(tree_node.NODE3, node3);
		tree_state.put(tree_node.NODE4, node4);
		tree_state.put(tree_node.NODE5, node5);
		tree_state.put(tree_node.KEBAB, kebab);
		tree_state.put(tree_node.VELLA, vella);
		tree_state.put(tree_node.CONSERVA, conserva);
		tree_state.put(tree_node.BK, bk);
		
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
	    	intent.putExtra(RESULT_MESSAGE, actual.result);
	    	
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
	 
	// Serialize state
	  private static class State implements Serializable {
		    private static final long serialVersionUID = 1;
			private static final String STATE = "OnDinem.STATE";

		    private tree_node act;

		    public State(DecisionNode act) {
		        this.act = act.t;
		    }

		    public tree_node getAct() {
		        return act;
		    }
		}

		@Override
		protected void onSaveInstanceState(Bundle outState) {
		    State s = new State(actual);

		    outState.putSerializable(State.STATE, s);

		    super.onSaveInstanceState(outState);
		}

		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
		    super.onRestoreInstanceState(savedInstanceState);

		    State s = (State) savedInstanceState.getSerializable(State.STATE);

		    // Gets the tree node and with the hash_map, select the current node of the Decision Tree
		    actual = tree_state.get(s.getAct());
		}

}
