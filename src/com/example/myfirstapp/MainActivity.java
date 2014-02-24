package com.example.myfirstapp;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {
	private UsersDataSource datasource;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendMessage(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }    
}
