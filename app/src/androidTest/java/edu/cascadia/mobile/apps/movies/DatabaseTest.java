package edu.cascadia.mobile.apps.movies;



import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.cascadia.mobile.apps.movies.database.MovieDao;
import edu.cascadia.mobile.apps.movies.database.movieDatabase;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;
import edu.cascadia.mobile.apps.movies.utilities.SampleData;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "Junit";
    private movieDatabase mDb;
    private MovieDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, movieDatabase.class).build();
        mDao = mDb.movieDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb(){
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveMovies(){
        mDao.addAll(SampleData.getMovies());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes: count=" + count);
        assertEquals(SampleData.getMovies().size(), count);

    }


    @Test
    public void compareStrings() {
        mDao.addAll(SampleData.getMovies());
        MovieEntity Original = SampleData.getMovies().get(0);
        MovieEntity fromDb = mDao.getMovie(1);
        assertEquals(Original.getTitle(), fromDb.getTitle());
        assertEquals(1, fromDb.getId());
    }

}
