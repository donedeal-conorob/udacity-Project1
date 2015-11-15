package conor.obrien.popularmovies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import conor.obrien.popularmovies.R;
import conor.obrien.popularmovies.adapters.MovieGridAdapter;
import conor.obrien.popularmovies.api.DiscoverMovieInterface;
import conor.obrien.popularmovies.fragments.MovieGridFragment;
import conor.obrien.popularmovies.model.MovieList;
import conor.obrien.popularmovies.model.MovieResult;
import conor.obrien.popularmovies.resources.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieGridActivity extends AppCompatActivity implements MovieGridFragment.MovieGridFragmentCallbacks {

    MovieGridFragment mMovieGridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);

        mMovieGridFragment = (MovieGridFragment) getSupportFragmentManager().findFragmentById(R.id.movie_grid_fragment);
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
            mMovieGridFragment.getMovies(Constants.SortBy.VOTE_AVERAGE_DESC);
        } else  if (id == R.id.action_sort_by_most_popular) {
            mMovieGridFragment.getMovies(Constants.SortBy.POPULARITY_DESC);
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
}
