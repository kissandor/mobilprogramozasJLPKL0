package com.example.mobilprogramozasjlpkl0;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public void updateTodo(int todoId, String newTodoText ){
        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDoContract.TodoEntry.COLUMN_NAME_TODO, newTodoText);


        db.update(
                ToDoContract.TodoEntry.TABLE_NAME,
                values,
                ToDoContract.TodoEntry._ID+"="+todoId,
                null);
        db.close();
    }

    public void deleteTodo(int todoId) {
        TodoOpenHelper helper = new TodoOpenHelper(_context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<ToDo> todos = new ArrayList<>();
        todos = getAllTodos();
        db.delete(ToDoContract.TodoEntry.TABLE_NAME, ToDoContract.TodoEntry._ID+ "="+ todoId,null);
        todos = getAllTodos();
    }
}
