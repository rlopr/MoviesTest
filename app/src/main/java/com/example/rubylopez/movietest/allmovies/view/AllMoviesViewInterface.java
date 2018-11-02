package com.example.rubylopez.movietest.allmovies.view;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import java.util.List;

public interface AllMoviesViewInterface extends BaseViewInterface {

    void onGetMoviesSucess(List<MoviesResults> result);

    void onGetMoviesFailure(String error);

}
