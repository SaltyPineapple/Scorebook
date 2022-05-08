package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

// was going to be used if I wanted to create a new round each time with a new activity
// (entering scores within a different screen and then that is what comes up on the score board)
// (non-editable scores on scoreboard)
public class NewRoundActivity extends AppCompatActivity {

    public static final String Extra_Scores= "NewRoundActivity.Extra_Scores";
    public String[] playerNames;
    public int playerCount;

    ConverterHelper converter = new ConverterHelper();

    Button bt_game;
    TableLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_round);

        playerNames = getIntent().getStringArrayExtra("playerNames");
        playerCount = getIntent().getIntExtra("playerCount",0);

        mLayout = findViewById(R.id.tl_scorePlayers);

        init();

        bt_game = findViewById(R.id.bt_addRound);
        bt_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(Extra_Scores, concat());
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });



    }

    public void init(){
        for(int x=0; x<playerCount; x++){
            TableRow scoreRow = new TableRow(this);

            TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(converter.convertDPS(120, this), converter.convertDPS(10, this), converter.convertDPS(60, this),0);


            EditText playerScore = new EditText(this);
            playerScore.setLayoutParams(params);
            playerScore.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_SIGNED);
            String name = playerNames[x] + ":";
            playerScore.setHint(name);
            playerScore.setTextSize(24);
            playerScore.setEms(5);
            playerScore.setHeight(converter.convertDPS(48,this));

            scoreRow.addView(playerScore);

            mLayout.addView(scoreRow);
        }
    }

    public String concat(){
        StringBuilder scores = new StringBuilder();
        for(int x=0; x< mLayout.getChildCount(); x++){
            TableRow row = (TableRow) mLayout.getChildAt(x);
            EditText item = (EditText)row.getChildAt(0);
            scores.append(item.getText().toString());
            scores.append(",");
        }
        return scores.toString();
    }



}