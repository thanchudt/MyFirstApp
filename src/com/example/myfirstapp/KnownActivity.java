package com.example.myfirstapp;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class KnownActivity extends Activity{
	//private LibraryDataSource libraryDataSource;
	ImageView imageQuestion;
	ImageView imageAnswer;
	long libraryId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		//int  current_index = intent.getIntExtra(TestActivity.TEST_EXTRA_MESSAGE, 0);
		String question = intent.getStringExtra(TestActivity.TEST_EXTRA_MESSAGE_QUESTION);
		String answer = intent.getStringExtra(TestActivity.TEST_EXTRA_MESSAGE_ANSWER);
		libraryId = intent.getLongExtra(TestActivity.TEST_EXTRA_MESSAGE_LIBRARY_ID, 0);
		setContentView(R.layout.activity_known);			
		DrawImage(imageQuestion, R.id.imageView1, question);
		DrawImage(imageAnswer, R.id.imageView2, answer);
		//libraryDataSource = new LibraryDataSource(this);
		//libraryDataSource.open();
		
		//Library value = libraryDataSource.getLibrary(current_index);
		//if(value != null)
		//{
			//DrawImage(imageQuestion, R.id.imageView1, question);
			//DrawImage(imageAnswer, R.id.imageView2, answer);			
		//}		
	}
	
	private String GetImagePath(String imagePath)
	{
		return "@drawable/" + imagePath;
	}
	
	private void DrawImage(ImageView image, int imageViewId, String imagePath)
	{
		String path = GetImagePath(imagePath);
		int imageResource = getResources().getIdentifier(path, null, getPackageName());
		Drawable res = getResources().getDrawable(imageResource);
		image = (ImageView) findViewById(imageViewId);
		image.setImageDrawable(res);	
	}
	
	public void known(View view){
		TestDataSource testDataSource = new TestDataSource(this);
		testDataSource.open();		
		Date currentDate = new Date(System.currentTimeMillis());
		Test test = testDataSource.createTest(1, libraryId, currentDate, 10);
		testDataSource.close();
		finish();
	}
	
	public void unknown(View view){
		TestDataSource testDataSource = new TestDataSource(this);
		testDataSource.open();		
		Date currentDate = new Date(System.currentTimeMillis());
		Test test = testDataSource.createTest(1, libraryId, currentDate, 0);
		testDataSource.close();
		finish();
	}
}
