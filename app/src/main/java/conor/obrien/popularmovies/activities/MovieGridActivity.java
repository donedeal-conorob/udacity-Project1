package conor.obrien.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import conor.obrien.popularmovies.R;
import conor.obrien.popularmovies.api.DiscoverMovieInterface;
import conor.obrien.popularmovies.fragments.MovieGridFragment;
import conor.obrien.popularmovies.model.MovieList;
import conor.obrien.popularmovies.model.MovieResult;
import conor.obrien.popularmovies.resources.Api;
import conor.obrien.popularmovies.resources.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieGridActivity extends AppCompatActivity implements MovieGridFragment.MovieGridFragmentCallbacks {

    static final String EXTRA_HAS_MOVIES = "hasMovies";
    static final String EXTRA_MOVIE_RESULTS = "movieResults";

    MovieGridFragment mMovieGridFragment;
    RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Constants.URLs.API_URL).build();
    DiscoverMovieInterface movieApi = restAdapter.create(DiscoverMovieInterface.class);

    List<MovieResult> mMovieResults = new ArrayList<>();
    Boolean mHasMovies = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGridFragment = (MovieGridFragment) getSupportFragmentManager().findFragmentById(R.id.movie_grid_fragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(EXTRA_HAS_MOVIES, mHasMovies);
        if (mMovieResults != null) {
            outState.putParcelableArrayList(EXTRA_MOVIE_RESULTS, (ArrayList<? extends Parcelable>) mMovieResults);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mHasMovies = savedInstanceState.getBoolean(EXTRA_HAS_MOVIES);

        if (savedInstanceState.containsKey(EXTRA_MOVIE_RESULTS)) {
            mMovieResults = savedInstanceState.getParcelableArrayList(EXTRA_MOVIE_RESULTS);
            mMovieGridFragment.populateMovieGrid(mMovieResults);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_by_highest_rated) {
            getMovies(Constants.SortBy.VOTE_AVERAGE_DESC);
        } else  if (id == R.id.action_sort_by_most_popular) {
            getMovies(Constants.SortBy.POPULARITY_DESC);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startMovieDetailsActivity(MovieResult movieResult) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.Keys.MOVIE_RESULT_KEY, movieResult);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void getMovies(String sortby) {
        movieApi.getMovies(sortby, Api.Keys.API_KEY, new Callback<MovieList>() {
            @Override
            public void success(MovieList movieList, Response response) {
                mMovieResults = movieList.getResults();
                mMovieGridFragment.populateMovieGrid(mMovieResults);
                mHasMovies = true;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(this.getClass().getSimpleName(), error.toString());
            }
        });
    }

    @Override
    public Boolean getHasMovies() {
        return mHasMovies;
    }
}
