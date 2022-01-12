package com.example.scorebook;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
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

    private final int imageResource;

    CardGame(@NonNull String title, @NonNull String desc, int players, int imageResource){
        this.title = title;
        this.desc = desc;
        this.players = players;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }


    public int getImageResource() {
        return imageResource;
    }
}
