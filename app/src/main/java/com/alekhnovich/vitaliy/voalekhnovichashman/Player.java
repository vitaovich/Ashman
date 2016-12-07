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
        color = Color.GREEN;
    }

//    @Override
//    public void drawPlayer(Canvas canvas)
//    {
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.GREEN);
//        float cx = getCurrentCellX()*cellWidth, cy = getCurrentCellY()*cellWidth;
//        if(getCurrentDirection() == Directions.Right && !atWall)
//        {
//            cx = cx + (float)2.5*(getSubStep()+1);
//        }
//        else if(getCurrentDirection() == Directions.Left && !atWall)
//        {
//            if(getSubStep() == 3)
//            {
//                cx = cx - 10;
//            }
//            cx = cx + (float)2.5*(getSubStep()+1);
//        }
//        else
//        {
//            cx = cx + 5;
//        }
//        if(getCurrentDirection() == Directions.Down && !atWall)
//        {
//            cy = cy + (float)2.5*(getSubStep()+1);
//        }
//        else if(getCurrentDirection() == Directions.Up && !atWall)
//        {
//            if(getSubStep() == 3)
//            {
//                cy = cy - 10;
//            }
//            cy = cy + (float)2.5*(getSubStep()+1);
//        }
//        else
//        {
//            cy = cy + 5;
//        }
//        canvas.drawCircle(cx, cy, getPlayerSize(), paint);
//    }
}
