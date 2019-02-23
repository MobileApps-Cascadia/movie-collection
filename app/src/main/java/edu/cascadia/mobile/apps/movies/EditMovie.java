package edu.cascadia.mobile.apps.movies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.cascadia.mobile.apps.movies.database.movieDatabase;;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class EditMovie extends AppCompatActivity {
    private movieDatabase mDatabase;
    public static String MOVIE_ID_KEY = "id";

    private MovieEntity MOVIE;
    private int movieId;
    private int movieListSize;
    private int newMovieListSize;
    private int runtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText eTitle = findViewById(R.id.edit_title);
        final EditText eDirector = findViewById(R.id.edit_director);
        final EditText eYear = findViewById(R.id.edit_year);
        final EditText eRuntime = findViewById(R.id.edit_runtime);

        mDatabase = movieDatabase.getInstance(this);

        movieListSize = mDatabase.movieDao().getCount();

        MOVIE = mDatabase.movieDao().getMovie(getIntent().getIntExtra(MOVIE_ID_KEY,0));

        if (MOVIE != null) {
            eTitle.setText(MOVIE.getTitle());
            eDirector.setText(MOVIE.getDirector());
            eYear.setText(MOVIE.getYear());
            eRuntime.setText(Integer.toString(MOVIE.getRunTime()));
            movieId = MOVIE.getId();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runtime = Integer.parseInt(eRuntime.getText().toString());

                MovieEntity mMovie = new MovieEntity(
                        movieId,
                        eTitle.getText().toString(),
                        eDirector.getText().toString(),
                        eYear.getText().toString(),
                        runtime
                        );

                mDatabase.movieDao().addOrUpdate(mMovie);

                newMovieListSize = mDatabase.movieDao().getCount();

                if (movieListSize == newMovieListSize) {
                    Toast.makeText(EditMovie.this, "Movie Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditMovie.this, "New Movie Added", Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
