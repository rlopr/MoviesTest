package com.example.rubylopez.movietest.allmovies.presenter;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MovieResult;

public interface AllMoviesPresenterInterface extends BaseViewInterface {

    void getMovies();
    void searchMovie(String query);

    MovieResult getMovie(int position);

}
