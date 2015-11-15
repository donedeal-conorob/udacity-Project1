package conor.obrien.popularmovies.api;

import conor.obrien.popularmovies.model.MovieList;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Conor O'Brien on 12/11/15.
 */
public interface DiscoverMovieInterface {
    @GET("/discover/movie")
    public void getMovies(@Query("sort_by") String sort, @Query("api_key") String apiKey, Callback<MovieList> movieListCallback);
}
