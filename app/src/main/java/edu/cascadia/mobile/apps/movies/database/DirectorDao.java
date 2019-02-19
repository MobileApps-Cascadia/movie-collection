package edu.cascadia.mobile.apps.movies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.cascadia.mobile.apps.movies.model.DirectorEntity;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

@Dao
public interface DirectorDao {

    // Create (and Update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addOrUpdate(DirectorEntity director);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<DirectorEntity> directors);

    //Read
    @Query("select * from director where id = :id")
    LiveData<DirectorEntity> getDirector(int id);
    @Query("select * from movie where director_id = :director_id")
    LiveData<List<MovieEntity>> getMovies(int director_id);
    @Query("select * from director")
    List<DirectorEntity> getDirectors();
    //Delete
    @Delete
    void remove(DirectorEntity directorEntity);
    @Delete
    void removeAll(List<DirectorEntity> directors);

    //Count
    @Query("select COUNT(*) from director")
    int getCount();

}
