package com.example.myfirstapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TestActivity extends Activity{
	public final static String TEST_EXTRA_MESSAGE_QUESTION = "com.example.myfirstapp.TEST_EXTRA_MESSAGE_QUESTION";
	public final static String TEST_EXTRA_MESSAGE_ANSWER = "com.example.myfirstapp.TEST_EXTRA_MESSAGE_ANSWER";
	public final static String TEST_EXTRA_MESSAGE_LIBRARY_ID= "com.example.myfirstapp.TEST_EXTRA_MESSAGE_LIBRARY_ID";
	Button buttonKnown;
	Button buttonUnknow;
	Button button;
	ImageView image;
	ImageView imageViewDifficultLevel;
	TextView textViewDiffLevel;
	private LibraryDataSource libraryDataSource;
	private TestDataSource testDataSource;
	private int current_index;
	List<Library> values;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test); 		
			
		libraryDataSource = new LibraryDataSource(this);
		libraryDataSource.open();
		
		values = libraryDataSource.getAllLibraries();
		current_index = 0;
		//String path= "@drawable/myresource.png";
		image = (ImageView) findViewById(R.id.imageView1);
		imageViewDifficultLevel = (ImageView) findViewById(R.id.imageViewDifficultLevel);
		textViewDiffLevel = (TextView) findViewById(R.id.textViewDifficultLevel);
		DrawQuestion();		
		
		/*File fileObj = new  File(“/sdcard/Images/test_image.jpg”);
		if(fileObj .exists()){
		    Bitmap bitMapObj= BitmapFactory.decodeFile(fileObj .getAbsolutePath());
		    ImageView imgView= (ImageView) findViewById(R.id.imageviewTest);
		    imgView.setImageBitmap(bitMapObj);
		}
		
		image.setImageResource(values.get(current_index).getQuestion());*/
		//addListenerOnButton();
	}

	private void DrawQuestion() {
		Utility utility = new Utility();
		current_index = utility.mod((current_index+1),values.size());
		String path= "@drawable/" + values.get(current_index).getQuestion();
		int imageResource = getResources().getIdentifier(path, null, getPackageName());
		Drawable res = getResources().getDrawable(imageResource);		
		image.setImageDrawable(res);
		testDataSource = new TestDataSource(this);
		testDataSource.open();
		Test test = testDataSource.getTest(1, values.get(current_index).getId());
		testDataSource.close();
		if(test == null)
		{
			imageViewDifficultLevel.setImageResource(R.drawable.zero_star);			
		}else
		{
			long hard = 0;
		
			if(test.getTimes() != 0) 
				hard = test.getTotalMark() / test.getTimes();
			if(hard > 9)
				imageViewDifficultLevel.setImageResource(R.drawable.zero_star);
			else if(hard > 8)
				imageViewDifficultLevel.setImageResource(R.drawable.one_star);
			else if(hard > 6)
				imageViewDifficultLevel.setImageResource(R.drawable.two_star);
			else if(hard > 4)
				imageViewDifficultLevel.setImageResource(R.drawable.three_star);
			else if(hard > 2)
				imageViewDifficultLevel.setImageResource(R.drawable.four_star);
			else 
				imageViewDifficultLevel.setImageResource(R.drawable.five_star);
		}
	}
	
	/*public void addListenerOnButton() {
		
		image = (ImageView) findViewById(R.id.imageView1);
 
		button = (Button) findViewById(R.id.btnChangeImage);
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				//image.setImageResource(R.drawable.cong_thuc_hoa_answer);
				current_index = mod((current_index+1),2);
				String path= "@drawable/" + values.get(current_index).getQuestion();;
				int imageResource = getResources().getIdentifier(path, null, getPackageName());
				Drawable res = getResources().getDrawable(imageResource);
				image.setImageDrawable(res);				
			}
 
		});
	}*/
				
	public void known(View view){
		Intent intent = new Intent(this, KnownActivity.class);
    	intent.putExtra(TEST_EXTRA_MESSAGE_QUESTION, values.get(current_index).getQuestion());
    	intent.putExtra(TEST_EXTRA_MESSAGE_ANSWER, values.get(current_index).getAnswer());
    	intent.putExtra(TEST_EXTRA_MESSAGE_LIBRARY_ID, values.get(current_index).getId());
    	//startActivity(intent);
    	startActivityForResult(intent, 100); // 100 is some code to identify the returning result
	}
 	
	// Function to read the result from newly created activity
	@Override
    protected void onActivityResult(int requestCode,
                                     int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);  
        DrawQuestion();    		       
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		libraryDataSource.open();		
	}

	@Override
	protected void onPause() {
		super.onPause();
		libraryDataSource.close();		
	}
}
