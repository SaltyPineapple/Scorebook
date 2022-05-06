package com.example.scorebook;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_table")
public class CardGame {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @NonNull
    @ColumnInfo(name="desc")
    private String desc;

    @ColumnInfo(name="players")
    private int players;

    @ColumnInfo(name="playerNames")
    private String playerNames;

    private final int imageResource;

    CardGame(@NonNull String title, @NonNull String desc, int players, String playerNames, int imageResource){
        this.title = title;
        this.desc = desc;
        this.players = players;
        this.imageResource = imageResource;
        this.playerNames = playerNames;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public String getPlayerNames() { return playerNames; }

    public void setPlayerNames(String names){ this.playerNames = names; }

    public int getImageResource() {
        return imageResource;
    }
}
