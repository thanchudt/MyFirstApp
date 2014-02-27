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
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "Learner20140226.db";
	// Table Names	
	public static final String TABLE_USER = "user";
	public static final String TABLE_CATEGORY = "category";
	public static final String TABLE_SUBJECT = "subject";
	public static final String TABLE_LIBRARY = "library";	
	public static final String TABLE_TEST = "test";
	
	// Common column names
	public static final String COLUMN_ID = "_id";
	
	// USER Table - column names
	public static final String COLUMN_USER_NAME = "name";
	public static final String COLUMN_USER_PASSWORD = "password";
    
	// CATEGORY Table - column names
	public static final String COLUMN_CATEGORY_NAME = "name";
	public static final String COLUMN_CATEGORY_PARENT_ID = "parent_id";
		
	// SUBJECT Table - column names
	public static final String COLUMN_SUBJECT_NAME = "name";
		
	// LIBRARY Table - column names
	public static final String COLUMN_LIBRARY_SUBJECT_ID = "subject_id";
	public static final String COLUMN_LIBRARY_QUESTION = "question";
	public static final String COLUMN_LIBRARY_ANSWER = "answer";
	public static final String COLUMN_LIBRARY_CATEGORY_ID = "category_id";
		
	// Test Table - column names
	public static final String COLUMN_TEST_USER_ID = "user_id";
	public static final String COLUMN_TEST_LIBRARY_ID = "library_id";
	public static final String COLUMN_TEST_LAST_TEST_DATE = "last_test_date";
	public static final String COLUMN_TEST_MARK = "mark";
	public static final String COLUMN_TEST_TIMES = "times";
	public static final String COLUMN_TEST_TOTAL_MARK = "total_mark";
		
    // Table Create Statements
    // Users table create statement    
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
  	      + TABLE_USER + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  	      + COLUMN_USER_NAME + " text not null unique, "
  	      + COLUMN_USER_PASSWORD + " text not null"
  	      + ");";
    
    // Category table create statement    
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
  	      + TABLE_CATEGORY + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  	      + COLUMN_CATEGORY_NAME + " text not null, "
  	      + COLUMN_CATEGORY_PARENT_ID + " INTEGER null, "
  	      + "FOREIGN KEY ("+ COLUMN_CATEGORY_PARENT_ID +") REFERENCES " + TABLE_CATEGORY + " ("+ COLUMN_ID +")"
  	      + ");";
    
    // SUBJECT table create statement    
    private static final String CREATE_TABLE_SUBJECT = "CREATE TABLE "
  	      + TABLE_SUBJECT + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  	      + COLUMN_SUBJECT_NAME + " text not null "
  	      + ");";
    
    // LIBRARY table create statement    
    private static final String CREATE_TABLE_LIBRARY = "CREATE TABLE "
  	      + TABLE_LIBRARY + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  		  + COLUMN_LIBRARY_SUBJECT_ID + " INTEGER not null, "
  	      + COLUMN_LIBRARY_QUESTION + " text not null, "
  	      + COLUMN_LIBRARY_ANSWER + " text not null, "
  	      + COLUMN_LIBRARY_CATEGORY_ID + " INTEGER not null, "
  	      + "FOREIGN KEY ("+ COLUMN_LIBRARY_SUBJECT_ID +") REFERENCES " + TABLE_SUBJECT + " ("+ COLUMN_ID +"),"
  	      + "FOREIGN KEY ("+ COLUMN_LIBRARY_CATEGORY_ID +") REFERENCES " + TABLE_CATEGORY + " ("+ COLUMN_ID +")"
  	      + ");";
    
    
    // Test table create statement    
    private static final String CREATE_TABLE_TEST = "CREATE TABLE "
  	      + TABLE_TEST + "(" 
  		  + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, "
  	      + COLUMN_TEST_USER_ID + " INTEGER not null, "
  	      + COLUMN_TEST_LIBRARY_ID + " INTEGER not null, "
  	      + COLUMN_TEST_LAST_TEST_DATE + " DATETIME not null, "
  	      + COLUMN_TEST_MARK + " INTEGER not null, "
  	      + COLUMN_TEST_TIMES + " INTEGER not null, "
  	      + COLUMN_TEST_TOTAL_MARK + " INTEGER not null, "
  	      + "FOREIGN KEY ("+ COLUMN_TEST_USER_ID +") REFERENCES " + TABLE_USER + " ("+ COLUMN_ID +")," 
  	      + "FOREIGN KEY ("+ COLUMN_TEST_LIBRARY_ID +") REFERENCES " + TABLE_LIBRARY + " ("+ COLUMN_ID +")"  	      
  	      + ");";
    
    public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_CATEGORY);
		db.execSQL(CREATE_TABLE_SUBJECT);
		db.execSQL(CREATE_TABLE_LIBRARY);
		db.execSQL(CREATE_TABLE_TEST);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRARY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
		onCreate(db);
	}
	
}
