package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by vio on 12/5/16.
 */

public class Player extends A_Player
{
    public Player(int startCellX, int startCellY, int cellWidth)
    {
        super(startCellX, startCellY, cellWidth);
    }

    @Override
    public void drawPlayer(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

        canvas.drawCircle(getPositionX(), getPositionY(), getPlayerSize(), paint);
    }
}
