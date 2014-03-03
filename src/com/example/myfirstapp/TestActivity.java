package com.example.myfirstapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class TestActivity extends Activity{
	public final static String TEST_EXTRA_MESSAGE = "com.example.myfirstapp.TEST_MESSAGE";
	Button buttonKnown;
	Button buttonUnknow;
	Button button;
	ImageView image;
	private LibraryDataSource libraryDataSource;
	private int current_index;
	List<Library> values;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test); 		
			
		libraryDataSource = new LibraryDataSource(this);
		libraryDataSource.open();
		
		values = libraryDataSource.getAllLibrarys();
		current_index = 0;
		//String path= "@drawable/myresource.png";
		String path= "@drawable/" + values.get(current_index).getQuestion();;
		int imageResource = getResources().getIdentifier(path, null, getPackageName());
		Drawable res = getResources().getDrawable(imageResource);
		image = (ImageView) findViewById(R.id.imageView1);
		image.setImageDrawable(res);		
		
		/*File fileObj = new  File(“/sdcard/Images/test_image.jpg”);
		if(fileObj .exists()){
		    Bitmap bitMapObj= BitmapFactory.decodeFile(fileObj .getAbsolutePath());
		    ImageView imgView= (ImageView) findViewById(R.id.imageviewTest);
		    imgView.setImageBitmap(bitMapObj);
		}
		
		image.setImageResource(values.get(current_index).getQuestion());*/
		addListenerOnButton();
	}
	
	public void addListenerOnButton() {
		
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
	}
				
	public void known(View view){
		Intent intent = new Intent(this, KnownActivity.class);
    	intent.putExtra(TEST_EXTRA_MESSAGE, current_index);
    	//startActivity(intent);
    	startActivityForResult(intent, 100); // 100 is some code to identify the returning result
	}
 	
	private int mod(int x, int y)
	{
	    int result = x % y;
	    return result < 0? result + y : result;
	}
	
	// Function to read the result from newly created activity
	@Override
    protected void onActivityResult(int requestCode,
                                     int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);        
    	current_index = mod((current_index+1),2);
		String path= "@drawable/" + values.get(current_index).getQuestion();;
		int imageResource = getResources().getIdentifier(path, null, getPackageName());
		Drawable res = getResources().getDrawable(imageResource);
		image.setImageDrawable(res);		        
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
