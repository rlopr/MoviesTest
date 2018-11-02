package com.example.rubylopez.movietest.moviedetail.view;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MoviesResults;

import java.util.List;

public interface MovieDetailViewInterface extends BaseViewInterface {

    void onGetMovieSucess(MoviesResults result);

    void onGetMovieFailure(String error);

}
