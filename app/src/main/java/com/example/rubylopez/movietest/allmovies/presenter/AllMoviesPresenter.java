package com.example.rubylopez.movietest.allmovies.presenter;

import com.example.rubylopez.movietest.BasePresenter;
import com.example.rubylopez.movietest.allmovies.view.AllMoviesViewInterface;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.models.MoviesResponse;
import com.example.rubylopez.movietest.common.models.MovieResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AllMoviesPresenter extends BasePresenter implements AllMoviesPresenterInterface {

    private AllMoviesViewInterface view;
    private List<MovieResult> movies;
    private int lastPage = 1;
    private boolean isSearching = false;
    private String query;

    public AllMoviesPresenter(BaseViewInterface view, ApiEndpointInterface api) {
        super(view, api);
        this.view = (AllMoviesViewInterface) view;
        this.api = api;
    }

    @Override
    public void getMovies() {
        view.showLoading();

        if (query != null) {
            searchMovie(query);
        } else {
            api.getAllMovies(lastPage).enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    view.onGetMoviesSucess(movies, lastPage == 1);
                    lastPage++;
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    view.onGetMoviesFailure(t.getMessage());
                }
            });
        }
    }

    @Override
    public void searchMovie(String query) {
        this.query = query;
        lastPage = 1;

        view.showLoading();
        api.getSearchedMovie(lastPage, query).enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies = response.body().getResults();
                view.onGetMoviesSucess(movies, true);
                lastPage++;
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                view.onGetMoviesFailure(t.getMessage());
            }
        });
    }

    @Override
    public MovieResult getMovie(int position) {
        return movies.get(position);
    }

    public void stopSearching() {
        query = null;
        lastPage = 1;
    }
}
