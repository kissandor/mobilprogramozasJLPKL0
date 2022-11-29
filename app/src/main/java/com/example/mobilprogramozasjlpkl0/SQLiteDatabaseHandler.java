package com.example.mobilprogramozasjlpkl0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import data.ToDo;

public class SQLiteDatabaseHandler {
    private Context _context;

    //konstruktor
    public SQLiteDatabaseHandler(Context context){
        _context = context;
    }

    public void addTodo(ToDo todo) {

        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ToDoContract.TodoEntry.COLUMN_NAME_TODO, todo.getTodo());
        values.put(ToDoContract.TodoEntry.COLUMN_NAME_CHECKED, String.valueOf(todo.getCompleted()));

        long id = db.insert(
                ToDoContract.TodoEntry.TABLE_NAME, null, values);

        //should close the connection ???
        //TodoOpenHelper.close();
    }
    public ArrayList<ToDo> getAllTodos(){

        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();
/*
        String[] columns = {
                ToDoContract.TodoEntry._ID,
                ToDoContract.TodoEntry.COLUMN_NAME_TODO,
                ToDoContract.TodoEntry.COLUMN_NAME_CHECKED
        };
*/
        Cursor cursor = db.query(
                false,
                ToDoContract.TodoEntry.TABLE_NAME,
                null,
                ToDoContract.TodoEntry.COLUMN_NAME_CHECKED+"='false'",
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<ToDo> todos = new ArrayList<>();

        while(cursor.moveToNext())
        {
            int index;
            index = cursor.getColumnIndexOrThrow("_id");
            long _id = cursor.getLong(index);

            index = cursor.getColumnIndexOrThrow(
                    ToDoContract.TodoEntry.COLUMN_NAME_TODO);
            String todo = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(
                    ToDoContract.TodoEntry.COLUMN_NAME_CHECKED);
            boolean checked = Boolean.getBoolean(cursor.getString(index));

            ToDo td = new ToDo(checked, todo);
            td.id = Math.toIntExact(_id);
            todos.add(td);
        }
        cursor.close();
        return todos;
    }
    public void updateTodo(ToDo todo){
        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String newValue = "csere";
        String newChecked = "true";
        ContentValues values = new ContentValues();
        values.put(ToDoContract.TodoEntry._ID, todo.id);
        values.put(ToDoContract.TodoEntry.COLUMN_NAME_TODO, newValue);
        values.put(ToDoContract.TodoEntry.COLUMN_NAME_CHECKED, newChecked);


        int update = db.update(
                ToDoContract.TodoEntry.TABLE_NAME,
                values,
                ToDoContract.TodoEntry._ID+"="+todo.id,
                null);
        //+"='false'"
    }
}
