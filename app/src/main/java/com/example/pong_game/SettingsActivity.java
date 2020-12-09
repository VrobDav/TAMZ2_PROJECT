package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.SharedPreferences;

public class SettingsActivity extends Activity {

    ImageButton SoundOn;
    ImageButton SoundOff;
    ImageButton gameBackground_1_Button;
    ImageButton gameBackground_2_Button;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




    }

    public void goBack(View view){
        finish();
    }

    public void setSoundOn(View view){
        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("sound", "true");
        prefEd.commit();
    }

    public void setSoundOff(View view){
        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("sound", "false");
        prefEd.commit();
    }

    public void setGameBackgroundImg1(View view){
        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("backGround", "background1");
        prefEd.commit();
    }

    public void setGameBackgroundImg2(View view){
        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("backGround", "background2");
        prefEd.commit();
    }
}