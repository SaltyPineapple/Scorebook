package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameActivity extends AppCompatActivity {

    public static final String Extra_Scores= "GameActivity.Extra_Scores";

    private static final int NEW_ROUND_REQUEST = 1;

    public TextView mTitleTextView;
    public TableLayout mTableLayout;
    public TableRow mTotalScore;
    private int playerCount;
    private String playerNamesCSV;
    private String playerScoresCSV;
    private int roundCounter = 0;
    private String[] playerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTitleTextView = findViewById(R.id.gameTitle);
        mTitleTextView.setText(getIntent().getStringExtra("title"));
        mTableLayout = findViewById(R.id.scoreTable);
        mTotalScore = findViewById(R.id.scoresTotal);

        playerCount = getIntent().getIntExtra("players", 0);
        playerNamesCSV = getIntent().getStringExtra("playerNames");

        // change this to restore() which will just grab everything from the db and restore it
        init();
        restore();

        // open a new activity for adding rounds
        FloatingActionButton fab = findViewById(R.id.fabNewRound);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewRoundActivity.class);
            // puts the player count into the next activity
            intent.putExtra("playerCount", playerCount);
            // puts the players names into the next activity
            intent.putExtra("playerNames", playerNames);

            startActivityForResult(intent, NEW_ROUND_REQUEST);
            Toast.makeText(
                    getApplicationContext(),
                    "Add a new round!",
                    Toast.LENGTH_SHORT).show();


        });

    }

    // this method will restore all items from the db into the proper place
    public void restore(){

    }

    public void init(){
        TableRow playerRow = new TableRow(this);
        int playerRowId = 1;
        playerRow.setId(playerRowId);

        TableRow scoreRowOne = new TableRow(this);
        scoreRowOne.setId(roundCounter);

        for(int x=0; x<playerCount; x++){
            // add players row
            TextView tvPlayerRowItem = new TextView(this);
            tvPlayerRowItem.setLayoutParams(new TableRow.LayoutParams());
            // tvPlayerRowItem.setText(playerNames);
            tvPlayerRowItem.setTextSize(24);
            tvPlayerRowItem.getLayoutParams().width = 300;
            tvPlayerRowItem.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            playerRow.addView(tvPlayerRowItem);

            // add totals at top
            TextView tvTotalScoreItem = new TextView(this);
            tvTotalScoreItem.setTypeface(Typeface.DEFAULT_BOLD);
            tvTotalScoreItem.setText("0");
            tvTotalScoreItem.setTextSize(24);
            tvTotalScoreItem.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            mTotalScore.addView(tvTotalScoreItem);
        }
        roundCounter++;
        mTableLayout.addView(playerRow,0);
        mTableLayout.addView(scoreRowOne);

        // this loop breaks up the csv player names
        playerNames = playerNamesCSV.split(",");
        for (String name:
             playerNames) {
            name = name.trim();
        }

        // this loop sets each of the player names correctly
        for(int x=0; x<playerRow.getChildCount(); x++){
            TextView child = (TextView)playerRow.getChildAt(x);
            child.setText(playerNames[x]);
        }

    }

    // add a new row of empty edit texts
    public void newRound(){
        // split the score into an array
        String[] playerScores = playerScoresCSV.split(",");

        TableRow scoreRow = new TableRow(this);

        for(int x=0; x<playerCount; x++){
            TextView score = new TextView(this);
            score.setTextSize(24);
            score.setText(playerScores[x]);
            scoreRow.addView(score);
        }
        mTableLayout.addView(scoreRow);
        sumScores();
    }

       /*
       for each player
       grab each score total it up
       set proper textview to the total
        */
    public void sumScores(){
        // initialize totals arr
        int[] totals = new int[playerCount];
        for(int j=0; j<playerCount; j++){
            totals[j] = 0;
        }

        // sum up each player scores
        for(int x=2; x< mTableLayout.getChildCount(); x++){
            // gets row
            TableRow row = (TableRow) mTableLayout.getChildAt(x);
            for(int y=0; y<row.getChildCount(); y++){
                // gets the current player view
                TextView view = (TextView) row.getChildAt(y);
                totals[y] += Integer.parseInt(view.getText().toString());
            }
        }

        // set the text of the totals to match
        TableRow scoresRow = (TableRow) mTableLayout.getChildAt(1);
        for(int i=0; i<scoresRow.getChildCount(); i++){
            TextView score = (TextView) scoresRow.getChildAt(i);
            score.setText(String.valueOf(totals[i]));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ROUND_REQUEST && resultCode == RESULT_OK){
            playerScoresCSV = data.getStringExtra(NewRoundActivity.Extra_Scores);
            newRound();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.updateScore){
            Toast.makeText(this, "Saving Round...",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}