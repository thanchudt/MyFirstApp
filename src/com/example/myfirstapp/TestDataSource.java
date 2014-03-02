package com.example.myfirstapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TestDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_TEST_USER_ID, DatabaseHelper.COLUMN_TEST_LIBRARY_ID, 
			DatabaseHelper.COLUMN_TEST_LAST_TEST_DATE, DatabaseHelper.COLUMN_TEST_MARK, DatabaseHelper.COLUMN_TEST_TIMES, DatabaseHelper.COLUMN_TEST_TOTAL_MARK
	};
	private static final int COLUMN_ID_INDEX = 0;
	private static final int COLUMN_TEST_USER_ID_INDEX = 1;
	private static final int COLUMN_TEST_LIBRARY_ID_INDEX = 2;
	private static final int COLUMN_TEST_LAST_TEST_DATE_INDEX = 3;
	private static final int COLUMN_TEST_MARK_INDEX = 4;
	private static final int CCOLUMN_TEST_TIMES_INDEX = 5;
	private static final int COLUMN_TEST_TOTAL_MARK_INDEX = 6;
	
	public TestDataSource(Context context){
		dbHelper = new DatabaseHelper(context);		
	}
	
	public void open() throws SQLException {
		//database = dbHelper.getWritableDatabase();
		database = dbHelper.getDb();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	@SuppressLint("SimpleDateFormat")
	public Test createTest(long user_id, long library_id, Date last_test_date, long mark, long times, long total_mark) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_TEST_USER_ID, user_id);
		values.put(DatabaseHelper.COLUMN_TEST_LIBRARY_ID, library_id);
		values.put(DatabaseHelper.COLUMN_TEST_LAST_TEST_DATE, dateFormat.format(last_test_date));
		values.put(DatabaseHelper.COLUMN_TEST_MARK, mark);
		values.put(DatabaseHelper.COLUMN_TEST_TIMES, times);
		values.put(DatabaseHelper.COLUMN_TEST_TOTAL_MARK, times);
		
		long insertId = database.insert(DatabaseHelper.TABLE_TEST, null, values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_TEST, 
				allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Test newTest = cursorToTest(cursor);
		cursor.close();
		return newTest;
	}

	public void deleteTest(Test test){
		long id = test.getId();
		System.out.println("Test deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_TEST, DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Test> getAllTests(){
		List<Test> tests = new ArrayList<Test>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_TEST, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Test test = cursorToTest(cursor);
			tests.add(test);
			cursor.moveToNext();
		}
		
		//make sure to close the cursor
		cursor.close();
		return tests;
	}
	
	@SuppressLint("SimpleDateFormat")
	private Test cursorToTest(Cursor cursor) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Test test = new Test();
		test.setId(cursor.getLong(COLUMN_ID_INDEX));		
		test.setUserId(cursor.getLong(COLUMN_TEST_USER_ID_INDEX));
		test.setLibraryId(cursor.getLong(COLUMN_TEST_LIBRARY_ID_INDEX));	
		try {
			test.setLastTestDate(dateFormat.parse(cursor.getString(COLUMN_TEST_LAST_TEST_DATE_INDEX)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.setMark(cursor.getLong(COLUMN_TEST_MARK_INDEX));
		test.setTimes(cursor.getLong(CCOLUMN_TEST_TIMES_INDEX));
		test.setTotalMark(cursor.getLong(COLUMN_TEST_TOTAL_MARK_INDEX));		
		
		return test;
	}
}
