package com.example.rubylopez.movietest.allmovies.view;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MovieResult;

import java.util.List;

public interface AllMoviesViewInterface extends BaseViewInterface {

    void showLoading();

    void onGetMoviesSucess(List<MovieResult> result, boolean reset);

    void onGetMoviesFailure(String error);

}
