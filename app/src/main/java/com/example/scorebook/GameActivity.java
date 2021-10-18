package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    public TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextView = findViewById(R.id.gameTitle);
        mTextView.setText(getIntent().getStringExtra("title"));

    }
}