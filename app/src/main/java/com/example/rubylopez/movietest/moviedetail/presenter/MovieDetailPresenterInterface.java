package com.example.rubylopez.movietest.moviedetail.presenter;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MovieResult;

public interface MovieDetailPresenterInterface extends BaseViewInterface {

    void setMovie(MovieResult movie);

    void getMovieDetails();

}
