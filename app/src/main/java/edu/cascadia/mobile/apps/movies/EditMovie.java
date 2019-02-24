package edu.cascadia.mobile.apps.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.viewmodel.EditViewModel;

import static edu.cascadia.mobile.apps.movies.utilities.Constants.EDITING_KEY;
import static edu.cascadia.mobile.apps.movies.utilities.Constants.MOVIE_ID_KEY;


public class EditMovie extends AppCompatActivity {

    private EditViewModel mViewModel;
    private boolean mNewMovie, mEditing;

    private EditText eTitle;
    private EditText eDirector;
    private EditText eYear;
    private EditText eRuntime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eTitle = findViewById(R.id.edit_title);
        eDirector = findViewById(R.id.edit_director);
        eYear = findViewById(R.id.edit_year);
        eRuntime = findViewById(R.id.edit_runtime);

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initViewModel();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveOrUpdate();

            }
        });
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EditViewModel.class);
        mViewModel.mLiveMovie.observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(@Nullable MovieEntity movieEntity) {
                if (movieEntity != null && !mEditing) {
                    eTitle.setText(movieEntity.getTitle());
                    eDirector.setText(movieEntity.getDirector());
                    eYear.setText(movieEntity.getYear());
                    eRuntime.setText(Integer.toString(movieEntity.getRunTime()));
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(R.string.new_movie);
            mNewMovie = true;
        } else {
            setTitle(R.string.edit_movie);
            int movieId = extras.getInt(MOVIE_ID_KEY);
            mViewModel.loadData(movieId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewMovie) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_edit, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveOrUpdate();
            return true;
        }
        else if (item.getItemId() == R.id.action_delete) {
            mViewModel.deleteMovie();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveOrUpdate() {

        if (TextUtils.isEmpty(eTitle.getText().toString().trim())) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }
        mViewModel.saveOrUpdateMovie(
                eTitle.getText().toString(),
                eDirector.getText().toString(),
                eYear.getText().toString(),
                Integer.parseInt(eRuntime.getText().toString())
        );

        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}
