package com.example.rubylopez.movietest.moviedetail.presenter;

import com.example.rubylopez.movietest.BasePresenter;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.models.MovieResult;
import com.example.rubylopez.movietest.common.models.MovieResultDetailed;
import com.example.rubylopez.movietest.moviedetail.view.MovieDetailViewInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailPresenter extends BasePresenter implements MovieDetailPresenterInterface {

    private MovieDetailViewInterface view;
    private MovieResult movie;
    private MovieResultDetailed movieDetails;

    public MovieDetailPresenter(BaseViewInterface view, ApiEndpointInterface api) {
        super(view, api);
        this.view = (MovieDetailViewInterface) view;
        this.api = api;
    }

    @Override
    public void setMovie(MovieResult movie) {
        this.movie = movie;
        view.onGetMovieSucess(this.movie);
        getMovieDetails();
    }

    @Override
    public void getMovieDetails() {
        view.showLoading();
        api.getMovieDetail(String.valueOf(movie.getId())).enqueue(new Callback<MovieResultDetailed>() {
            @Override
            public void onResponse(Call<MovieResultDetailed> call, Response<MovieResultDetailed> response) {
                movieDetails = response.body();
                view.onGetMovieDetailSucess(movieDetails);
            }

            @Override
            public void onFailure(Call<MovieResultDetailed> call, Throwable t) {
                view.onGetMovieFailure(t.getMessage());
            }
        });
    }

}
