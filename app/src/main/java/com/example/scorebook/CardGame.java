package com.example.scorebook;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_table")
public class CardGame implements Parcelable {

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

    @ColumnInfo(name="scores")
    private String scores;

    private final int imageResource;

    CardGame(@NonNull String title, @NonNull String desc, int players, String playerNames, String scores, int imageResource){
        this.title = title;
        this.desc = desc;
        this.players = players;
        this.imageResource = imageResource;
        this.playerNames = playerNames;
        this.scores = scores;
    }

    protected CardGame(Parcel in) {
        title = in.readString();
        desc = in.readString();
        players = in.readInt();
        playerNames = in.readString();
        scores = in.readString();
        imageResource = in.readInt();
    }

    public static final Creator<CardGame> CREATOR = new Creator<CardGame>() {
        @Override
        public CardGame createFromParcel(Parcel in) {
            return new CardGame(in);
        }

        @Override
        public CardGame[] newArray(int size) {
            return new CardGame[size];
        }
    };

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

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeInt(players);
        parcel.writeInt(imageResource);
        parcel.writeString(scores);
    }
}
