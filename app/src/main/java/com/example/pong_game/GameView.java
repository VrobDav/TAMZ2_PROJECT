package com.example.pong_game;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.os.Handler;


public class GameView extends View {

    Bitmap background;
    Rect rect;
    static int displayHeight, displayWidth;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;
    Ball ball;
    Paddle paddle1;
    Paddle paddle2;
    static boolean bot = false;
    boolean sound = true;
    SharedPreferences preferences;

    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background1);

        preferences = getContext().getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
        String backGround = preferences.getString("backGround","");
        if(backGround.equals("background1")){
            background = BitmapFactory.decodeResource(getResources(), R.drawable.background1);
        }
        if(background.equals("background2")){
            background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
        }
        String Sound = preferences.getString("sound","");
        if(Sound.equals("true")){
            sound = true;
        }
        if(Sound.equals("false")){
            sound = false;
        }


        WindowMetrics windowMetrics = ((Activity) context).getWindowManager().getCurrentWindowMetrics();
        Insets insets = windowMetrics.getWindowInsets()
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        displayWidth = windowMetrics.getBounds().width() - insets.left - insets.right;
        displayHeight = windowMetrics.getBounds().height() - insets.top - insets.bottom;

        rect = new Rect(0,0, displayWidth, displayHeight);

        paddle1 = new Paddle(context, 1);
        paddle2 = new Paddle(context, 2);
        ball = new Ball(context);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {

                invalidate();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int num = event.getPointerCount();
        for (int a = 0; a < num; a++) {
            int x = (int) event.getX(event.getPointerId(a));
            int y = (int) event.getY(event.getPointerId(a));
            if(y < displayHeight/2)  paddle1.movePaddle(x, y);
            if(y > displayHeight/2)  paddle2.movePaddle(x, y);
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rect, null);

        canvas.drawBitmap(paddle1.paddle, paddle1.paddleX, paddle1.paddleY, null);
        canvas.drawBitmap(paddle2.paddle, paddle2.paddleX, paddle2.paddleY, null);
        canvas.drawBitmap(ball.getBitmap(), ball.ballX, ball.ballY, null);
        ball.ballFrame++;
        if(ball.ballFrame > 15){
            ball.ballFrame = 0;
        }


        ball.ballX += ball.ballVelocityX;
        ball.ballY += ball.ballVelocityY;
        if (ball.ballX < 5) {
            ball.ballVelocityX = -ball.ballVelocityX;
        }
        if ((ball.ballX + ball.getWidth()) > displayWidth - 5) {
            ball.ballVelocityX = -ball.ballVelocityX;
        }

        ball.paddleCollision(paddle1, paddle2);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }


}
