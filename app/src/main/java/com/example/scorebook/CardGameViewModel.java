package com.example.scorebook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CardGameViewModel extends AndroidViewModel {
    private CardGameRepository mRepository;
    private LiveData<List<CardGame>> mAllGames;

    public CardGameViewModel(Application application){
        super(application);
        mRepository = new CardGameRepository(application);
        mAllGames = mRepository.getAllGames();
    }

    LiveData<List<CardGame>> getAllGames() { return mAllGames; }

    public void insert(CardGame game){ mRepository.insert(game); }
}
