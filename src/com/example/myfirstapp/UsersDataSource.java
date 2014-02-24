package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsersDataSource {
	private SQLiteDatabase database;
	private UserDbHelper dbHelper;
	private String[] allColumns = { UserDbHelper.COLUMN_ID, 
			UserDbHelper.COLUMN_MAIL, UserDbHelper.COLUMN_PASSWORD
	};
	private static final int COLUMN_ID_INDEX = 0;
	private static final int COLUMN_MAIL_INDEX = 1;
	private static final int COLUMN_PASSWORD_INDEX = 2;
	
	public UsersDataSource(Context context){
		dbHelper = new UserDbHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public User createUser(String mail, String password) {
		ContentValues values = new ContentValues();
		values.put(UserDbHelper.COLUMN_MAIL, mail);
		values.put(UserDbHelper.COLUMN_PASSWORD, password);
		long insertId = database.insert(UserDbHelper.TABLE_USER, null, values);
		Cursor cursor = database.query(UserDbHelper.TABLE_USER, 
				allColumns, UserDbHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		User newUser = cursorToUser(cursor);
		cursor.close();
		return newUser;
	}

	public void deleteUser(User user){
		long id = user.getId();
		System.out.println("User deleted with id: " + id);
		database.delete(UserDbHelper.TABLE_USER, UserDbHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		
		Cursor cursor = database.query(UserDbHelper.TABLE_USER, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		
		//make sure to close the cursor
		cursor.close();
		return users;
	}
	
	private User cursorToUser(Cursor cursor) {
		User user = new User();
		user.setId(cursor.getLong(COLUMN_ID_INDEX));
		user.setMail(cursor.getString(COLUMN_MAIL_INDEX));
		user.setPassword(cursor.getString(COLUMN_PASSWORD_INDEX));
		return null;
	}
}
