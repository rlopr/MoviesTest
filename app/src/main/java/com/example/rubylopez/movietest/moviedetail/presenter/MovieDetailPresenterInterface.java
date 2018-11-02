package com.example.rubylopez.movietest.moviedetail.presenter;

import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MoviesResults;

public interface MovieDetailPresenterInterface extends BaseViewInterface {

    void setMovie(MoviesResults movie);

}
