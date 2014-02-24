package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserActivity extends Activity {
	private UsersDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_list);

		datasource = new UsersDataSource(this);
		datasource.open();

		//List<User> values = datasource.getAllUsers();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		//ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
			//	android.R.layout.simple_list_item_1, values);
		//setListAdapter(adapter);
		ArrayList<String> message = new ArrayList<String>();
		ListView remedyList = (ListView) findViewById(R.id.ListView_SymptomRemedy);
	    remedyList.setTextFilterEnabled(true);
	    ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, message);
	    remedyList.setAdapter(adapt);
	}

	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
		/*@SuppressWarnings("unchecked")
		ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
		User user = null;
		switch (view.getId()) {
		case R.id.add:
			String[] mails = new String[] { "Cool", "Very nice", "Hate it" };
			String[] passwords = new String[] { "Cool", "Very nice", "Hate it" };
			int nextInt = new Random().nextInt(3);
			// Save the new comment to the database
			user = datasource.createUser(mails[nextInt], passwords[nextInt]);			
			adapter.add(user);
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				user = (User) getListAdapter().getItem(0);
				datasource.deleteUser(user);
				adapter.remove(user);
			}
			break;
		}
		adapter.notifyDataSetChanged();*/
	}

	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}

	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

}
