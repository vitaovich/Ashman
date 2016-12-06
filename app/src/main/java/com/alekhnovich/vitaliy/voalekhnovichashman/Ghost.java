package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by vio on 12/5/16.
 */

public class Ghost extends A_Player
{
    public Ghost(int startCellX, int startCellY, int cellWidth)
    {
        super(startCellX, startCellY, cellWidth);
    }

    public void drawGhost(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(getPositionX(), getPositionY(), 3, paint);
    }

    @Override
    public void drawPlayer(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(getPositionX(), getPositionY(), getPlayerSize(), paint);
    }
}
