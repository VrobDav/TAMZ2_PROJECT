package com.example.pong_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Paddle {
    static int scoreLimit;
    Bitmap paddle;
    int paddleVelocity;
    int paddleX, paddleY, paddleWidth, paddleHeight;
    int paddleNumber;
    int score;

    public Paddle(Context context, int paddleNum){
        paddle = BitmapFactory.decodeResource(context.getResources(), R.drawable.paddle);
        paddleNumber = paddleNum;
        scoreLimit = 1;
        score = 0;
        paddleVelocity = 3;
        paddleWidth = paddle.getWidth();
        paddleHeight = paddle.getHeight();
        paddleX = GameView.displayWidth/2 - paddleWidth/2;
        if (paddleNumber == 1){
            paddleY = GameView.displayHeight/5;
        }
        else if (paddleNumber == 2){
            paddleY =  (GameView.displayHeight/5)*4 - paddle.getHeight();
        }

    }
    public int getWidth(){
        return paddle.getWidth();
    }

    public int getHeight(){
        return paddle.getHeight();
    }

    public static void setScoreLimit(int limit){
        scoreLimit = limit;
    }

    public void movePaddle(int x, int y){
        if (x > (paddleX + paddleWidth/2))
            paddleX += paddleVelocity;
        if (x < (paddleX + paddleWidth/2))
            paddleX -= paddleVelocity;
        if(paddleX <= 0)
            paddleX = 0;
        if(paddleX + paddleWidth >= GameView.displayWidth)
            paddleX = GameView.displayWidth - paddleWidth;
    }
}
