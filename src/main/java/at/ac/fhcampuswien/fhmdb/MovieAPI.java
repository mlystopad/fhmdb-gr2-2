package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class MovieAPI {
    /**
     * A method that downloads a list of Movies as JSON and converts it to an array of Movies
     * @param url a URL for a webpage with movies in corresponding format
     * @return Movie[] - an array of Movies parsed from JSON
     */
    public Movie[] getApiMovies(String url) {;
        Movie[] movies;
        try {
            movies = new Gson().fromJson(getRequest(url).string(), Movie[].class);
        } catch (IOException e) {
            movies = null;
            e.printStackTrace();
        }
        return movies;
    }


    /**
     * Implementation of GET method that eats a URL to return a ResponseBody.
     * @param url a URL for a webpage
     * @throws IOException
     * @return ResponseBody from the webpage
     */
    ResponseBody getRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().addHeader("User-Agent", "FHMDB/1.0") // a User-Agent header is added to pass 403 Error
                .url(url)
                .build();

        ResponseBody responseBody = client.newCall(request).execute().body();
        return responseBody;
    }

}
