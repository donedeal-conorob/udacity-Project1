package conor.obrien.popularmovies.resources;

/**
 * Created by Conor O'Brien on 12/11/15.
 */
public class Constants {

    public static class URLs {

        public static final String API_URL = "http://api.themoviedb.org/3";
        public static final String POSTER_URL = "http://image.tmdb.org/t/p/w185/";
    }

    public static class Keys {

        public static final String MOVIE_RESULT_KEY = "conor.obrien.popularmovies.MovieResult";
    }

    public static class SortBy {

        public static final String POPULARITY_DESC = "popularity.desc";
        public static final String VOTE_AVERAGE_DESC = "vote_average.desc";
    }
}
