package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDbHelper extends SQLiteOpenHelper{
	public static final String TABLE_USER = "user";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_MAIL = "mail";
	  public static final String COLUMN_PASSWORD = "password";
	  
	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Learner.db"; 	

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	      + TABLE_USER + "(" 
		  + COLUMN_ID + " integer primary key autoincrement, "
	      + COLUMN_MAIL + " text not null unique, "
	      + COLUMN_PASSWORD + " text not null"
	      + ");";
	
	public UserDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(UserDbHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
	
}
