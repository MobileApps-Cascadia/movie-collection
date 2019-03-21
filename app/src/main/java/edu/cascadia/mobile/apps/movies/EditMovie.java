package edu.cascadia.mobile.apps.movies;

import android.annotation.SuppressLint;
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

    private MovieEntity Movie;
    private int id;
    private int List;
    private int run_time;
    private int secondList;

    private EditText eTitle = findViewById(R.id.edit_title);
    private EditText eDirector = findViewById(R.id.edit_director);
    private EditText eYear = findViewById(R.id.edit_year);
    private EditText eRuntime = findViewById(R.id.edit_runtime);
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = movieDatabase.getInstance(this);

        List = mDatabase.movieDao().getCount();



        Movie = mDatabase.movieDao().getMovie(getIntent().getIntExtra(MOVIE_ID_KEY,0));



        if (Movie != null) {
            eDirector.setText(Movie.getDirector());
            eTitle.setText(Movie.getTitle());
            id = Movie.getId();
            eRuntime.setText(Integer.toString(Movie.getRunTime()));
            eYear.setText(Movie.getYear());
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO manage movie update by setting movie id if avaliable
                int movieId = 0;

                //Add Movie

                run_time = Integer.parseInt(eRuntime.getText().toString());

                MovieEntity mMovie = new MovieEntity(
                        movieId,
                        eTitle.getText().toString(),
                        eDirector.getText().toString(),
                        eYear.getText().toString(),
                        run_time
                        );

                mDatabase.movieDao().addOrUpdate(mMovie);

                secondList = mDatabase.movieDao().getCount();



                if (List == secondList) {

                    Toast.makeText(EditMovie.this, "Movie Updated", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(EditMovie.this, "New Movie Added", Toast.LENGTH_SHORT).show();
                }

                finish();
                Snackbar.make(view, "Movie Added to Database", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
