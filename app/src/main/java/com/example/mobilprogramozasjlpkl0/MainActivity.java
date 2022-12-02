package com.example.mobilprogramozasjlpkl0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.ToDoAdapter;
import data.ToDo;

public class MainActivity extends AppCompatActivity {

    //list to store the todos
    // the idea is to store the todos in database
    ArrayList<ToDo> todos;
    ImageButton add;
    EditText data;
    SQLiteDatabaseHandler dbHandler;
    ToDoAdapter adapter;
    DeleteTodo fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new SQLiteDatabaseHandler(getApplicationContext());
        todos = new ArrayList<>();
        //get todos from the database
        todos = dbHandler.getAllTodos();

        adapter = new ToDoAdapter(MainActivity.this, R.layout.list_item, todos);
        ListView listView = findViewById(R.id.messages);
        listView.setAdapter(adapter);



        add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = findViewById(R.id.quick_input);
                if (data.getText().length() > 0){
                    addToDo(data, false);
                    todos.clear();
                    todos = dbHandler.getAllTodos();
                    updateScreen();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    public  void updateScreen(){
        adapter.updateAdapter(todos);
    }

    //method to add todos to the list
    public void addToDo(EditText et, Boolean completed){
        ToDo newTodo = new ToDo(completed, et.getText().toString());
        dbHandler.addTodo(newTodo);
        et.setText("");
    }
}