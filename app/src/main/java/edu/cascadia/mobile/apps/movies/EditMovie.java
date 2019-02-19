package edu.cascadia.mobile.apps.movies;

import android.arch.persistence.room.RoomDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobile.apps.movies.database.movieDatabase;
import edu.cascadia.mobile.apps.movies.model.DirectorEntity;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.ui.DirectorsAdapter;

public class EditMovie extends AppCompatActivity {
    private movieDatabase mDatabase;
    private List<DirectorEntity> mDirectorData = new ArrayList<>();
    private RecyclerView mDirectorRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDatabase = movieDatabase.getInstance(this);
        mDirectorRecycler = findViewById(R.id.movie_director_list);
        mDirectorData = movieDatabase.getInstance(this).directorDao().getDirectors();
        initDirectorRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Movie
                EditText mTitle = findViewById(R.id.movie_name);
                EditText mYear = findViewById(R.id.movie_year);
                EditText mRuntime = findViewById(R.id.movie_runtime);
                int runtime = Integer.parseInt(mRuntime.getText().toString());
                MovieEntity mMovie = new MovieEntity(
                        mTitle.getText().toString(),
                        mYear.getText().toString(),
                        runtime,
                        false,
                        1
                        );

                mDatabase.movieDao().addOrUpdate(mMovie);


                Snackbar.make(view, "Movie Added to Database", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDirectorRecyclerView() {
        mDirectorRecycler.setHasFixedSize(true);
        mDirectorRecycler.setLayoutManager(new LinearLayoutManager(this));
        mDirectorRecycler.setAdapter(new DirectorsAdapter(mDirectorData,this));
    }


}
