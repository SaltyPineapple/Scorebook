package com.example.scorebook;

public class CardGame {
    private String title;
    private String desc;
    private int players;

    private final int imageResource;

    CardGame(String title, String desc, int players, int imageResource){
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
