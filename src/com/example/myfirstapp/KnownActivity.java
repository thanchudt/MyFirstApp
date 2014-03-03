package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KnownActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		int  current_index = intent.getIntExtra(TestActivity.TEST_EXTRA_MESSAGE, 0);		
		setContentView(R.layout.activity_known);			
	}
	
	public void known(View view){
		finish();
	}
	
	public void unknown(View view){
		finish();
	}
}
