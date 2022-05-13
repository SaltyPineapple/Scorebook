package com.example.scorebook;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardGameRepository {
    private GameDAO mGameDAO;
    private LiveData<List<CardGame>> mAllGames;

    CardGameRepository(Application application){
        CardGameRoomDatabase db = CardGameRoomDatabase.getDatabase(application);
        mGameDAO = db.gameDAO();
        mAllGames = mGameDAO.getAllGames();
    }

    LiveData<List<CardGame>> getAllGames(){
        return mAllGames;
    }

    public void insert(CardGame game){
        new insertAsyncTask(mGameDAO).execute(game);
    }

    public void deleteAll() { new deleteAllGamesAsyncTask(mGameDAO).execute(); }

    public void deleteGame(CardGame game) { new deleteGameAsyncTask(mGameDAO).execute(game); }

    public void updateScores(String title, String scores) { new updateGameAsyncTask(mGameDAO).execute(title, scores); }

    private static class insertAsyncTask extends AsyncTask<CardGame, Void, Void> {
        private GameDAO mAsyncGameDAO;

        insertAsyncTask(GameDAO dao){
            mAsyncGameDAO = dao;
        }

        @Override
        protected Void doInBackground(final CardGame ... params){
            mAsyncGameDAO.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {

        private GameDAO mAsyncGameDAO;

        deleteAllGamesAsyncTask(GameDAO dao){
            this.mAsyncGameDAO = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncGameDAO.deleteAll();
            return null;
        }
    }

    private static class deleteGameAsyncTask extends AsyncTask<CardGame, Void, Void> {
        private GameDAO mAsyncGameDAO;

        deleteGameAsyncTask(GameDAO dao){
            this.mAsyncGameDAO = dao;
        }

        @Override
        protected Void doInBackground(final CardGame ... params){
            mAsyncGameDAO.deleteGame(params[0]);
            return null;
        }
    }

    private static class updateGameAsyncTask extends AsyncTask<String, String, Void> {
        private GameDAO mAsyncGameDao;

        updateGameAsyncTask(GameDAO dao) { this.mAsyncGameDao = dao; }

        @Override
        protected Void doInBackground(String... strings) {
            mAsyncGameDao.updateScores(strings[0], strings[1]);
            return null;
        }
    }

}
