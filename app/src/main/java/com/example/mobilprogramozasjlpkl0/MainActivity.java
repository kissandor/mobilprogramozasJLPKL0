package com.example.mobilprogramozasjlpkl0;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.ToDoAdapter;
import Interfaces.IToDo;
import data.ToDo;

public class MainActivity extends AppCompatActivity {

    //list to store the todos
    // the idea is to store the todos in database
    ArrayList<ToDo> todos;
    ImageButton add;
    EditText data;
    SQLiteDatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new SQLiteDatabaseHandler(this);
        todos = new ArrayList<>();
        //get todos from the database
        todos = dbHandler.getAllTodos();


        ToDoAdapter adaptr = new ToDoAdapter(getApplicationContext(), R.layout.list_item, todos);
        ListView listView = findViewById(R.id.messages);
        listView.setAdapter(adaptr);

        add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = findViewById(R.id.quick_input);
                if (data.getText().length() > 0){
                    addToDo(data, false);
                    todos = dbHandler.getAllTodos();
                    adaptr.notifyDataSetChanged();
                }

            }
        });
    }

    //method to add todos to the list
    public void addToDo(EditText et, Boolean completed){
        ToDo newTodo = new ToDo(completed, et.getText().toString());
        dbHandler.addTodo(newTodo);
        et.setText("");
    }
}