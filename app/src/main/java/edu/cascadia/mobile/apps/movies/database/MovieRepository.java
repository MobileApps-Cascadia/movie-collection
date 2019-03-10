package edu.cascadia.mobile.apps.movies.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

public class MovieRepository {
    private static MovieRepository ourInstance;

    public LiveData<List<MovieEntity>> mMovies;
    private movieDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();
    public static MovieRepository getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new MovieRepository(context);
        }
        return ourInstance;
    }

    private MovieRepository(Context context) {
        mDb = movieDatabase.getInstance(context);
        mMovies = getAllMovies();
    }

    public void addSampleData(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().addAll(SampleData.getMovies());
            }
        });
    }

    private LiveData<List<MovieEntity>> getAllMovies(){
        return mDb.movieDao().getMovies();
    }
}
