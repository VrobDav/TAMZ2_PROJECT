package com.example.pong_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ball {
    Bitmap ball[] = new Bitmap[16];
    int ballX, ballY, ballVelocity, ballFrame, ballWidth;

    public Ball(Context context){
        ball[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball0);
        ball[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball1);
        ball[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball2);
        ball[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball3);
        ball[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball4);
        ball[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball5);
        ball[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball6);
        ball[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball7);
        ball[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball8);
        ball[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball9);
        ball[10] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball10);
        ball[11] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball11);
        ball[12] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball12);
        ball[13] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball13);
        ball[14] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball14);
        ball[15] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball15);

        ballWidth = ball[0].getWidth();

    }
}
