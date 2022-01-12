package com.example.scorebook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface GameDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CardGame game);

    @Query("DELETE FROM game_table")
    void deleteAll();

    @Query("SELECT * FROM game_table ORDER BY title ASC")
    LiveData<List<CardGame>> getAllGames();



}
