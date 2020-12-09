package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame2Players(View view){
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();

    }

    public void startGameSinglePlayer(View view){


    }

    public void riseScoreLimit(View view){


    }

    public void lowerScoreLimit(View view){


    }
}    