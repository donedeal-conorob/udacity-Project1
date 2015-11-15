package conor.obrien.popularmovies.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import conor.obrien.popularmovies.R;
import conor.obrien.popularmovies.adapters.MovieGridAdapter;
import conor.obrien.popularmovies.api.DiscoverMovieInterface;
import conor.obrien.popularmovies.model.MovieList;
import conor.obrien.popularmovies.model.MovieResult;
import conor.obrien.popularmovies.resources.Api;
import conor.obrien.popularmovies.resources.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGridFragment extends Fragment implements MovieGridAdapter.MovieGridAdapterCallbacks {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MovieGridAdapter mAdapter;
    MovieGridFragmentCallbacks mMovieGridFragmentCallbacks;
    RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(Constants.URLs.API_URL).build();
    DiscoverMovieInterface movieApi = restAdapter.create(DiscoverMovieInterface.class);

    public MovieGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_grid, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new MovieGridAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        mMovieGridFragmentCallbacks = (MovieGridFragmentCallbacks) getActivity();

        mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getMovies(Constants.SortBy.POPULARITY_DESC);

        return view;
    }

    public void getMovies(String sortby) {
        movieApi.getMovies(sortby, Api.Keys.API_KEY, new Callback<MovieList>() {
            @Override
            public void success(MovieList movieList, Response response) {
                mAdapter.setMovieList(movieList.getResults());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(this.getClass().getSimpleName(), error.toString());
            }
        });
    }

    @Override
    public void startMovieDetailsActivity(MovieResult movieResult) {
        mMovieGridFragmentCallbacks.startMovieDetailsActivity(movieResult);
    }

    public interface MovieGridFragmentCallbacks {
        public void startMovieDetailsActivity (MovieResult movieResult);
    }
}
