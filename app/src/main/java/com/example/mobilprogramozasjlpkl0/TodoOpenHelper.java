package com.example.mobilprogramozasjlpkl0;

import static com.example.mobilprogramozasjlpkl0.ToDoContract.SQL_CREATE_TABLE;
import static com.example.mobilprogramozasjlpkl0.ToDoContract.SQL_DELETE_TABLE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TodoOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "tododata.db";
    public static final int DB_VERSION = 1;

    public TodoOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
