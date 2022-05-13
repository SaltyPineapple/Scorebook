package com.example.scorebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_GAME_REQUEST = 1;

    private RecyclerView mRecyclerView;
    private CardGameAdapter mAdapter;

    private CardGameViewModel mGameViewModel;

    public String scoresFromActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoresFromActivity = getIntent().getStringExtra(GameActivity.Extra_Scores);
        CardGame currentGame = (CardGame) getIntent().getParcelableExtra(GameActivity.Extra_CurrentGame);

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

        updateScores(currentGame);

        // Item Touch Helper for deleting games
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                CardGame game = mAdapter.getGameAtPosition(position);

                AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
                myAlertBuilder.setTitle("Delete?");
                myAlertBuilder.setMessage("Are you sure you want to delete this?");

                myAlertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Deleting " + game.getTitle() + "...", Toast.LENGTH_SHORT).show();
                        mGameViewModel.deleteGame(game);
                    }
                });

                myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(), "Pressed Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertBuilder.show();
            }
        });

        helper.attachToRecyclerView(mRecyclerView);

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
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivityForResult(intent,NEW_GAME_REQUEST);
    }

    private void updateScores(CardGame game){
        if(game != null) {
            mGameViewModel.updateScores(game.getTitle(), scoresFromActivity);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_GAME_REQUEST && resultCode == RESULT_OK){
            String newGameTitle = data.getStringExtra(NewGameActivity.Extra_GameTitle);
            String newGameDesc = data.getStringExtra(NewGameActivity.Extra_GameDesc);
            String playerString = data.getStringExtra(NewGameActivity.Extra_GamePlayers);
            String playerNames = data.getStringExtra(NewGameActivity.Extra_GamePlayersNames);
            String scores = data.getStringExtra(GameActivity.Extra_Scores);
            int players = Integer.parseInt(playerString);

            TypedArray gameImageResource = getResources().obtainTypedArray(R.array.game_images);
            Random rand = new Random();

            CardGame newGame = new CardGame(newGameTitle, newGameDesc, players, playerNames, scores, gameImageResource.getResourceId(rand.nextInt(10), 0));
            mGameViewModel.insert(newGame);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.clear_data){
            Toast.makeText(this, "Deleting all games...",
                    Toast.LENGTH_SHORT).show();
            mGameViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}