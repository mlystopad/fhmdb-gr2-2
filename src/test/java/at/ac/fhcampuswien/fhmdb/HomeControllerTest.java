package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class HomeControllerTest {
    @Test
    void test_ascending_sorting() {
        /*
         * Dont look here yet, Im learning (¬,‿,¬)
         */

        // given
        ArrayList<Movie> movies = new ArrayList<>();
        Movie a = new Movie("Bonga Banga", "3");
        Movie b = new Movie("Aonga Banga", "2");
        Movie c = new Movie("Konga Banga", "4");
        Movie d = new Movie("Zonga Banga", "5");
        Movie e = new Movie("Aenga Banga", "1");
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
  
}