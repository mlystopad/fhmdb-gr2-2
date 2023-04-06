package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.MovieAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Movie implements Comparable<Movie>{
    protected final String id;
    protected final String title;
    protected final String description;
    protected final List<String> genres;
    protected final int releaseYear;
    protected final String imgUrl;
    protected final int lengthInMinutes;
    protected final List<String> writers;
    protected final List<String> mainCast;
    protected final double rating;
    private int year;
    private String director;

    // TODO add more properties here

    public Movie(String id, String title, String description, List<String> genres, int releaseYear, String imgUrl, int year, int lengthInMinutes, String director, ArrayList<String> writers, ArrayList<String> mainCast, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
        this.director = director;
        this.year = year;
    }

    public Movie() {
        this.id = null;
        this.title = null;
        this.description = null;
        this.genres = null;
        this.releaseYear = 0;
        this.imgUrl = null;
        this.lengthInMinutes = 0;
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

    public List<String> getGenres() {
        return genres;
    }

    public int getReleaseYear() { return releaseYear; }

    public int getYear() {return year;}

    public List<String> getMainCast() { return mainCast; }

    public String getDirector() { return director; }




    public static List<Movie> initializeMovies(){
        String url = "https://prog2.fh-campuswien.ac.at/movies";
        MovieAPI movieAPI = new MovieAPI();
        List<Movie> movies = Stream.of(movieAPI.getApiMovies(url))
                .collect(Collectors.toList());
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
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(genres, movie.genres) && Objects.equals(releaseYear, movie.releaseYear);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
