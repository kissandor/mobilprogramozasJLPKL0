package com.example.mobilprogramozasjlpkl0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        startMainActivity();
    }

    // method to load the main activity after 3 sec.
    public void startMainActivity(){
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(StartPage.this, MainActivity.class);
            StartPage.this.startActivity(intent);
            StartPage.this.finish();;
        }, 3000);
    }
}