package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.ArrayList;

public class Test {
    /*
     * Have you ever wondered what happens if you convert ArrayList to String? I sure as hell did!
     */
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar", "A story in 3D", ""));
        movies.add(new Movie("Equilibrium", "A dark story", ""));
        movies.add(new Movie("Crown", "More a series than a movie", ""));

        System.out.println(movies.toString());
    }
}
