package com.example.rubylopez.movietest.moviedetail.view;

import android.support.annotation.NonNull;
import com.example.rubylopez.movietest.common.BaseViewInterface;
import com.example.rubylopez.movietest.common.models.MovieResult;
import com.example.rubylopez.movietest.common.models.MovieResultDetailed;

public interface MovieDetailViewInterface extends BaseViewInterface {

    void onGetMovieSucess(MovieResult result);

    void onGetMovieDetailSucess(MovieResultDetailed result);

    void onGetMovieFailure(String error);

    void showLoading();

}
