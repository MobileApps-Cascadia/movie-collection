package edu.cascadia.mobile.apps.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import edu.cascadia.mobile.apps.movies.database.AppRepository;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<MovieEntity>> mMovies;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mMovies = mRepository.mMovies;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllData() {
        mRepository.deleteAllData();
    }
}
