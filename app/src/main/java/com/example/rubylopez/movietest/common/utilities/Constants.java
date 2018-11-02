package com.example.rubylopez.movietest.common.utilities;

import com.example.rubylopez.movietest.BuildConfig;

/**
 * Created by hhorta on 17-02-17.
 */

public final class Constants {

    public static final int API_CONNECTION_TIMEOUT = 120;

    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public final class APIConstants {

        private APIConstants() {
        }

        public static final String ALL_MOVIES = "movie/upcoming?page=1";
        public static final String SEARCH_MOVIE = "search/movie?"; //query={SEARCH}
        public static final String MOVIE_DETAIL = "movie/{movie_id}";
        public static final String IMAGES = "https://image.tmdb.org/t/p/";
        public static final String IMAGES_SMALL = IMAGES + "w200";
        public static final String IMAGES_MEDIUM = IMAGES + "w400";
    }


}
