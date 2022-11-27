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

        values.put(ToDoContract.TodoEntry.COLUMN_NAME_TODO, todo.todo);
        values.put(ToDoContract.TodoEntry.COLUMN_NAME_CHECKED, String.valueOf(todo.completed));

        long id = db.insert(
                ToDoContract.TodoEntry.TABLE_NAME, null, values);

        //should close the connection ???
        //TodoOpenHelper.close();
    }

    public ArrayList<ToDo> getAllTodos(){

        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {
                ToDoContract.TodoEntry._ID,
                ToDoContract.TodoEntry.COLUMN_NAME_TODO,
                ToDoContract.TodoEntry.COLUMN_NAME_CHECKED
        };

        Cursor cursor = db.query(
                ToDoContract.TodoEntry.TABLE_NAME,   // The table to query
                columns,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        ArrayList<ToDo> todos = new ArrayList<ToDo>();
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("_id");
            long _id = cursor.getLong(index);

            index = cursor.getColumnIndexOrThrow(
                    ToDoContract.TodoEntry.TABLE_NAME);
            String todo = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(
                    ToDoContract.TodoEntry.COLUMN_NAME_CHECKED);
            String checked = cursor.getString(index);

            ToDo td;
            if(checked == "false"){
                td = new ToDo(false, todo);
            } else {
                td = new ToDo(true, todo);
            }
            todos.add(td);
        }
        cursor.close();
        return todos;
    }
}
