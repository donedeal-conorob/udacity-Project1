package conor.obrien.popularmovies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import conor.obrien.popularmovies.R;
import conor.obrien.popularmovies.model.MovieResult;
import conor.obrien.popularmovies.resources.Constants;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment {

    MovieResult mMovieResult;
    MovieDetailFragmentCallbacks mMovieDetailFragmentCallbacks;
    TextView mMovieTitleTextView, mMovieSynopsisTextView, mMovieUserRatingTextView, mMovieReleaseDateTextView;
    ImageView mMoviePosterImageView;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        mMovieDetailFragmentCallbacks = (MovieDetailFragmentCallbacks) getActivity();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() == null) {
            return;
        }

        mMovieTitleTextView = (TextView) getView().findViewById(R.id.textview_movie_title);
        mMoviePosterImageView = (ImageView) getView().findViewById(R.id.imageview_movie_poster);
        mMovieSynopsisTextView = (TextView) getView().findViewById(R.id.textview_movie_synopsis);
        mMovieUserRatingTextView = (TextView) getView().findViewById(R.id.textview_user_rating);
        mMovieReleaseDateTextView = (TextView) getView().findViewById(R.id.textview_release_date);

        mMovieResult = mMovieDetailFragmentCallbacks.getMovieResult();

        mMovieTitleTextView.setText(mMovieResult.getTitle());
        mMovieSynopsisTextView.setText(mMovieResult.getOverview());
        mMovieUserRatingTextView.setText(getString(R.string.movie_details_rating) + " " + String.valueOf(mMovieResult.getVoteAverage()));
        mMovieReleaseDateTextView.setText(getString(R.string.movie_details_release_date) + " " + mMovieResult.getReleaseDate());
        Picasso.with(getActivity()).load(Constants.URLs.POSTER_URL_LARGE + mMovieResult.getPosterPath()).into(mMoviePosterImageView);
    }

    public interface MovieDetailFragmentCallbacks {
        MovieResult getMovieResult();
    }
}
