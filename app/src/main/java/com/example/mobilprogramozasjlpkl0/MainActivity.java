package com.example.mobilprogramozasjlpkl0;

import static android.widget.AdapterView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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
    EditText updateToDoText;


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

        updateToDoText = findViewById(R.id.input_update);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //the text to updade
                String todoText = adapter.getItem(i).getTodo();
                int todoId = adapter.getItem(i).id;

                Toast.makeText(MainActivity.this, String.valueOf(todoId), Toast.LENGTH_SHORT).show();

                //sending the text to the fragment
                Bundle bundle = new Bundle();
                bundle.putString("textToUpdate", todoText);

                Update updateFregment = new Update();
                // set Fragmentclass Arguments
                updateFregment.setArguments(bundle);
                FragmentManager fr = getSupportFragmentManager();
                FragmentTransaction ft = fr.beginTransaction();
                ft.replace(R.id.fragmentContainerView, updateFregment);
                ft.commit();

                //showHideFragment(updateFregment);
            }
        });


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
/*
        info inf = new info();
        FragmentManager fr = getSupportFragmentManager();
        FragmentTransaction ft = fr.beginTransaction();
        ft.replace(R.id.fragmentContainerView, inf);
        ft.commit();
*/
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
        ft.replace(R.id.fragmentContainerView, fragment);
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