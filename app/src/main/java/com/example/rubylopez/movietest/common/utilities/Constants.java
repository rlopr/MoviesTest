package com.example.rubylopez.movietest.common.utilities;

import com.example.rubylopez.movietest.BuildConfig;

/**
 * Created by hhorta on 17-02-17.
 */

public final class Constants {

    public static final int TIMEOUT = 900000; //20000; //5000; //millis

    public static final int API_CONNECTION_TIMEOUT = 120;

    public static final int OK = 200;

    public static final int ERROR_SERVER = 500;
    public static final int ERROR_CLIENT = 400;

    public static final int AMOUNT_ANIMATION_DURATION = 1500;
    public static final String RELEASE = "release";
    public static final String DEMO = "demo";
    public static final String PAGE_TITLE = "Page";
    public static final String LOCALE = "ES";
    public static final String CURRENCY_SYMBOL = "$";
    public static final String CURRENCY_US_SYMBOL = "US";
    public static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String ISO_DATE_FORMAT_INCOMPLETE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String VERBOSE_YEAR_MONTH_FORMAT = "MMMM yyyy";
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String MMDDYY = "MM/dd/yy";
    public static final String DDMMYY = "dd/MM/yy";


    public final class APIConstants {

        private APIConstants() {
        }

        public static final String LANGUAGE = "en-US";
        public static final String DEFAULT_PARAMS = "";//"&api_key=" + BuildConfig.CLIENT_ID + "&language=" + LANGUAGE;

        public static final String ALL_MOVIES = "movie/upcoming?page=1"+ DEFAULT_PARAMS;
        public static final String SEARCH_MOVIE = "search/movie?"+ DEFAULT_PARAMS; //query={SEARCH}
        public static final String MOVIE_DETAIL = "movie/{MOVIE_ID}";
        public static final String IMAGES = "https://image.tmdb.org/t/p/w200/{IMAGE_ID}";

    }


}
