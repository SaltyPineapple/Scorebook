package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_GAME_REQUEST = 1;

    private RecyclerView mRecyclerView;
    private ArrayList<CardGame> mGames;
    private GameAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Recycler View
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set Layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initalize card games
        // will need to pull from data base -- MUCH LATER --
        mGames = new ArrayList<>();

        // Intialize adapter and set to recycler view
        mAdapter = new GameAdapter(this,mGames);
        mRecyclerView.setAdapter(mAdapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
                Snackbar.make(v, "Create a new game!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
    }

    private void newGame() {
        // pull up new activity and return data from it

        Intent intent = new Intent(this, NewGameActivity.class);
        startActivityForResult(intent,NEW_GAME_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_GAME_REQUEST){
            if(resultCode == RESULT_OK){
                String newGameTitle = data.getStringExtra(NewGameActivity.Extra_GameTitle);
                String newGameDesc = data.getStringExtra(NewGameActivity.Extra_GameDesc);

                TypedArray gameImageResource = getResources().obtainTypedArray(R.array.game_images);
                Random rand = new Random();

                mGames.add(new CardGame(newGameTitle, newGameDesc, 4, gameImageResource.getResourceId(rand.nextInt(10), 0)));
                gameImageResource.recycle();

                mAdapter.notifyDataSetChanged();
            }
        }
    }
}