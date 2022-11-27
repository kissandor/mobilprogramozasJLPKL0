package com.example.mobilprogramozasjlpkl0;

import android.provider.BaseColumns;

public class ToDoContract {
    //private konstruktor//
    private ToDoContract(){}

    public static class TodoEntry implements BaseColumns{
        public static final String TABLE_NAME = "tododata";
        public static final String COLUMN_NAME_TODO = "todo";
        public static final String COLUMN_NAME_CHECKED = "active";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                    TodoEntry._ID + " INTEGER PRIMARY KEY," +
                    TodoEntry.COLUMN_NAME_TODO + " TEXT," +
                    TodoEntry.COLUMN_NAME_CHECKED + " TEXT)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;
}
