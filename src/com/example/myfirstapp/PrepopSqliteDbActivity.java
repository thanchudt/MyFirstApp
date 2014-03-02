package com.example.myfirstapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class PrepopSqliteDbActivity extends Activity{   
    private SQLiteDatabase database;
    private UserDataSource userDataSource;
	private CategoryDataSource categoryDataSource;
	private SubjectDataSource subjectDataSource;
	private LibraryDataSource libraryDataSource;
	private TestDataSource testDataSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepop_sqlite_db);

        //Our key helper
        DatabaseHelper dbOpenHelper = new DatabaseHelper(this);
        database = dbOpenHelper.openDataBase();        
    }    
}
