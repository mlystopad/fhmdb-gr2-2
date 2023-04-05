package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.MovieAPI;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Stream;

public class Movie implements Comparable<Movie>{
    private final String iD;
    private final String title;
    private final String description;
    private final List<String> genre;
    private final int releaseYear;
    private final String imgURL;
    private final int lengthInMin;
    private final ArrayList<String> directors;
    private final ArrayList<String> writers;
    private final ArrayList<String> mainCast;
    private final double rating;
    // TODO add more properties here

    public Movie(String iD,String title, String description, List<String> genre, int releaseYear, String imgURL, int lengthInMin, ArrayList<String> directors, ArrayList<String> writers, ArrayList<String> mainCast, double rating) {
        this.iD = iD;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.imgURL = imgURL;
        this.lengthInMin = lengthInMin;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    public Movie() {
        this.iD = null;
        this.title = null;
        this.description = null;
        this.genre = null;
        this.releaseYear = 0;
        this.imgURL = null;
        this.lengthInMin = 0;
        this.directors = null;
        this.writers = null;
        this.mainCast = null;
        this.rating = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenre() {
        return genre;
    }



    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        MovieAPI movieAPI = new MovieAPI();




        return movies;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genre, movie.genre);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
