package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by vio on 12/5/16.
 */

public class Ghost extends A_Player
{
    public Ghost(int startCellX, int startCellY, int cellWidth)
    {
        super(startCellX, startCellY, cellWidth);
        setCurrentDirection(pickRandomDirection());
    }

    @Override
    public void drawPlayer(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(getCurrentCellX()*cellWidth + 5, getCurrentCellY()*cellWidth + 5, getPlayerSize(), paint);
    }
}
