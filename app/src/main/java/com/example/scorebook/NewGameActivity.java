package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity {

    public static final String Extra_GameTitle = "NewGameActivity.Extra_GameTitle";
    public static final String Extra_GameDesc = "NewGameActivity.Extra_GameDesc";
    public static final String Extra_GamePlayers = "NewGameActivity.Extra_GamePlayers";

    public EditText mTitle;
    public EditText mDesc;
    public EditText mPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        mTitle = findViewById(R.id.et_newGame_gameTitle);
        mDesc = findViewById(R.id.et_newGame_gameDesc);
        mPlayers = findViewById(R.id.et_newGame_Players);



    }

    public void createGame(View view){
        String title = mTitle.getText().toString();
        String desc = mDesc.getText().toString();
        String players = mPlayers.getText().toString();


        Intent createGameIntent = new Intent();
        createGameIntent.putExtra(Extra_GameTitle, title);
        createGameIntent.putExtra(Extra_GameDesc, desc);
        createGameIntent.putExtra(Extra_GamePlayers, players);
        setResult(RESULT_OK, createGameIntent);
        finish();

    }
}