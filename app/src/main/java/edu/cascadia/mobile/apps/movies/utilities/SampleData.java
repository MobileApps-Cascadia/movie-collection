package edu.cascadia.mobile.apps.movies.utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobile.apps.movies.model.DirectorEntity;
import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class SampleData {

    public static List<MovieEntity> getMovies (){
        List<MovieEntity> moviesList = new ArrayList<MovieEntity>();

        moviesList.add(new MovieEntity(1,"My Neighbor Totoro", "1988", 86, Boolean.FALSE, 1) );
        moviesList.add(new MovieEntity(2,"Spirited Away", "2001", 125, Boolean.FALSE, 1) );

        return moviesList;
    }

    public static List<DirectorEntity> getDirectors (){
        List<DirectorEntity> directorList = new ArrayList<DirectorEntity>();

        directorList.add(new DirectorEntity(1,"Hiyao", "Miyazaki") );

        return directorList;
    }
}
