package com.example.pong_game;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StartGame extends Activity {
    GameView gameView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = 1;
        gameView = new GameView(this);
        setContentView(gameView);

    }

}
