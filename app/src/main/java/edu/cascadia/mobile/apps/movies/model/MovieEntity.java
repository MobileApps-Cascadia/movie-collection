package edu.cascadia.mobile.apps.movies.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "movie",
        indices = @Index("director_id"),
        foreignKeys = @ForeignKey(entity = DirectorEntity.class,
                parentColumns = "id",
                childColumns = "director_id"))
public class MovieEntity  {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String year;
    @ColumnInfo(name = "run_time")private int runTime;
    private Boolean collection;
    @ColumnInfo(name = "director_id")
    int directorId;

    @Ignore
    public MovieEntity() {
    }

    public MovieEntity(int id, String title, String year, int runTime, Boolean collection, int directorId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.collection = collection;
        this.directorId = directorId;
    }
    @Ignore
    public MovieEntity(String title, String year, int runTime, Boolean collection, int directorId) {
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.collection = collection;
        this.directorId = directorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", runTime=" + runTime +
                ", collection=" + collection +
                ", directorId=" + directorId +
                '}';
    }
}
