package com.example.scorebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_GAME_REQUEST = 1;

    private RecyclerView mRecyclerView;
    private List<CardGame> mGames;
    private CardGameAdapter mAdapter;

    private CardGameViewModel mGameViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Recycler View
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new CardGameAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Set Layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mGameViewModel = new ViewModelProvider(this).get(CardGameViewModel.class);

        mGameViewModel.getAllGames().observe(this, new Observer<List<CardGame>>() {
            @Override
            public void onChanged(List<CardGame> cardGames) {
                mAdapter.setGames(cardGames);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();

                Toast.makeText(
                        getApplicationContext(),
                        "Create a new game!",
                        Toast.LENGTH_SHORT).show();
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

        if(requestCode == NEW_GAME_REQUEST && resultCode == RESULT_OK){
            String newGameTitle = data.getStringExtra(NewGameActivity.Extra_GameTitle);
            String newGameDesc = data.getStringExtra(NewGameActivity.Extra_GameDesc);
            String playerString = data.getStringExtra(NewGameActivity.Extra_GamePlayers);
            int players = Integer.parseInt(playerString);

            TypedArray gameImageResource = getResources().obtainTypedArray(R.array.game_images);
            Random rand = new Random();

            CardGame newGame = new CardGame(newGameTitle, newGameDesc, players, gameImageResource.getResourceId(rand.nextInt(10), 0));
            mGameViewModel.insert(newGame);
        }

    }
}