package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Movie implements Comparable<Movie>{
    private String title;
    private String description;
    private String resolution;
    private String genre;
    // TODO add more properties here

    public Movie(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar", "A story in 3D", "Adventure, Drama, Science Fiction"));
        movies.add(new Movie("Equilibrium", "A dark story", "Adventure, Drama"));
        movies.add(new Movie("Crown", "More a series than a movie", "Documentary"));
        movies.add(new Movie("The Dark Knight", "Billionaire in a bat suit", "Action, Adventure"));
        movies.add(new Movie("Pulp Fiction", "Do you know what they call a BigMac in France?", "Crime, Drama"));
        movies.add(new Movie("Spirited Away", "どういたしまして。", "Adventure, Fantasy"));
        movies.add(new Movie("Good Will Hunting", "Every mathematician be like", "Drama, Romance"));
        movies.add(new Movie("12 Years a Slave", "This one is actually good", "Drama"));
        movies.add(new Movie("Forrest Gump", "You already know what Ill say - run", "Drama, Romance"));

        return movies;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }
}
