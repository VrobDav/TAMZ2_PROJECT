package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {

    TextView scoreLimitTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        scoreLimitTextView = findViewById(R.id.scoreLimitTextView);
        scoreLimitTextView.setText('1');
    }

    public void startGame2Players(View view){
        Intent intent = new Intent(this, StartGame.class);
        startActivity(intent);
        finish();

    }

    public void startGameSinglePlayer(View view){


    }

    public void riseScoreLimit(View view){
        int currentLimit = Integer.parseInt(scoreLimitTextView.getText().toString());
        currentLimit++;
        if(currentLimit > 100){
            currentLimit = 100;
        }
        scoreLimitTextView.setText(String.valueOf(currentLimit));
        Paddle.setScoreLimit(currentLimit);
    }

    public void lowerScoreLimit(View view){
        int currentLimit = Integer.parseInt(scoreLimitTextView.getText().toString());
        currentLimit--;
        if(currentLimit < 1){
            currentLimit = 1;
        }
        scoreLimitTextView.setText(String.valueOf(currentLimit));
        Paddle.setScoreLimit(currentLimit);

    }
}    