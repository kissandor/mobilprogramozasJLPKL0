package com.example.mobilprogramozasjlpkl0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DeleteTodo extends AppCompatActivity {

    ImageView deleteYes;
    ImageView deleteNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_todo);

        Intent intent = getIntent();
        int todoChecked = intent.getIntExtra("TodoChecked", -1);

        deleteYes = findViewById(R.id.deleteYes);
        deleteYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(getApplicationContext());
                db.deleteTodo(todoChecked);
                Intent it = new Intent(DeleteTodo.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        deleteNo = findViewById(R.id.deleteNo);
        deleteNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DeleteTodo.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}