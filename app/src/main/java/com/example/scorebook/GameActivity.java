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

    private static final int NEW_ROUND_REQUEST = 1;

    public TextView mTitleTextView;
    public TableLayout mTableLayout;
    public TableRow mTotalScore;
    private int players;
    private String playerNamesCSV;
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

        players = getIntent().getIntExtra("players", 0);
        playerNamesCSV = getIntent().getStringExtra("playerNames");

        // change this to restore() which will just grab everything from the db and restore it
        init();
        restore();

        // open a new activity for adding rounds
        FloatingActionButton fab = findViewById(R.id.fabNewRound);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewRound.class);
            // puts the player count into the next activity
            intent.putExtra("PLAYERS", players);
            // puts the players names into the next activity

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

        for(int x=0; x<players; x++){
            // add players row
            TextView tvPlayerRowItem = new TextView(this);
            tvPlayerRowItem.setLayoutParams(new TableRow.LayoutParams());
            // tvPlayerRowItem.setText(playerNames);
            tvPlayerRowItem.setTextSize(24);
            tvPlayerRowItem.setId(100 + x);
            tvPlayerRowItem.getLayoutParams().width = 400;
            tvPlayerRowItem.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            playerRow.addView(tvPlayerRowItem);

            // add totals at top
            TextView tvTotalScoreItem = new TextView(this);
            tvTotalScoreItem.setTypeface(Typeface.DEFAULT_BOLD);
            tvTotalScoreItem.setText("0");
            tvTotalScoreItem.setTextSize(24);
            tvTotalScoreItem.setId(Integer.parseInt( x + "10"));
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

       /*
       for each player
       grab each score total it up
       set proper textview to the total
        */
    public void sumScores(){

        for(int x=0; x< mTableLayout.getChildCount(); x++){
            // gets row
            TextView view = (TextView) mTableLayout.getChildAt(x);

            Toast.makeText(this, view.getText().toString(),
                    Toast.LENGTH_SHORT).show();

        }

        /*for(int x=0; x<players; x++){
            int sum = 0;
            for(int y=0; y<roundCounter; y++){
                String id = y + "00" + x;
                EditText tvRound = findViewById(Integer.parseInt(id));
                int roundScore;
                if(tvRound.getText() == null){
                    roundScore = 0;
                }
                else{
                    roundScore = Integer.parseInt(tvRound.getText().toString());
                }
                sum += roundScore;
            }
            TextView tvTotal = findViewById(Integer.parseInt(x + "10"));
            tvTotal.setText(sum);
        }*/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
            sumScores();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}