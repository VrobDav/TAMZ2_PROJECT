package com.example.pong_game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.os.Handler;

public class GameView extends View {

    static int scoreLimit = 1;
    Bitmap background;
    Rect rect;
    static int displayHeight, displayWidth;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 10;
    Ball ball;
    Paddle paddle1;
    Paddle paddle2;
    static boolean bot = false;
    static boolean sound = true;
    SharedPreferences preferences;
    int wallHitSound;
    static int paddleHitSound;
    static SoundPool soundPool;
    int gameStatus = 0;  // 0=stop   1=play  3=game over

    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background1);

        preferences = getContext().getSharedPreferences("myPreferences",Context.MODE_PRIVATE);
        String backGround = preferences.getString("backGround","");
        if(backGround.equals("background1")){
            background = BitmapFactory.decodeResource(getResources(), R.drawable.background1);
        }
        if(backGround.equals("background2")){
            background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
        }
        String Sound = preferences.getString("sound","");
        if(Sound.equals("true")){
            sound = true;
        }
        if(Sound.equals("false")){
            sound = false;
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        wallHitSound = soundPool.load(context, R.raw.wall_hit, 1);
        paddleHitSound = soundPool.load(context, R.raw.paddle_hit, 1);


        WindowManager wm = (WindowManager)    context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;

        //Log.d("width", String.valueOf(displayWidth));
        //Log.d("height", String.valueOf(displayHeight));

        /*WindowMetrics windowMetrics = ((Activity) context).getWindowManager().getCurrentWindowMetrics();
        Insets insets = windowMetrics.getWindowInsets()
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        displayWidth = windowMetrics.getBounds().width() - insets.left - insets.right;
        displayHeight = windowMetrics.getBounds().height() - insets.top - insets.bottom;
        */
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

    public static void setScoreLimit(int limit){
        scoreLimit = limit;
    }
    @Override

    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
            if (gameStatus == 1) {
                int num = event.getPointerCount();
                for (int a = 0; a < num; a++) {
                    int x = (int) event.getX(event.getPointerId(a));
                    int y = (int) event.getY(event.getPointerId(a));
                    if (!bot)
                        if (y < displayHeight / 2)
                            paddle1.movePaddle(x);
                    if (y > displayHeight / 2)
                        paddle2.movePaddle(x);
                }
            }
            if (gameStatus == 0) gameStatus = 1;

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

        //Log.d("ballY", String.valueOf(ball.ballY));
        //Log.d("ballX", String.valueOf(ball.ballX));
        if(gameStatus == 0){


        }

        if(gameStatus == 1) {
            ball.ballFrame++;
            if (ball.ballFrame > 15) {
                ball.ballFrame = 0;
            }

            ball.ballX += ball.ballVelocityX;
            ball.ballY += ball.ballVelocityY;
            if (ball.ballX < 5) {
                ball.ballVelocityX = -ball.ballVelocityX;
                if (sound)
                    soundPool.play(wallHitSound, 1, 1, 0, 0, 1);
            }
            if ((ball.ballX + ball.getWidth()) > displayWidth - 5) {
                ball.ballVelocityX = -ball.ballVelocityX;
                if (sound)
                    soundPool.play(wallHitSound, 1, 1, 0, 0, 1);

            }
            if (bot) {
                if(ball.ballY < displayHeight/2){
                    paddle1.movePaddle(ball.ballX);
                }
            }
            ball.paddleCollision(paddle1, paddle2);
            if (!bot)
                checkWinner(paddle1, paddle2);

            if (bot)
                checkBotWin(paddle1, paddle2);
        }

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    public void checkWinner(Paddle pad1, Paddle pad2){
         if(pad1.score == scoreLimit){
             gameStatus = 3;
             Intent intent = new Intent(getContext(), WinnerActivity.class);
             intent.putExtra("player", "Player 1");
             getContext().startActivity(intent);

         }
         if(pad2.score == scoreLimit){
             gameStatus = 3;
             Intent intent = new Intent(getContext(), WinnerActivity.class);
             intent.putExtra("player", "Player 2");
             getContext().startActivity(intent);

         }
    }

    public void checkBotWin(Paddle pad1, Paddle pad2){
        if(pad1.score >= 1){
            gameStatus = 3;
            Intent intent = new Intent(getContext(), botEndActivity.class);
            intent.putExtra("score", String.valueOf(pad2.score));
            getContext().startActivity(intent);

        }
    }


}
