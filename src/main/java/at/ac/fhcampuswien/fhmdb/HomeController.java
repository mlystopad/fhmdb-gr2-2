package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXComboBox releaseYearComboBox;

    @FXML
    public JFXComboBox ratingFromComboBox;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    public void fillMovies(){
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    public HomeController() {
        this.fillMovies();
    }

    public List<Integer> getReleaseYearsAsList() {
        List<Integer> releaseYears = new ArrayList<>();
        for (Movie movie : allMovies) {
            int year = movie.getReleaseYear();
            if (!releaseYears.contains(year)) {
                releaseYears.add(year);
            }
        }
        Collections.sort(releaseYears);
        return releaseYears;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add movies to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.getItems().add("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.getGenresAsList());
        genreComboBox.getSelectionModel().select(0);


        releaseYearComboBox.getItems().add("Filter by Release Year");
        releaseYearComboBox.getItems().addAll(getReleaseYearsAsList());
        releaseYearComboBox.getSelectionModel().select(0);

        ratingFromComboBox.getItems().add("Filter by Rating");
        ratingFromComboBox.getItems().addAll(/*Movie.getRatingsAsList()*/);
        ratingFromComboBox.getSelectionModel().select(0);


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                observableMovies.sort(null);
                sortBtn.setText("Sort (desc)");
            } else {
                observableMovies.sort(Comparator.reverseOrder());
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
            }
        });

        searchField.textProperty().addListener((observable, oldField, newField) -> {
            fillMovies();
            String selectedGenre = (String) genreComboBox.getSelectionModel().getSelectedItem();

            filterMoviesByGenre(selectedGenre);
            filterMoviesBySearchString(newField);
        });

        genreComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                fillMovies();

                filterMoviesByGenre(newValue);
                filterMoviesBySearchString(searchField.getText());
            }
        });

        releaseYearComboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                fillMovies();
                filterMoviesByGenre((String) genreComboBox.getSelectionModel().getSelectedItem());
                filterMoviesBySearchString(searchField.getText());
                filterMoviesByReleaseYear(newValue);
            }
        });

    }

    public void filterMoviesByGenre(String genre){
        if(genre == null){
            System.err.println("Genre must not be null!");
            return;
        }

        if(!Genre.getGenresAsList().contains(genre)){
            return;
        }

        Iterator<Movie> iterator = observableMovies.iterator();
        while (iterator.hasNext()){
            if(!iterator.next().getGenres().contains(genre)){
                iterator.remove();
            }
        }
    }

    public void filterMoviesBySearchString(String toSearch){
        if(toSearch == null){
            System.err.println("Searchstring must not be null!");
            return;
        }else if(toSearch.equals("")){
            return;
        }

        Iterator<Movie> iterator = observableMovies.iterator();
        while (iterator.hasNext()){
            Movie next = iterator.next();
            if(!next.getTitle().toLowerCase().contains(toSearch.toLowerCase()) &&
                    !next.getDescription().toLowerCase().contains(toSearch.toLowerCase())){
                iterator.remove();
            }
        }
    }

    public void filterMoviesByReleaseYear(Integer releaseYear){
        if(releaseYear == null){
            System.err.println("Release year must not be null!");
            return;
        }

        Iterator<Movie> iterator = observableMovies.iterator();
        while (iterator.hasNext()){
            Movie movie = iterator.next();
            Integer movieReleaseYear = movie.getReleaseYear();
            if (!movieReleaseYear.equals(releaseYear)) {
                iterator.remove();
            }
        }
    }

    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .map(Movie::getTitle)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirector().equals(director))
                .count();
    }

    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
                .collect(Collectors.toList());
    }

}