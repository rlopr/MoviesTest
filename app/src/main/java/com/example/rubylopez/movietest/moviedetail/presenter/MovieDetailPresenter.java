package com.example.rubylopez.movietest.moviedetail.presenter;

import com.example.rubylopez.movietest.BasePresenter;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.models.MoviesResponse;
import com.example.rubylopez.movietest.common.models.MoviesResults;
import com.example.rubylopez.movietest.moviedetail.view.MovieDetailViewInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MovieDetailPresenter extends BasePresenter implements MovieDetailPresenterInterface {

    private MovieDetailViewInterface view;
    private MoviesResults movie;

    public MovieDetailPresenter(BaseViewInterface view, ApiEndpointInterface api) {
        super(view, api);
        this.view = (MovieDetailViewInterface) view;
        this.api = api;
    }

    @Override
    public void setMovie(MoviesResults movie) {
        this.movie = movie;
        view.onGetMovieSucess(this.movie);
    }

}
