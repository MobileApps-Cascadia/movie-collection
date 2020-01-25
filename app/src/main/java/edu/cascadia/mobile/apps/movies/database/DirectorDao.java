package edu.cascadia.mobile.apps.movies.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.cascadia.mobile.apps.movies.model.DirectorEntity;

public interface DirectorDao {

    //Create (and Update)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addOrUpdate(DirectorEntity director);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAll(List<DirectorEntity> directors);

    //Delete
    @Delete
    void remove(DirectorEntity director);
    @Delete
    void removeAll(List<DirectorEntity> directors);

    //Read
    @Query("select * from director where id = :id")
    DirectorEntity getDirector(int id);
    @Query("select * from director order by last_name asc")
    List<DirectorEntity> getDirectors();

    //Count
    @Query("select COUNT(*) from director")
    int getCount();

}
