package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Movie implements Comparable<Movie>{
    private String title;
    private String description;
    private String resolution;
    // TODO add more properties here

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar", "A story in 3D"));
        movies.add(new Movie("Equilibrium", "A dark story"));
        movies.add(new Movie("Crown", "More a series than a movie"));

        return movies;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }
}
