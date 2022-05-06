package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// was going to be used if I wanted to create a new round each time with a new activity
// (entering scores within a different screen and then that is what comes up on the score board)
// (non-editable scores on scoreboard)
public class NewRound extends AppCompatActivity {

    public static final String[] Extra_GamePlayersNames = new String[] {};

    Button bt_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_round);

        init();

        bt_game = findViewById(R.id.bt_addRound);
        bt_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                replyIntent.putExtra("names",Extra_GamePlayersNames);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });



    }

    public void init(){

    }



}