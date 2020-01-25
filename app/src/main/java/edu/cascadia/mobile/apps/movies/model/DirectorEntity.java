package edu.cascadia.mobile.apps.movies.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "director", indices = @Index(value = {"director_id"}, unique = true))
public class DirectorEntity  {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String first;
    private String last;
    @ColumnInfo(name = "director_id")
    private String director;


    @Ignore
    public DirectorEntity() {
    }

    public DirectorEntity(int id, String first, String last) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.director = director;
    }
    @Ignore
    public DirectorEntity(String first,  String last) {
        this.first = first;
        this.last = last;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "DirectorEntity{" +
                "id=" + id +
                ", first name='" + first + '\'' +
                ", last name='" + last + '\'' +
                ", director=" + director +
                '}';
    }
}