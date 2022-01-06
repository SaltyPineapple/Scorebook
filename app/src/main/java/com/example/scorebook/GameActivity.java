package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
                newRound();
                Snackbar.make(v, "Added round!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });


    }

    public void init(){
        TableLayout scoreTable = (TableLayout) findViewById(R.id.scoreTable);
        for(int x=0; x<players; x++){
            TableRow tbrow = new TableRow(this);
            tbrow.setId(100 + x);

            EditText etPlayerRow = new EditText(this);
            String text = "Player " + (x+1);
            etPlayerRow.setText(text);
            etPlayerRow.setTextSize(24);
            tbrow.addView(etPlayerRow);

            EditText etScoreRow = new EditText(this);
            etScoreRow.setTextSize(24);
            etScoreRow.setInputType(InputType.TYPE_CLASS_NUMBER);
            etScoreRow.setMinWidth(100);
            tbrow.addView(etScoreRow);
            etScoreRow.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

            scoreTable.addView(tbrow);

        }

    }

    public void newRound(){
        TableLayout scoreTable = (TableLayout) findViewById(R.id.scoreTable);

        for(int x=0; x<players; x++){
            TableRow tbrow = findViewById(100+x);
            EditText etScoreRow = new EditText(this);
            etScoreRow.setTextSize(24);
            etScoreRow.setMinWidth(100);
            etScoreRow.setInputType(InputType.TYPE_CLASS_NUMBER);
            tbrow.addView(etScoreRow);

            etScoreRow.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
            //scoreTable.addView(etRow);
        }

    }
}