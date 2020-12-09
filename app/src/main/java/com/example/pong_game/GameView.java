package com.example.pong_game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowMetrics;

public class GameView extends View {

    Bitmap background;
    Rect rect;
    int displayHeight, displayWidth;

    public GameView(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background1);


        WindowMetrics windowMetrics = ((Activity) getContext()).getWindowManager().getCurrentWindowMetrics();
        displayWidth = windowMetrics.getBounds().width();
        displayHeight = windowMetrics.getBounds().height();

        rect = new Rect(0,0, displayWidth, displayHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, null, rect, null);
    }
}
