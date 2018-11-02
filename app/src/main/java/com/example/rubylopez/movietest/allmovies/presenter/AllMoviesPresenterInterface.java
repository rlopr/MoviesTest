package com.example.rubylopez.movietest.allmovies.presenter;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MoviesResults;

public interface AllMoviesPresenterInterface extends BaseViewInterface {

    void getMovies();
    void searchMovie(String query);

    MoviesResults getMovie(int position);

}
