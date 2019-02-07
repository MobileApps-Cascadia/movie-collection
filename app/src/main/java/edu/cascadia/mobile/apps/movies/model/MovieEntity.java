package edu.cascadia.mobile.apps.movies.model;

public class MovieEntity  {
    private int id;
    private String title;
    private String year;
    private int runTime;
    private Boolean collection;
    private int directorId;

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
