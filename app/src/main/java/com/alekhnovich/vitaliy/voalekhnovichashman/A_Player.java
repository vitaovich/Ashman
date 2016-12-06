package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;

/**
 * Created by vio on 12/5/16.
 */

public abstract class A_Player
{
    Directions currentDirection;
    private int cellWidth;
    private int currentCellX;
    private int currentCellY;
    private float positionX;
    private float positionY;
    private float playerSize;

    public A_Player(int startCellX, int startCellY, int cellWidth)
    {
        this.currentCellX = startCellX;
        this.currentCellY = startCellY;
        this.cellWidth = cellWidth;
        //center position on cell
        this.positionX = getCurrentCellX()*cellWidth + cellWidth/2;
        this.positionY = getCurrentCellY()*cellWidth + cellWidth/2;
        playerSize = cellWidth/3;
    }
    
    public void setPositionX(float positionX)
    {
        this.positionX = positionX;
    }
    public float getPositionX()
    {
        return this.positionX;
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
    public float getCurrentCellY()
    {
        return this.currentCellY;
    }

    public void setPositionY(float positionY)
    {
        this.positionY = positionY;
    }
    public float getPositionY()
    {
        return this.positionY;
    }

    public void setPlayerSize(float playerSize)
    {
        this.playerSize = playerSize;
    }
    public float getPlayerSize()
    {
        return this.playerSize;
    }


    public void setCurrentDirection(Directions direction)
    {
        this.currentDirection = direction;
    }

    public abstract void drawPlayer(Canvas canvas);
}