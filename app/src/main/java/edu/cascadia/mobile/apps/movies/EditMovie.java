package edu.cascadia.mobile.apps.movies;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = movieDatabase.getInstance(this);

        //access current intent to get id stored earlier in putextra()
        final Intent intent = this.getIntent();

        //use -1 as a flag for a movie not yet in the database
        final int mId = intent.getIntExtra("movieId",-1);

        //if the movie was found in the DB...
        if (mId != -1) {
            //get current movie
            MovieEntity selectedMovie = mDatabase.movieDao().getMovie(mId);

            //get and set existing movie data in fragment EditText views
            ((EditText)findViewById(R.id.edit_title)).setText(selectedMovie.getTitle());
            ((EditText)findViewById(R.id.edit_director)).setText(selectedMovie.getDirector());
            ((EditText)findViewById(R.id.edit_year)).setText(selectedMovie.getYear());
            ((EditText)findViewById(R.id.edit_runtime)).setText(Integer.toString(selectedMovie.getRunTime()));
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO manage movie update by setting movie id if avaliable

                //data variables
                EditText eTitle = findViewById(R.id.edit_title);
                EditText eDirector = findViewById(R.id.edit_director);
                EditText eYear = findViewById(R.id.edit_year);
                EditText eRuntime = findViewById(R.id.edit_runtime);

                int runtime = Integer.parseInt(eRuntime.getText().toString());

                MovieEntity mMovie;

                //handle new movie or existing case id
                boolean isNew = (mId == -1);
                int movieId;
                String snackBarMessage;

                if (isNew) {
                    movieId = mDatabase.movieDao().getMaxId() + 1;
                    snackBarMessage = "Added New Record: " + eTitle.getText().toString();
                }
                else {
                    movieId = mId;
                    snackBarMessage = "Updated Existing Record: " + eTitle.getText().toString();
                }

                mMovie = new MovieEntity(
                        movieId,
                        eTitle.getText().toString(),
                        eDirector.getText().toString(),
                        eYear.getText().toString(),
                        runtime
                );
                mDatabase.movieDao().addOrUpdate(mMovie);

                Snackbar.make(view, snackBarMessage, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
