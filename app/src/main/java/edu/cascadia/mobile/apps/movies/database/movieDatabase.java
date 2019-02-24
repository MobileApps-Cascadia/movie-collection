package edu.cascadia.mobile.apps.movies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 2, exportSchema = false)
public abstract class movieDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MovieDatabase.db";
    private static volatile  movieDatabase instance;
    private static final Object LOCK = new Object();

    public abstract MovieDao movieDao();

    public static movieDatabase getInstance(Context context){
        if (instance == null){
            synchronized (LOCK){
                if (instance == null)
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            movieDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return instance;
    }
}
