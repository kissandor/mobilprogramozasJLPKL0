package com.example.mobilprogramozasjlpkl0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    info infoFregment;
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

        //to make the fragmment hidden

        FragmentManager fragmentManager = getSupportFragmentManager();
        infoFregment = (info) fragmentManager.findFragmentById(R.id.fragmentContainerView);
        showHideFragment(infoFregment);



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

    //to inflate the actonBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        infoFregment = (info) fragmentManager.findFragmentById(R.id.fragmentContainerView);
        showHideFragment(infoFregment);
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

    public void showHideFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);

        if (fragment.isHidden()) {
            ft.show(fragment);
        } else {
            ft.hide(fragment);
        }

        ft.commit();
    }
}