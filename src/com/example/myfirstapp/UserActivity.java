package com.example.myfirstapp;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class UserActivity extends ListActivity{
	private UsersDataSource datasource;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        
        datasource = new UsersDataSource(this);
        datasource.open();
        
        List<User> values = datasource.getAllUsers();
        
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, 
        		android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);        
    }
    
    public void onClick(View view) {
    	@SuppressWarnings("unchecked")
    	ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();
    	User user = null;
    	switch(view.getId()) {
    	case R.id.add:
    		String[] users = new String[] { "peter@a.com", "Marry@b.com", "Laza@b.com"};
    		String[] passwords = new String[] { "peter@a.com", "Marry@b.com", "Laza@b.com"};
    		int nextInt = new Random().nextInt(3);
    		//save the new user to the database
    		user = datasource.createUser(users[nextInt], passwords[nextInt]);
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
    	adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
    	datasource.open();
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	datasource.close();
    	super.onPause();
    }       
}
