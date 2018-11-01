package com.example.rubylopez.movietest.allmovies;

import com.example.rubylopez.movietest.BasePresenter;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMoviesPresenter extends BasePresenter implements AllMoviesPresenterInterface {

    private AllMoviesViewInterface view;

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
                view.onGetMoviesSucess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

    }
}
