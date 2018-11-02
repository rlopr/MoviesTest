package com.example.rubylopez.movietest.common.datasources.interfaces;

import com.example.rubylopez.movietest.common.models.MovieResultDetailed;
import com.example.rubylopez.movietest.common.models.MoviesResponse;
import com.example.rubylopez.movietest.common.models.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.rubylopez.movietest.common.utilities.Constants.APIConstants.ALL_MOVIES;
import static com.example.rubylopez.movietest.common.utilities.Constants.APIConstants.MOVIE_DETAIL;
import static com.example.rubylopez.movietest.common.utilities.Constants.APIConstants.SEARCH_MOVIE;

public interface ApiEndpointInterface {

    @GET(ALL_MOVIES)
    Call<MoviesResponse> getAllMovies();

    @GET(SEARCH_MOVIE)
    Call<MoviesResponse> getSearchedMovie(
            @Query("query") String search);

    @GET(MOVIE_DETAIL)
    Call<MovieResultDetailed> getMovieDetail(
            @Path("movie_id") String id);
}
