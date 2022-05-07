package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class NewGameActivity extends AppCompatActivity {

    public static final String Extra_GameTitle = "NewGameActivity.Extra_GameTitle";
    public static final String Extra_GameDesc = "NewGameActivity.Extra_GameDesc";
    public static final String Extra_GamePlayers = "NewGameActivity.Extra_GamePlayers";
    public static final String Extra_GamePlayersNames = "NewGameActivity.Extra_GamesPlayersNames";
    private static final int NAME_PLAYERS_REQUEST = 1;

    public EditText mTitle;
    public EditText mDesc;
    public EditText mPlayers;
    public String playerNames;
    public Button submitButton;
    public Button addPlayersButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        mTitle = findViewById(R.id.et_newGame_gameTitle);
        mDesc = findViewById(R.id.et_newGame_gameDesc);
        mPlayers = findViewById(R.id.et_newGame_Players);
        submitButton = findViewById(R.id.bt_createGame);
        addPlayersButton = findViewById(R.id.bt_addPlayers);
        submitButton.setEnabled(false);

    }

    public void createGame(View view){
        String title = mTitle.getText().toString();
        String desc = mDesc.getText().toString();
        String players = mPlayers.getText().toString();

        Intent createGameIntent = new Intent();
        createGameIntent.putExtra(Extra_GameTitle, title);
        createGameIntent.putExtra(Extra_GameDesc, desc);
        createGameIntent.putExtra(Extra_GamePlayers, players);
        createGameIntent.putExtra(Extra_GamePlayersNames, playerNames);

        setResult(RESULT_OK, createGameIntent);
        finish();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NAME_PLAYERS_REQUEST && resultCode == RESULT_OK){
            playerNames = data.getStringExtra(PlayerNameActivity.Extra_PlayersNames);
            submitButton.setEnabled(true);
            addPlayersButton.setEnabled(false);
        }
    }

    public void addPlayers(View view) {
        String players = mPlayers.getText().toString();
        Intent namePlayersIntent = new Intent(this,PlayerNameActivity.class);
        namePlayersIntent.putExtra(Extra_GamePlayers, players);
        startActivityForResult(namePlayersIntent, NAME_PLAYERS_REQUEST);
    }
}