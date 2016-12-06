package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by vio on 12/5/16.
 */

public class PlayField extends View
{
    public final static int WALL_VALUE = 0;
    public final static int CORRIDOR_VALUE = 1;
    public final static int CAKE_VALUE = 2;
    public final static int POWER_UP_VALUE = 3;
    public final static int PLAYFIELD_WIDTH = 14;
    public final static int PLAYFIELD_HEIGHT = 14;
    public final static int PLAYFIELD_PHYSICAL_COORDS = 10;
    public final static int PLAYFIELD_PHYSICAL_WIDTH = 140;
    public final static int PLAYFIELD_PHYSICAL_HEIGHT = 140;
    public final static String LEVEL_SEED = "levelone";
    private static final int TIMER_MSEC = 70;

    private Handler mHandler;
    private Runnable mTimer;
    private boolean mIsRunning = false;

    float mAspectRatio = 1;
    Player ashMan;
    Ghost ghost;
    int [][] gameField = new int [PLAYFIELD_HEIGHT+2][PLAYFIELD_WIDTH+2];


    public PlayField(Context context)
    {
        super(context);
        commonInit();
    }

    public PlayField(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        commonInit();
    }

    public PlayField(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        commonInit();

    }

    public void commonInit()
    {
        mHandler = new Handler();
        mTimer = new Runnable() {
            @Override
            public void run() {
                onTimer();
                if(mIsRunning)
                    mHandler.postDelayed(this, TIMER_MSEC);
            }
        };
        createLevel(LEVEL_SEED);
        ashMan = new Player(1, 5, PLAYFIELD_PHYSICAL_COORDS);
        ghost = new Ghost(11, 10, PLAYFIELD_PHYSICAL_COORDS);
    }

    protected void onTimer()
    {
        float cx = ashMan.getPositionX();
        if(cx < PLAYFIELD_PHYSICAL_WIDTH-5)
        {
            cx = cx + 5;
        }
        ashMan.setPositionX(cx);
        invalidate();
    }

    public boolean isRunning()
    {
        return mIsRunning;
    }

    public void setRunning(boolean running)
    {
        mIsRunning = running;
    }

    public void start()
    {
        mIsRunning = true;
        mHandler.postDelayed(mTimer, TIMER_MSEC);
    }

    public void stop()
    {
        mIsRunning = false;
        mHandler.removeCallbacks(mTimer);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        float sw = (float)getWidth()/PLAYFIELD_PHYSICAL_WIDTH;
        float sh = (float)getHeight()/PLAYFIELD_PHYSICAL_WIDTH;
        setLayerType(this.LAYER_TYPE_SOFTWARE, null);
        canvas.scale(sw, sh);
        canvas.save();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);


        int row, column;
        RectF rectf;
        for(int i = 0; i < gameField.length-2; i++)
        {
            for(int j = 0; j < gameField[i].length-2; j++)
            {
                rectf = new RectF(j*PLAYFIELD_PHYSICAL_COORDS,i*PLAYFIELD_PHYSICAL_COORDS,
                        (j*PLAYFIELD_PHYSICAL_COORDS)+PLAYFIELD_PHYSICAL_COORDS,
                        (i*PLAYFIELD_PHYSICAL_COORDS)+PLAYFIELD_PHYSICAL_COORDS);
                switch(gameField[i+1][j+1])
                {
                    case WALL_VALUE:
                        paint.setColor(Color.BLACK);
                        drawWall(canvas, paint, rectf);
                        break;
                    case CORRIDOR_VALUE:
                        paint.setColor(Color.BLUE);
                        drawWall(canvas, paint, rectf);
                        break;
                    case CAKE_VALUE:
                        drawCake(canvas, paint, rectf);
                        break;
                    case POWER_UP_VALUE:
                        paint.setColor(Color.RED);
                        drawWall(canvas, paint, rectf);
                        break;
                }
            }

        }
        ashMan.drawPlayer(canvas);
        ghost.drawPlayer(canvas);
    }

    private void drawWall(Canvas canvas, Paint paint, RectF rectf)
    {

        canvas.drawRect(rectf, paint);
    }

    private void drawCake(Canvas canvas, Paint paint, RectF rectF)
    {
        paint.setColor(Color.BLUE);
        drawWall(canvas, paint, rectF);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), (float)1.5, paint);

    }


    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec) ;
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec) ;

        int calcWidth = (int)((float)parentHeight * mAspectRatio) ;
        int calcHeight = (int)((float)parentWidth / mAspectRatio) ;

        int finalWidth, finalHeight;

        if (calcHeight > parentHeight) {
            finalWidth = calcWidth ;
            finalHeight = parentHeight ;
        } else {
            finalWidth = parentWidth ;
            finalHeight = calcHeight ;
        }

        setMeasuredDimension (finalWidth, finalHeight) ;
    }

    private void createLevel(String seed)
    {
        gameField[2][2] = CAKE_VALUE;
        gameField[2][3] = CAKE_VALUE;
        gameField[3][2] = CAKE_VALUE;
        gameField[2][4] = CAKE_VALUE;
        gameField[3][4] = CAKE_VALUE;
        gameField[2][5] = CAKE_VALUE;
        gameField[1][5] = CAKE_VALUE;
        gameField[2][6] = CAKE_VALUE;
        gameField[3][6] = CAKE_VALUE;
        gameField[5][2] = CAKE_VALUE;
        gameField[5][5] = CAKE_VALUE;
        gameField[7][4] = CAKE_VALUE;
        gameField[7][7] = CAKE_VALUE;
        for(int i = 0; i < 8; i++)
        {
            gameField[4][i] = CAKE_VALUE;
        }
        for(int i = 0; i < 8; i++)
        {
            gameField[6][i] = CAKE_VALUE;
        }

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                gameField[i][14-j] = gameField[i][j];
            }
        }
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                gameField[15-i][14-j] = gameField[i][j];
            }
        }
//        Random random = new Random(seed.hashCode());
//        int value;
//        for(int i = 1; i < gameField.length-1; i++)
//        {
//            for(int j = 1; j < gameField[i].length-1; j++)
//            {
//                value = random.nextInt(3);
//                if(value > 0)
//                {
//                    gameField[i][j] = CAKE_VALUE;
//                }
//
//            }
//        }


    }
}