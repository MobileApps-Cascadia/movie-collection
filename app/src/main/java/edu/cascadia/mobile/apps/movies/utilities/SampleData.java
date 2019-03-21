package edu.cascadia.mobile.apps.movies.utilities;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobile.apps.movies.model.MovieEntity;

public class SampleData {

    public static List<MovieEntity> getMovies (){
        List<MovieEntity> moviesList = new ArrayList<MovieEntity>();

        moviesList.add(new MovieEntity("My Neighbor Totoro", "Misyazaki, Hiayao", "1988", 86) );
        moviesList.add(new MovieEntity("Spirited Away", "Misyazaki, Hiayao", "2001", 125) );
        moviesList.add(new MovieEntity("The Fellowship of the Rings", "Jackson, Peter", "2001", 228) );

        return moviesList;
    }

}
