package edu.cascadia.mobile.apps.movies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.cascadia.mobile.apps.movies.database.AppRepository;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class EditViewModel extends AndroidViewModel {

    public MutableLiveData<MovieEntity> mLiveMovie =
            new MutableLiveData<>();

    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());

    }

    public void loadData(final int movieId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                MovieEntity movie = mRepository.getMovieById(movieId);
                mLiveMovie.postValue(movie);
            }
        });

    }

    public void saveOrUpdateMovie(String title, String director, String year, int runTime) {
        MovieEntity movie = mLiveMovie.getValue();

        if (movie == null) {
            if (TextUtils.isEmpty(title.trim())) {
                return;
            }
            movie = new MovieEntity(title.trim(), director.trim(), year.trim(), runTime);
        } else {
            movie.setTitle(title.trim());
            movie.setDirector(director.trim());
            movie.setYear(year.trim());
            movie.setRunTime(runTime);
        }
        mRepository.insertMovie(movie);
    }

    public void deleteMovie() {
        mRepository.deleteMovie(mLiveMovie.getValue());
    }
}
