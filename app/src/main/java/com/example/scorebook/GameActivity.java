package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class GameActivity extends AppCompatActivity {

    public TextView mTitleTextView;
    private int players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTitleTextView = findViewById(R.id.gameTitle);
        mTitleTextView.setText(getIntent().getStringExtra("title"));

        int players = getIntent().getIntExtra("players", 0);
        this.players = players;


        init();

        FloatingActionButton fab = findViewById(R.id.fabNewRound);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, NewRound.class);
                startActivityForResult(intent, RESULT_OK);
            }
        });


    }

    public void init(){
        TableLayout scoreTable = (TableLayout) findViewById(R.id.scoreTable);

        TableRow playerRow = new TableRow(this);
        int playerRowId = 1;
        playerRow.setId(playerRowId);

        TableRow scoreRowOne = new TableRow(this);
        int scoreRowOneId = 2;
        scoreRowOne.setId(scoreRowOneId);

        for(int x=0; x<players; x++){
            TextView tvPlayerRow = new TextView(this);
            String text = "Player " + (x+1);
            tvPlayerRow.setText(text);
            tvPlayerRow.setTextSize(24);
            tvPlayerRow.setId(100 + x);
            playerRow.addView(tvPlayerRow);

            TextView tvScoreRow = new TextView(this);
            String textScore = "10";
            tvScoreRow.setText(textScore);
            tvScoreRow.setTextSize(24);
            tvScoreRow.setId(200 + x);
            scoreRowOne.addView(tvScoreRow);
        }
        scoreTable.addView(playerRow);
        scoreTable.addView(scoreRowOne);

    }

    // this needs to open a new activity to enter in scores
    // call this from the FAB instead
    public void newRound(){


//        TableLayout scoreTable = (TableLayout) findViewById(R.id.scoreTable);
//
//        for(int x=0; x<players; x++){
//            TableRow tbrow = findViewById(100+x);
//            EditText etScoreRow = new EditText(this);
//            etScoreRow.setTextSize(24);
//            etScoreRow.setMinWidth(100);
//            etScoreRow.setInputType(InputType.TYPE_CLASS_NUMBER);
//            tbrow.addView(etScoreRow);
//
//            etScoreRow.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//            //scoreTable.addView(etRow);
//        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}