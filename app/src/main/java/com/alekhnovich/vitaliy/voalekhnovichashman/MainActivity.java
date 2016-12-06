package com.alekhnovich.vitaliy.voalekhnovichashman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayField playfield = (PlayField)findViewById(R.id.view);
        playfield.start();
    }
}
