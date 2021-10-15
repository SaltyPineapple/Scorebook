package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
                initializeData();
                Snackbar.make(v, "Create a new game!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
    }

    private void initializeData() {
        TypedArray gameImageResource = getResources().obtainTypedArray(R.array.game_images);

        Random rand = new Random();

        mGames.add(new CardGame("New Game", "New Game", 4, gameImageResource.getResourceId(rand.nextInt(10), 0)));
        gameImageResource.recycle();

        mAdapter.notifyDataSetChanged();

    }
}