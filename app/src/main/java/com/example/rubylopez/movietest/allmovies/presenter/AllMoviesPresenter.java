package com.example.rubylopez.movietest.allmovies.presenter;

import com.example.rubylopez.movietest.BasePresenter;
import com.example.rubylopez.movietest.allmovies.view.AllMoviesViewInterface;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.models.MoviesResponse;
import com.example.rubylopez.movietest.common.models.MoviesResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AllMoviesPresenter extends BasePresenter implements AllMoviesPresenterInterface {

    private AllMoviesViewInterface view;
    private List<MoviesResults> movies;

    public AllMoviesPresenter(BaseViewInterface view, ApiEndpointInterface api) {
        super(view, api);
        this.view = (AllMoviesViewInterface) view;
        this.api = api;
    }

    @Override
    public void getMovies() {
        api.getAllMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                movies = response.body().getResults();
                view.onGetMoviesSucess(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                view.onGetMoviesFailure(t.getMessage());
            }
        });
    }

    @Override
    public MoviesResults getMovie(int position) {
        return movies.get(position);
    }


}
