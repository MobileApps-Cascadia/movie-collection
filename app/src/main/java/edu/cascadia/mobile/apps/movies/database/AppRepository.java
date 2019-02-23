package edu.cascadia.mobile.apps.movies.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<MovieEntity>> mMovies;
    private movieDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = movieDatabase.getInstance(context);
        mMovies = getAllMovies();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().addAll(SampleData.getMovies());
            }
        });
    }

    private LiveData<List<MovieEntity>> getAllMovies() {
        return mDb.movieDao().getMovies();
    }

    public void deleteAllData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().removeAll();
            }
        });
    }
}
