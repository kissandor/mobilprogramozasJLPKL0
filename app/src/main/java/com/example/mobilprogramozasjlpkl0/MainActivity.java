package com.example.mobilprogramozasjlpkl0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IToDo;
import data.ToDo;

public class MainActivity extends AppCompatActivity {

    //list to store the todos
    // the idea is to store the todos in database
    List<IToDo> todos;
    ImageButton add;
    EditText data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = findViewById(R.id.quick_input);
                addToDo(data, false);
            }
        });
    }
    
    //method to add todos to the list
    public void addToDo(EditText et, Boolean completed){
        IToDo newTodo = new ToDo(completed, et.getText().toString());
        todos = new ArrayList<IToDo>();
        todos.add(newTodo);
        et.setText("");
    }
}