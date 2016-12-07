package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        PlayField playField = (PlayField)findViewById(R.id.view);
        playField.stop();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        int ghostCount = pref.getInt(getString(R.string.preferences_levelone_ghosts_key), 2);
        int ghostsPerLevel = pref.getInt(getString(R.string.preferences_ghosts_added_key), 2);

        PlayField playField = (PlayField)findViewById(R.id.view);
        playField.setPreferences(ghostCount, ghostsPerLevel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.action_about:
                Toast.makeText(this,
                        "Final Ashman Project, Fall 2016, Vitaliy O Alekhnovich",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
