package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by vio on 12/5/16.
 */

public abstract class A_Player
{
    Directions currentDirection;
    protected int cellWidth;
    private int currentCellX;
    private int currentCellY;
    private float playerSize;
    private boolean moving;
    private int subStep;
    protected boolean atWall;
    protected int color;

    public A_Player(int startCellX, int startCellY, int cellWidth)
    {
        this.currentCellX = startCellX;
        this.currentCellY = startCellY;
        this.cellWidth = cellWidth;
        playerSize = cellWidth/3;
        moving = false;
        subStep = 1;
    }

    public void setCurrentCellX(int currentCellX)
    {
        this.currentCellX = currentCellX;
    }
    public int getCurrentCellX()
    {
        return this.currentCellX;
    }

    public void setCurrentCellY(int currentCellY)
    {
        this.currentCellY = currentCellY;
    }
    public int getCurrentCellY()
    {
        return this.currentCellY;
    }

    public void setPlayerSize(float playerSize)
    {
        this.playerSize = playerSize;
    }
    public float getPlayerSize()
    {
        return this.playerSize;
    }

    public int getSubStep()
    {
        return this.subStep;
    }

    public void setSubStep(int subStep)
    {
        if(subStep < 0)
        {
            subStep = 3;
        }
        this.subStep = subStep % 4;
    }

    public void setCurrentDirection(Directions direction)
    {
        setSubStep(1);
        this.currentDirection = direction;
    }
    public Directions getCurrentDirection() { return this.currentDirection; }

    public void drawPlayer(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        float cx = getCurrentCellX()*cellWidth, cy = getCurrentCellY()*cellWidth;
        if(getCurrentDirection() == Directions.Right && !atWall)
        {
            cx = cx + (float)2.5*(getSubStep()+1);
        }
        else if(getCurrentDirection() == Directions.Left && !atWall)
        {
            if(getSubStep() == 3)
            {
                cx = cx - 10;
            }
            cx = cx + (float)2.5*(getSubStep()+1);
        }
        else
        {
            cx = cx + 5;
        }
        if(getCurrentDirection() == Directions.Down && !atWall)
        {
            cy = cy + (float)2.5*(getSubStep()+1);
        }
        else if(getCurrentDirection() == Directions.Up && !atWall)
        {
            if(getSubStep() == 3)
            {
                cy = cy - 10;
            }
            cy = cy + (float)2.5*(getSubStep()+1);
        }
        else
        {
            cy = cy + 5;
        }
        canvas.drawCircle(cx, cy, getPlayerSize(), paint);
    }

    public static Directions pickRandomDirection()
    {
        Random random = new Random();
        int value = random.nextInt(4);
        switch(value)
        {
            case 0:
                return Directions.Up;
            case 1:
                return Directions.Down;
            case 2:
                return Directions.Left;
            case 3:
                return Directions.Right;
        }
        return Directions.Up;
    }

    public boolean isMoving()
    {
        return this.moving;
    }

    public void setMoving(boolean moving)
    {
        this.moving = moving;
    }

    public int calculateNextPosition(int[][] gameField)
    {
        int currentCellX =0, currentCellY = 0;
        int nextCell = 0;
        Directions direction;
        boolean moved = false;

        direction = getCurrentDirection();

        if(direction != null)
        {
            if(getSubStep() == 3)
            {
                currentCellX = getCurrentCellX();
                currentCellY = getCurrentCellY();
                switch(direction)
                {
                    case Up:
                        currentCellY--;
                        if(currentCellY < 0)
                        {
                            currentCellY = 13;
                        }
                        nextCell = gameField[currentCellY+1][currentCellX+1];
                        break;
                    case Down:
                        currentCellY++;
                        currentCellY = currentCellY % 14;
                        nextCell = gameField[currentCellY+1][currentCellX+1];
                        break;
                    case Right:
                        currentCellX++;
                        currentCellX = currentCellX %14;
                        nextCell = gameField[currentCellY+1][currentCellX+1];

                        break;
                    case Left:
                        currentCellX--;
                        if(currentCellX < 0)
                        {
                            currentCellX = 13;
                        }
                        nextCell = gameField[currentCellY+1][currentCellX+1];

                        break;
                }
                if(currentCellX >= 0 && currentCellX < 15 && nextCell > PlayField.WALL_VALUE)
                {
                    setCurrentCellX(currentCellX);
                    moved = true;
                }
                if(currentCellY >= 0 && currentCellY < 15 && nextCell > PlayField.WALL_VALUE)
                {
                    setCurrentCellY(currentCellY);
                    moved = true;
                }
                if(nextCell == PlayField.WALL_VALUE)
                {
                    atWall = true;
                }
                else
                {
                    atWall = false;
                }
            }
            if(getCurrentDirection() == Directions.Left || getCurrentDirection() == Directions.Up)
            {
                setSubStep(getSubStep()-1);
            }
            else
            {
                setSubStep(getSubStep()+1);
            }
        }
        setMoving(moved);
        return gameField[currentCellY+1][currentCellX+1];
    }
}