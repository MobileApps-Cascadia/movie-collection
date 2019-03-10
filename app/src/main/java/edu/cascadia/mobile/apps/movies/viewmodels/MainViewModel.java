package edu.cascadia.mobile.apps.movies.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import edu.cascadia.mobile.apps.movies.database.MovieRepository;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

public class MainViewModel extends AndroidViewModel {
    public LiveData<List<MovieEntity>> mMovies;
    private MovieRepository mRepository;

    public MainViewModel(@NonNull Application application){
        super(application);

        mRepository = MovieRepository.getInstance(application.getApplicationContext());
        mMovies = mRepository.mMovies;
    }

    public void addSampleData(){
        mRepository.addSampleData();
    }
}
