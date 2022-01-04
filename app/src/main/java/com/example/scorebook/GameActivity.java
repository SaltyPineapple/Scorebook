package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    public TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTitleTextView = findViewById(R.id.gameTitle);
        mTitleTextView.setText(getIntent().getStringExtra("title"));

        int players = getIntent().getIntExtra("players", 0);


        init(players);


    }

    public void init(int players){
        TableLayout scoreTable = (TableLayout) findViewById(R.id.scoreTable);
        for(int x=0; x<players-1; x++){
            TableRow tbrow = new TableRow(this);

            EditText etRow = new EditText(this);
            etRow.setText("Player" + x+1);
            etRow.setTextSize(24);

            tbrow.addView(etRow);

            scoreTable.addView(tbrow);

        }

    }
}