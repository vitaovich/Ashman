package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.graphics.Canvas;

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
    private float positionX;
    private float positionY;
    private float playerSize;
    private boolean moving;


    public A_Player(int startCellX, int startCellY, int cellWidth)
    {
        this.currentCellX = startCellX;
        this.currentCellY = startCellY;
        this.cellWidth = cellWidth;
        //center position on cell
        this.positionX = getCurrentCellX()*cellWidth + cellWidth/2;
        this.positionY = getCurrentCellY()*cellWidth + cellWidth/2;
        playerSize = cellWidth/3;
        moving = false;
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
    public int getCurrentCellY()
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
    public Directions getCurrentDirection() { return this.currentDirection; }

    public abstract void drawPlayer(Canvas canvas);

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
        }
        setMoving(moved);
        return gameField[currentCellY+1][currentCellX+1];
    }
}