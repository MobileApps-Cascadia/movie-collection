package edu.cascadia.mobile.apps.movies.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();

    public LiveData<List<MovieEntity>> mMovies;
    private movieDatabase mDatabase;

    public static AppRepository getInstance() {
        return ourInstance;
    }

    private AppRepository() {
        mMovies = getAllMovies();
    }

    private LiveData<List<MovieEntity>> getAllMovies(){
        return mDatabase.movieDao().getMovies();
    }

    public void addSampleData() {

    }
}
