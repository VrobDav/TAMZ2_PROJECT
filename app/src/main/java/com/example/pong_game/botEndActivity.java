package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class botEndActivity extends Activity {
    private DBHelper mydb;
    TextView score;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_end);
        score = findViewById(R.id.scoreTextView);
        name = findViewById(R.id.playerNameTextView);
    }

    public void goToMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void postScore(View view){
        if(mydb.insertItem(name.getText().toString(), Integer.parseInt(score.getText().toString()))) {
            Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "not saved", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}