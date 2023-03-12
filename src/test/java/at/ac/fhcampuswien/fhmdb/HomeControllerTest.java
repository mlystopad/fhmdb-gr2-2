package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class HomeControllerTest {
    private StringProperty searchField;
    private ObservableList<Movie> observableMovies;
    private List<Movie> allMovies;

    @Test
    void ascending_sorting() {
        // given
        ArrayList<Movie> movies = new ArrayList<>();
        Movie a = new Movie("Bonga Banga", "3", Collections.singletonList("GENRE"));
        Movie b = new Movie("Aonga Banga", "2", Collections.singletonList("GENRE"));
        Movie c = new Movie("Konga Banga", "4", Collections.singletonList("GENRE"));
        Movie d = new Movie("Zonga Banga", "5", Collections.singletonList("GENRE"));
        Movie e = new Movie("Aenga Banga", "1", Collections.singletonList("GENRE"));
        movies.add(a);
        movies.add(b);
        movies.add(c);
        movies.add(d);
        movies.add(e);
        movies.sort(null);
        // when
        String actual = movies.toString();
        // then
        ArrayList<Movie> temp = new ArrayList<>();
        temp.add(e);
        temp.add(b);
        temp.add(a);
        temp.add(c);
        temp.add(d);
        String expected = temp.toString();
        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test
    void descending_sorting() {
        // given
        ArrayList<Movie> movies = new ArrayList<>();
        Movie a = new Movie("Bonga Banga", "3", Collections.singletonList("GENRE"));
        Movie b = new Movie("Aonga Banga", "2", Collections.singletonList("GENRE"));
        Movie c = new Movie("Konga Banga", "4", Collections.singletonList("GENRE"));
        Movie d = new Movie("Zonga Banga", "5", Collections.singletonList("GENRE"));
        Movie e = new Movie("Aenga Banga", "1", Collections.singletonList("GENRE"));
        movies.add(a);
        movies.add(b);
        movies.add(c);
        movies.add(d);
        movies.add(e);
        movies.sort(null);
        Collections.reverse(movies);
        // when
        String actual = movies.toString();
        // then
        ArrayList<Movie> temp = new ArrayList<>();
        temp.add(d);
        temp.add(c);
        temp.add(a);
        temp.add(b);
        temp.add(e);
        String expected = temp.toString();
        assertArrayEquals(expected.toCharArray(), actual.toCharArray());
    }

    @Test
    void filter_by_genre_null_throws_NullPointerException() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>(movies);

        //when
        //then
        assertThrows(NullPointerException.class,
                () -> Movie.filterMoviesByGenre(movies, null));

    }

    @Test
    void filter_by_nonexistent_genre() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>(movies);

        //when
        Movie.filterMoviesByGenre(movies, "Puppenspielfilm");

        //then
        assertArrayEquals(expected.toArray(), movies.toArray());

    }



    @Test
    void filter_by_search_string_null_throws_NullPointerException() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>(movies);

        //when
        //then
        assertThrows(NullPointerException.class,
                () -> Movie.filterMoviesBySearchString(movies, null));

    }

    @Test
    void filter_by_genre_drama() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movies.get(0));
        expected.add(movies.get(3));
        expected.add(movies.get(5));
        expected.add(movies.get(6));
        expected.add(movies.get(14));

        //when
        Movie.filterMoviesByGenre(movies, Genre.ADVENTURE);

        //then
        assertArrayEquals(expected.toArray(), movies.toArray());

    }

    @Test
    public void filter_by_search_string_working() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movies.get(12));
        expected.add(movies.get(14));

        //when
        Movie.filterMoviesBySearchString(movies, "WORKING");

        //then
        assertArrayEquals(expected.toArray(), movies.toArray());
    }

    @Test
    public void filter_by_genre_drama_and_search_string_working() {
        // given
        List<Movie> movies = Movie.initializeMovies();
        ArrayList<Movie> expected = new ArrayList<>();
        expected.add(movies.get(14));

        //when
        Movie.filterMoviesBySearchString(movies, "WORKING");
        Movie.filterMoviesByGenre(movies, Genre.WESTERN);

        //then
        assertArrayEquals(expected.toArray(), movies.toArray());
    }

}
