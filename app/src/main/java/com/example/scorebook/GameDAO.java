package com.example.scorebook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GameDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CardGame game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Delete
    void deleteGame(CardGame game);

    @Query("SELECT * FROM game_table ORDER BY title ASC")
    LiveData<List<CardGame>> getAllGames();

    @Query("Update game_table set scores = :scores where title = :title")
    void updateScores(String title, String scores);

}
