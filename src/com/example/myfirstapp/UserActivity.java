package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserActivity extends Activity {
	private UserDataSource userDataSource;
	private CategoryDataSource categoryDataSource;
	private SubjectDataSource subjectDataSource;
	private LibraryDataSource libraryDataSource;
	private TestDataSource testDataSource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_list);

		userDataSource = new UserDataSource(this);
		userDataSource.open();
		
		categoryDataSource = new CategoryDataSource(this);
		categoryDataSource.open();
		
		subjectDataSource = new SubjectDataSource(this);
		subjectDataSource.open();
		
		libraryDataSource = new LibraryDataSource(this);
		libraryDataSource.open();
		
		testDataSource = new TestDataSource(this);
		testDataSource.open();

		//User user = null;
		// Save the new comment to the database
		//user = datasource.createUser("ARERE", "erer");			
		
		List<User> values = userDataSource.getAllUsers();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		//ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
			//	android.R.layout.simple_list_item_1, values);
		//setListAdapter(adapter);
		
		
		ArrayList<String> message = new ArrayList<String>();
		for(int i=0;i<values.size();i++)
		{
			message.add(values.get(i).toString());
		}
		ListView remedyList = (ListView) findViewById(R.id.ListView_SymptomRemedy);
	    //remedyList.setTextFilterEnabled(true);
	    //ArrayAdapter<User> adapt = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, values);
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, message);
	    remedyList.setAdapter(adapt);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		//ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
		User user = null;
		user = userDataSource.createUser("Hai Dang", "12345678");
		Category cat = categoryDataSource.createCategory("12");
		Subject subject = subjectDataSource.createSubject("Chemistry");
		Library lib = libraryDataSource.createLibrary(subject.getId(), "chemistry\\1.jpg", "Chemistry\\answer\\1.jpg", cat.getId());
		Date currentDate = new Date(System.currentTimeMillis());
		Test test = testDataSource.createTest(user.getId(), lib.getId(), currentDate, 10, 1, 10);
		
		switch (view.getId()) {
		case R.id.add:
			//String[] names = new String[] { "Cool", "Very nice", "Hate it" };
			//String[] passwords = new String[] { "Cool", "Very nice", "Hate it" };
			//int nextInt = new Random().nextInt(3);
			// Save the new comment to the database
			//user = datasource.createUser(names[nextInt], passwords[nextInt]);			
			//adapter.add(user);			
			break;
		case R.id.delete:
			//if (getListAdapter().getCount() > 0) {
				//user = (User) getListAdapter().getItem(0);
				//datasource.deleteUser(user);
				//adapter.remove(user);
			//}
			break;
		}
		//adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();
		userDataSource.open();
		categoryDataSource.open();		
		subjectDataSource.open();
		libraryDataSource.open();
		testDataSource.open();
	}

	@Override
	protected void onPause() {
		super.onPause();
		userDataSource.close();
		categoryDataSource.close();		
		subjectDataSource.close();
		libraryDataSource.close();
		testDataSource.close();
	}

}
