package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
    void testSearchField() {
        StringProperty searchField = new SimpleStringProperty();

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

        List<Movie> allMovies = new ArrayList<>();
        allMovies.add(new Movie("Title 1", "Description 1", Collections.singletonList("GENRE")));
        allMovies.add(new Movie("Title 2", "Description 2", Collections.singletonList("GENRE")));
        allMovies.add(new Movie("Title 3", "Description 3", Collections.singletonList("GENRE")));

        // Add a listener to the search field
        searchField.addListener((observable, oldField, newField) -> {
            observableMovies.clear();
            for (Movie movie : allMovies) {
                if (movie.getTitle().toLowerCase(Locale.ROOT).contains(newField.toLowerCase(Locale.ROOT)) ||
                        movie.getDescription().toLowerCase(Locale.ROOT).contains(newField.toLowerCase(Locale.ROOT))) {
                    observableMovies.add(movie);
                }
            }
        });

        // Test searching for a term that matches one movie
        searchField.set("Title 1");
        assertEquals(1, observableMovies.size());
        assertEquals(allMovies.get(0), observableMovies.get(0));

        // Test searching for a term that matches multiple movies
        searchField.set("Description");
        assertEquals(3, observableMovies.size());
        assertEquals(allMovies.get(0), observableMovies.get(0));
        assertEquals(allMovies.get(1), observableMovies.get(1));
        assertEquals(allMovies.get(2), observableMovies.get(2));

        // Test searching for a term that doesn't match any movies
        searchField.set("KillMePls");
        assertEquals(0, observableMovies.size());
    }

    @Test
    public void testSearchByGenre() {
        searchField = new SimpleStringProperty();
        observableMovies = FXCollections.observableArrayList();
        allMovies = new ArrayList<>();
        allMovies.add(new Movie("Title 1", "Description 1", Collections.singletonList("DRAMA")));
        allMovies.add(new Movie("Title 2", "Description 2", Collections.singletonList("COMEDY")));
        allMovies.add(new Movie("Title 3", "Description 3", Collections.singletonList("WAR")));

        // Set up test data
        observableMovies.addAll(allMovies);

        // Set up event handler
        searchField.addListener((observable, oldField, newField) -> {
            observableMovies.clear();

            String selectedGenre = newField;
            if (!selectedGenre.isEmpty()) {
                for (Movie movie : allMovies) {
                    if (movie.getGenre().contains(selectedGenre)) {
                        observableMovies.add(movie);
                    }
                    if(selectedGenre.equals("Filter by Genre")){
                        observableMovies.setAll(allMovies);
                    }
                }
            } else {
                observableMovies.setAll(allMovies);
            }
        });

        // Test search by genre
        searchField.set("DRAMA");
        assertEquals(1, observableMovies.size());
        assertEquals("Title 1", observableMovies.get(0).getTitle());

        searchField.set("COMEDY");
        assertEquals(1, observableMovies.size());
        assertEquals("Title 2", observableMovies.get(0).getTitle());

        searchField.set("WAR");
        assertEquals(1, observableMovies.size());
        assertEquals("Title 3", observableMovies.get(0).getTitle());

        // Test empty search field
        searchField.set("Filter by Genre");
        assertEquals(3, observableMovies.size());

        // Test non-matching search
        searchField.set("MYSTERY");
        assertEquals(0, observableMovies.size());
    }
}
