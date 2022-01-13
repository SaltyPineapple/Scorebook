package com.example.scorebook;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CardGame.class}, version = 1, exportSchema = false)
public abstract class CardGameRoomDatabase extends RoomDatabase {
    public abstract GameDAO gameDAO();

    private static CardGameRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    public static CardGameRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CardGameRoomDatabase.class){
                if (INSTANCE == null) {
                    // create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardGameRoomDatabase.class, "cardgame_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {
        private final GameDAO mDao;





        PopulateDbAsync(CardGameRoomDatabase db){
            mDao = db.gameDAO();
        }

        @Override
        protected Void doInBackground(final Void ... params){

            //mDao.deleteAll();
            

            return null;
        }
    }
}
