package com.example.pong_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScoreActivity extends Activity {

    DBHelper mydb;
    ListView itemListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        mydb = new DBHelper(this);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList = mydb.getItemList();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setBackgroundColor(Color.TRANSPARENT);
                textView.setTextColor(Color.parseColor("#47C1A5"));
                return textView;
            }
        };

        itemListView = findViewById(R.id.scoreListView);
        itemListView.setAdapter(arrayAdapter);
    }

    public void goBack(View view){
        finish();
    }
}