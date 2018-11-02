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

        public static final String LANGUAGE = "en-US";
        public static final String DEFAULT_PARAMS = "";//"&api_key=" + BuildConfig.CLIENT_ID + "&language=" + LANGUAGE;

        public static final String ALL_MOVIES = "movie/upcoming?page=1"+ DEFAULT_PARAMS;
        public static final String SEARCH_MOVIE = "search/movie?"+ DEFAULT_PARAMS; //query={SEARCH}
        public static final String MOVIE_DETAIL = "movie/{MOVIE_ID}";
        public static final String IMAGES = "https://image.tmdb.org/t/p/";
        public static final String IMAGES_SMALL = IMAGES + "w200";
        public static final String IMAGES_MEDIUM = IMAGES + "w400";
    }


}
