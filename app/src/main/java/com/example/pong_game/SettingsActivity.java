package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.LightingColorFilter;
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

        gameBackground_1_Button = findViewById(R.id.gameBackground_1_Button);
        gameBackground_2_Button = findViewById(R.id.gameBackground_2_Button);
        SoundOn = findViewById(R.id.soundOnButton);
        SoundOff = findViewById(R.id.soundOffButton);

        preferences = getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
        String backGround = preferences.getString("backGround","background1");
        if(backGround.equals("background1")){
            gameBackground_1_Button.setColorFilter(null);
            gameBackground_2_Button.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));
        }
        if(backGround.equals("background2")){
            gameBackground_2_Button.setColorFilter(null);
            gameBackground_1_Button.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));
        }
        String Sound = preferences.getString("sound","true");
        if(Sound.equals("true")){
            SoundOn.setColorFilter(null);
            SoundOff.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));
        }
        if(Sound.equals("false")){
            SoundOff.setColorFilter(null);
            SoundOn.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));
        }

    }

    public void goBack(View view){
        finish();
    }

    public void setSoundOn(View view){
        SoundOn.setColorFilter(null);
        SoundOff.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));

        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("sound", "true");
        prefEd.commit();
    }

    public void setSoundOff(View view){
        SoundOff.setColorFilter(null);
        SoundOn.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));

        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("sound", "false");
        prefEd.commit();
    }

    public void setGameBackgroundImg1(View view){
        gameBackground_1_Button.setColorFilter(null);
        gameBackground_2_Button.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));

        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("backGround", "background1");
        prefEd.commit();
    }

    public void setGameBackgroundImg2(View view){
        gameBackground_2_Button.setColorFilter(null);
        gameBackground_1_Button.setColorFilter(new LightingColorFilter(0xff888888, 0x000000));

        preferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEd = preferences.edit();
        prefEd.putString("backGround", "background2");
        prefEd.commit();
    }
}