package com.example.pong_game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import java.util.Random;


public class Ball {
    Bitmap ball[] = new Bitmap[16];
    int ballX, ballY, ballVelocityX, ballVelocityY, ballFrame, ballWidth;
    private Random random;
    int paddleHitCount;


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
        ballFrame = 0;

        random = new Random();
        spawn();
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
        ballX = GameView.displayWidth/2 - ballWidth/2;
        ballY = GameView.displayHeight/2 - ballWidth/2;

        int temp = random.nextInt(2);
        if(temp == 0)
            ballVelocityY = -10;
        if(temp == 1)
            ballVelocityY = 10;
        ballVelocityX = -10 + random.nextInt(20);
        if(ballVelocityX == 0)
            ballVelocityX = 1;
        paddleHitCount = 0;

    }
    //(ballY <= (pad1.paddleY + pad1.getHeight()) && (ballY + getHeight()/2) >= pad1.paddleY) || ballY <= 0
    public void paddleCollision(Paddle pad1, Paddle pad2){
        if (ballY <= pad1.getHeight()) {
            if((ballX + getWidth()/2) >= pad1.paddleX && (ballX + getWidth()/2) <= (pad1.paddleX + pad1.getWidth())){
                ballY = pad1.paddleY + pad1.getHeight();
                paddleHitCount++;
                ballVelocityY = -ballVelocityY +(paddleHitCount / 4);
                ballVelocityX = -20 + random.nextInt(40) +(paddleHitCount / 4);
                if (ballVelocityX == 0)
                    ballVelocityX = 1;
                if(GameView.sound)
                    GameView.soundPool.play(GameView.paddleHitSound, 1,1,0,0,1);
            }
        }
        if(ballY <= 0) {
            pad2.score++;
            spawn();
        }

//(ballY + getHeight() )>= pad2.paddleY && (ballY + getHeight()/2) <= (pad2.paddleY + pad2.getHeight())
        if ((ballY + getHeight()) >= pad2.paddleY) {
            if((ballX + getWidth()/2) >= pad2.paddleX && (ballX + getWidth()/2) <= (pad2.paddleX + pad2.getWidth())){
                ballY = pad2.paddleY - getHeight();
                paddleHitCount++;
                ballVelocityY = -ballVelocityY - (paddleHitCount / 4);
                ballVelocityX = -20 + random.nextInt(40) +(paddleHitCount / 4);
                if (ballVelocityX == 0)
                    ballVelocityX = 1;
                if(GameView.sound)
                    GameView.soundPool.play(GameView.paddleHitSound, 1,1,0,0,1);
            }
        }
        if(ballY >= GameView.displayHeight) {
            pad1.score++;
            spawn();
        }

    }
}
