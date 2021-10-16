package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity {

    public static final String Extra_GameTitle = "NewGameActivity.Extra_GameTitle";
    public static final String Extra_GameDesc = "NewGameActivity.Extra_GameDesc";

    public EditText et_newGame_title;
    public EditText et_newGame_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        et_newGame_title = findViewById(R.id.et_newGame_gameTitle);
        et_newGame_desc = findViewById(R.id.et_newGame_gameDesc);



    }

    public void createGame(View view){
        String title = et_newGame_title.getText().toString();
        String desc = et_newGame_desc.getText().toString();

        Intent createGameIntent = new Intent();
        createGameIntent.putExtra(Extra_GameTitle, title);
        createGameIntent.putExtra(Extra_GameDesc, desc);
        setResult(RESULT_OK, createGameIntent);
        finish();

    }
}