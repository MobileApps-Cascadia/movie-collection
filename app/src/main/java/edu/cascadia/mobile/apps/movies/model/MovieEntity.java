package edu.cascadia.mobile.apps.movies.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "movie",
indices = @Index(value = {"director_id"}, unique = true), foreignKeys = @ForeignKey(entity = MovieEntity.class, parentColumns = "director_id", childColumns = "director_id"))
public class MovieEntity  {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String year;
    @ColumnInfo(name = "run_time")private int runTime;
    @ColumnInfo(name = "director_id")
    private String director;
    @ForeignKey(entity = MovieEntity.class, parentColumns = "director_id", childColumns = "id")

    @Ignore
    public MovieEntity() {
    }
    public MovieEntity(int id, String title, String director, String year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.director = director;
    }
    @Ignore
    public MovieEntity(String title,  String director, String year,  int runTime) {
        this.title = title;
        this.year = year;
        this.runTime = runTime;
        this.director = director;
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
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", runTime=" + runTime +
                ", director=" + director +
                '}';
    }
}
