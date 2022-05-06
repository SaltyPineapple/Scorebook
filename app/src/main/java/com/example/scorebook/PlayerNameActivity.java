package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerNameActivity extends AppCompatActivity {

    public static final String Extra_PlayersNames = "PlayerNameActivity.Extra_GamesPlayersNames";

    public int numPlayers;
    public EditText mPlayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        numPlayers = Integer.parseInt(getIntent().getExtras().getString(NewGameActivity.Extra_GamePlayers));

        mPlayerName = findViewById(R.id.editTextTextPersonName);

        init();
    }

    public void init(){
//        for(int x=0; x< numPlayers; x++){
//
//        }
    }

    public void submitPlayersNames(View view){
        String playerName = mPlayerName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Extra_PlayersNames, playerName);
        setResult(RESULT_OK, intent);
        finish();
    }
}