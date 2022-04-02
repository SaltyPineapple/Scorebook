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
    public TableLayout mTableLayout;
    public TableRow mTotalScore;
    private int players;
    private int roundCounter = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTitleTextView = findViewById(R.id.gameTitle);
        mTitleTextView.setText(getIntent().getStringExtra("title"));
        mTableLayout = findViewById(R.id.scoreTable);
        mTotalScore = findViewById(R.id.scoresTotal);

        int players = getIntent().getIntExtra("players", 0);
        this.players = players;


        init();

        FloatingActionButton fab = findViewById(R.id.fabNewRound);
        fab.setOnClickListener(v -> {
            newRound();
        });


    }

    public void init(){
        TableRow playerRow = new TableRow(this);
        int playerRowId = 1;
        playerRow.setId(playerRowId);

        TableRow scoreRowOne = new TableRow(this);
        scoreRowOne.setId(roundCounter);

        for(int x=0; x<players; x++){
            // add players row
            TextView tvPlayerRow = new TextView(this);
            String text = "Player " + (x+1);
            tvPlayerRow.setText(text);
            tvPlayerRow.setTextSize(24);
            tvPlayerRow.setId(100 + x);
            playerRow.addView(tvPlayerRow);

            // add first round scores
            EditText etScoreRow = new EditText(this);
            String textScore = "0";
            etScoreRow.setHint(textScore);
            etScoreRow.setTextSize(24);
            etScoreRow.setId(Integer.parseInt(roundCounter + "00" + x));
            etScoreRow.setInputType(InputType.TYPE_CLASS_NUMBER);
            scoreRowOne.addView(etScoreRow);

            // add totals at bottom
            // todo: bold the font
            TextView tvTotalScore = new TextView(this);
            tvTotalScore.setHint(textScore);
            tvTotalScore.setTextSize(24);
            tvTotalScore.setId(Integer.parseInt(roundCounter + "10" + x));
            mTotalScore.addView(tvTotalScore);

        }
        roundCounter++;
        mTableLayout.addView(playerRow,0);
        mTableLayout.addView(scoreRowOne);

    }

    // add a new row of empty edit texts
    public void newRound(){
        TableRow scoreRow = new TableRow(this);
        scoreRow.setId(roundCounter);
        for(int x=0; x<players; x++){
            EditText etScoreRow = new EditText(this);
            String textScore = "0";
            etScoreRow.setHint(textScore);
            etScoreRow.setTextSize(24);
            etScoreRow.setId(Integer.parseInt(roundCounter + "00" + x));
            etScoreRow.setInputType(InputType.TYPE_CLASS_NUMBER);
            scoreRow.addView(etScoreRow);
        }
        roundCounter++;
        mTableLayout.addView(scoreRow);
    }

    public void sumScores(){

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}