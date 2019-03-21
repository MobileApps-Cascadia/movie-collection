package edu.cascadia.mobile.apps.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

public class MainViewModel extends AndroidViewModel {



    public List<MovieEntity> mMovies = SampleData.getMovies();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
