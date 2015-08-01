package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	private static final String TAG="CheatActivity";
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
	private static final String CHEAT_ANSWER="cheatBooleanAnswer";
	private static final String ANSWER_CHECKED="answerChecked";
	private boolean isAnswerChecked;
	

	private void setAnswerShownResult(boolean isAnswerShown){
		Log.d(TAG,"Passing Activity info " + isAnswerShown);
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK,data);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
	
		
		isAnswerChecked=false;
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}else{
					mAnswerTextView.setText(R.string.false_button);
				}
				
				isAnswerChecked=true;
				setAnswerShownResult(isAnswerChecked);
				Log.d(TAG,"Answer is checked " + isAnswerChecked);
			}
		});
		
		if (savedInstanceState != null){
		       mAnswerIsTrue=savedInstanceState.getBoolean(CHEAT_ANSWER);
		       isAnswerChecked=savedInstanceState.getBoolean(ANSWER_CHECKED);
		}
		
		setAnswerShownResult(isAnswerChecked);
		
	   
	}
	
	  @Override
	  public void onSaveInstanceState(Bundle savedInstanceState){
		  super.onSaveInstanceState(savedInstanceState);
	      savedInstanceState.putBoolean(CHEAT_ANSWER,mAnswerIsTrue);
	      savedInstanceState.putBoolean(ANSWER_CHECKED, isAnswerChecked);
	  }
	  
		

}
