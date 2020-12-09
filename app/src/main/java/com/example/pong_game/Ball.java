package com.example.pong_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;


public class Ball {
    Bitmap ball[] = new Bitmap[16];
    int ballX, ballY, ballVelocityX, ballVelocityY, ballFrame, ballWidth;
    private Random random;
    int count;

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
        ballVelocityX = 3;
        ballVelocityY = 3;
        ballX = GameView.displayWidth/2 + ballWidth/2;
        ballY = GameView.displayHeight/2 + ballWidth/2;
        ballFrame = 0;

        random = new Random();
        count = 0;
    }

    public Bitmap getBitmap(){
        return ball[ballFrame];
    }

    public int getWidth(){
        return ball[0].getWidth();
    }

    public int getHeight(){
        return ball[0].getHeight();
    }

    public void spawn(){
        ballX = GameView.displayWidth/2 + ballWidth/2;
        ballY = GameView.displayHeight/2 + ballWidth/2;

        ballVelocityY = -1 + random.nextInt(2);
        if(ballVelocityY == 0)
            ballVelocityY = 1;
        ballVelocityX = -2 + random.nextInt(4);
        if(ballVelocityX == 0)
            ballVelocityX = 1;
        count = 0;

    }
}
