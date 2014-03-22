package com.example.myfirstapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LibraryDataSource {
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allColumns = { DatabaseHelper.COLUMN_ID,  DatabaseHelper.COLUMN_LIBRARY_SUBJECT_ID, 
			DatabaseHelper.COLUMN_LIBRARY_QUESTION, DatabaseHelper.COLUMN_LIBRARY_ANSWER, DatabaseHelper.COLUMN_LIBRARY_CATEGORY_ID 
	};
	private static final int COLUMN_ID_INDEX = 0;
	private static final int COLUMN_LIBRARY_SUBJECT_ID_INDEX = 1;
	private static final int COLUMN_LIBRARY_QUESTION_INDEX = 2;
	private static final int COLUMN_LIBRARY_ANSWER_INDEX = 3;
	private static final int COLUMN_LIBRARY_CATEGORY_ID_INDEX = 4;
	
	public LibraryDataSource(Context context){
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		//database = dbHelper.getWritableDatabase();
		database = dbHelper.getDb();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Library createLibrary(long subject_id, String question, String answer, long category_id) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_LIBRARY_SUBJECT_ID, subject_id);
		values.put(DatabaseHelper.COLUMN_LIBRARY_QUESTION, question);
		values.put(DatabaseHelper.COLUMN_LIBRARY_ANSWER, answer);
		values.put(DatabaseHelper.COLUMN_LIBRARY_CATEGORY_ID, category_id);
		long insertId = database.insert(DatabaseHelper.TABLE_LIBRARY, null, values);
		Cursor cursor = database.query(DatabaseHelper.TABLE_LIBRARY, 
				allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Library newLibrary = cursorToLibrary(cursor);
		cursor.close();
		return newLibrary;
	}

	public void deleteLibrary(Library library){
		long id = library.getId();
		System.out.println("Library deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_LIBRARY, DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	
	public List<Library> getAllLibraries(){
		List<Library> libraries = new ArrayList<Library>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_LIBRARY, allColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Library library = cursorToLibrary(cursor);
			libraries.add(library);
			cursor.moveToNext();
		}
		
		//make sure to close the cursor
		cursor.close();
		return libraries;
	}
	
	public Library getLibrary(long id){
		Library library = null;
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_LIBRARY, allColumns, DatabaseHelper.COLUMN_ID + " = ?", new String[] { Long.toString(id) }, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			library = cursorToLibrary(cursor);
			cursor.moveToNext();
		}
		
		//make sure to close the cursor
		cursor.close();
		return library;
	}
	
	private Library cursorToLibrary(Cursor cursor) {
		Library library = new Library();
		library.setId(cursor.getLong(COLUMN_ID_INDEX));
		library.setSubjectId(cursor.getLong(COLUMN_LIBRARY_SUBJECT_ID_INDEX));
		library.setQuestion(cursor.getString(COLUMN_LIBRARY_QUESTION_INDEX));
		library.setAnswer(cursor.getString(COLUMN_LIBRARY_ANSWER_INDEX));
		library.setCategoryId(cursor.getLong(COLUMN_LIBRARY_CATEGORY_ID_INDEX));
		return library;
	}
}

