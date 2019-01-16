package com.example.ruchs.gameoflife.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ruchs.gameoflife.GameOfLifeBoard_View.GameOfLifeView;
import com.example.ruchs.gameoflife.R;

public class GameOfLifeMainActivity extends AppCompatActivity {
    GameOfLifeView gameOfLifeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_of_life_main);
        gameOfLifeView=findViewById(R.id.gameOfLife_View);
    }
    @Override
    protected void onResume() {
        super.onResume();
        gameOfLifeView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameOfLifeView.stop();
    }

}
