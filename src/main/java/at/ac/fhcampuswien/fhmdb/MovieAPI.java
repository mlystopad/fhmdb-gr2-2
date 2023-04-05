package at.ac.fhcampuswien.fhmdb;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class MovieAPI {

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
     * Implementation of GET method that eats a URL to return a body of Response.
     * @param url a URL for a webpage
     * @throws IOException
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
