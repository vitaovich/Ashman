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
    private int successfulMoves;

    public Ghost(int startCellX, int startCellY, int cellWidth)
    {
        super(startCellX, startCellY, cellWidth);
        setCurrentDirection(pickRandomDirection());
        successfulMoves = 0;
    }

    @Override
    public void drawPlayer(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawCircle(getCurrentCellX()*cellWidth + 5, getCurrentCellY()*cellWidth + 5, getPlayerSize(), paint);
    }

    public int getSuccessfulMoves()
    {
        return this.successfulMoves;
    }

    public void setSuccessfulMoves(int successfulMoves)
    {
        this.successfulMoves = successfulMoves;
    }

    public void seeIfChangeDirectionIsNeeded()
    {
        if(getSuccessfulMoves() > 14 || !isMoving())
        {
            setCurrentDirection(pickRandomDirection());
            setSuccessfulMoves(0);
        }
        if(isMoving())
        {
            setSuccessfulMoves(getSuccessfulMoves()+1);
        }
    }
}
