package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
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

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.getGenresAsList());
        genreComboBox.getSelectionModel().select(0);

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
            observableMovies.clear();
            String selectedGenre = (String) genreComboBox.getSelectionModel().getSelectedItem();

            for(Movie movie : allMovies){
                // check if string is contained in title or description
                if(movie.getTitle().toLowerCase(Locale.ROOT).contains(newField.toLowerCase(Locale.ROOT)) ||
                        movie.getDescription().toLowerCase(Locale.ROOT).contains(newField.toLowerCase(Locale.ROOT))){
                    // now check for the genre if one is selected
                    if(movie.getGenre().contains(selectedGenre) || selectedGenre.equals("Filter by Genre")){
                        observableMovies.add(movie);
                    }
                }
            }
        });


        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            String selectedGenre = (String) genreComboBox.getSelectionModel().getSelectedItem();

            observableMovies.addAll(allMovies);
            // remove all movies that don´t fit the genre
            if(!selectedGenre.equals("Filter by Genre")) {
                Movie.filterMoviesByGenre(observableMovies, selectedGenre);
            }
            // remove all movies that don´t contain the searchstring
            if(!searchField.getText().equals("")) {
                Movie.filterMoviesBySearchString(observableMovies, searchField.getText());
            }
        });

    }
}