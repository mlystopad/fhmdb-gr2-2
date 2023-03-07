package at.ac.fhcampuswien.fhmdb.models;

import java.util.*;

public class Movie implements Comparable<Movie>{
    private final String title;
    private final String description;
    private final List<String> genre;
    // TODO add more properties here


    public Movie(String title, String description, List<String> genre) {
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

    public List<String> getGenre() {
        return genre;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                Arrays.asList("ACTION", "ADVENTURE", "SCIENCE FICTION", "FANTASY", "MYSTERY")));
        movies.add(new Movie("Equilibrium",
                "In an oppressive future where all forms of feeling are illegal, a man in charge of enforcing the law rises to overthrow the system and state.",
                Arrays.asList("ACTION", "DRAMA", "THRILLER", "SCIENCE FICTION")));
        movies.add(new Movie("Megamind",
                "Evil genius Megamind finally defeats his do-gooder nemesis, Metro Man, but is left without a purpose in a superhero-free world.",
                Arrays.asList("ACTION", "COMEDY", "SCIENCE FICTION", "ANIMATION", "FAMILY")));
        movies.add(new Movie("The Dark Knight",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                Arrays.asList("ADVENTURE", "ACTION", "THRILLER", "CRIME", "DRAMA", "MYSTERY")));
        movies.add(new Movie("Pulp Fiction",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                Arrays.asList("COMEDY", "CRIME", "THRILLER", "CRIME")));
        movies.add(new Movie("Spirited Away",
                "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.",
                Arrays.asList("ANIMATION", "ADVENTURE", "FANTASY", "MYSTERY")));
        movies.add(new Movie("Inglourious Basterds",
                "In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner's vengeful plans for the same.",
                Arrays.asList("WAR", "ACTION", "COMEDY", "DRAMA", "ADVENTURE")));
        movies.add(new Movie("Good Will Hunting",
                "Will Hunting, a janitor at M.I.T., has a gift for mathematics, but needs help from a psychologist to find direction in his life.",
                Arrays.asList("DRAMA", "ROMANCE")));
        movies.add(new Movie("12 Years a Slave",
                "In the antebellum United States, Solomon Northup, a free black man from upstate New York, is abducted and sold into slavery.",
                Arrays.asList("DRAMA", "HISTORY")));
        movies.add(new Movie("Forrest Gump",
                "Forrest, a man with low IQ, recounts the early years of his life when he found himself in the middle of key historical events. All he wants now is to be reunited with his childhood sweetheart, Jenny.",
                Arrays.asList("DRAMA", "ROMANCE", "COMEDY")));
        movies.add(new Movie("The Shining",
                "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.",
                Arrays.asList("MYSTERY", "HORROR")));
        movies.add(new Movie("Moulin Rouge",
                "A poor Bohemian poet in 1890s Paris falls for a beautiful courtesan and nightclub star coveted by a jealous duke.",
                Arrays.asList("MUSICAL", "DRAMA", "COMEDY", "ROMANCE")));
        movies.add(new Movie("Green Book",
                "A working-class Italian-American bouncer becomes the driver for an African-American classical pianist on a tour of venues through the 1960s American South.",
                Arrays.asList("BIOGRAPHY", "COMEDY", "DRAMA")));
        movies.add(new Movie("Girl in the Picture",
                "A young mother's mysterious death and her son's subsequent kidnapping open a decades-long mystery.",
                Collections.singletonList("DOCUMENTARY")));
        movies.add(new Movie("Once Upon a Time in the West",
                "A mysterious stranger with a harmonica joins forces with a notorious desperado to protect a beautiful widow from a ruthless assassin working for the railroad.",
                Arrays.asList("WESTERN", "ACTION", "ADVENTURE", "DRAMA")));
        movies.add(new Movie("Hustle",
                "A basketball scout discovers a phenomenal street ball player while in Spain and sees the prospect as his opportunity to get back into the NBA.",
                Arrays.asList("SPORT", "DRAMA")));

        return movies;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
