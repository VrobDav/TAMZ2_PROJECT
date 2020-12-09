package com.example.pong_game;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
    }

    public void openMenu(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();

    }

    public void openSettings(View view){


    }

    public void openHighScore(View view){


    }
}