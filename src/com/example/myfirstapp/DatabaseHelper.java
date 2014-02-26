package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	// Log tag
	private static final String LOG = DatabaseHelper.class.getName();
	// If you change the database schema, you must increment the database version.
	// Database Version
	private static final int DATABASE_VERSION = 3;
	// Database Name
	private static final String DATABASE_NAME = "Learner.db";
	// Table Names	
	public static final String TABLE_USER = "users";
	
	// Common column names
	public static final String COLUMN_ID = "_id";
	
	// USERS Table - column names
	public static final String COLUMN_USER_NAME = "name";
	public static final String COLUMN_USER_PASSWORD = "password";
    
    // Table Create Statements
    // Users table create statement    
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
  	      + TABLE_USER + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  	      + COLUMN_USER_NAME + " text not null unique, "
  	      + COLUMN_USER_PASSWORD + " text not null"
  	      + ");";
    
    public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
	
}
