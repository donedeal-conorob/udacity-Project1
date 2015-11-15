package conor.obrien.popularmovies.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import conor.obrien.popularmovies.R;
import conor.obrien.popularmovies.fragments.MovieDetailFragment;
import conor.obrien.popularmovies.model.MovieResult;
import conor.obrien.popularmovies.resources.Constants;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailFragment.MovieDetailFragmentCallbacks{

    MovieResult mMovieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras.containsKey(Constants.Keys.MOVIE_RESULT_KEY)) {
            mMovieResult = extras.getParcelable(Constants.Keys.MOVIE_RESULT_KEY);
        }
    }

    @Override
    public MovieResult getMovieResult() {
        return mMovieResult;
    }
}
