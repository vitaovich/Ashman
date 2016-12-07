package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView upButton, downButton, leftButton, rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upButton = (ImageView) findViewById(R.id.up);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                playfield.ashMan.setCurrentDirection(Directions.Up);
            }
        });

        downButton = (ImageView) findViewById(R.id.down);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                playfield.ashMan.setCurrentDirection(Directions.Down);
            }
        });

        leftButton = (ImageView) findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                playfield.ashMan.setCurrentDirection(Directions.Left);
            }
        });

        rightButton = (ImageView) findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                playfield.ashMan.setCurrentDirection(Directions.Right);
            }
        });

        TextView instructions = (TextView) findViewById(R.id.instructions);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                if(playfield.isRunning())
                {
                    playfield.stop();
                }
                else
                {
                    playfield.start();
                }
            }
        });


        instructions.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PlayField playfield = (PlayField)findViewById(R.id.view);
                playfield.EnableCheat();
                return true;
            }
        });
    }
}
