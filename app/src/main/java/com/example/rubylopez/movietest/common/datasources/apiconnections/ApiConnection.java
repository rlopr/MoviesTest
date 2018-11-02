package com.example.rubylopez.movietest.common.datasources.apiconnections;

import android.content.Context;
import com.example.rubylopez.movietest.BuildConfig;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiConnection {

    private static ApiEndpointInterface apiEndpointInterface;
    private static Retrofit retrofit;

    private ApiConnection() {
    }

    public static ApiEndpointInterface getApi(final Context context) {
        if (apiEndpointInterface == null)
            initialize(context);

        return apiEndpointInterface;
    }

    private static void initialize(final Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat(Constants.ISO_DATE_FORMAT)
                .create();

        final OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(null);

        builder.readTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder()
                        .addQueryParameter("api_key", BuildConfig.CLIENT_ID)
                        .addQueryParameter("language", "en-US")
                        .build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });

        //TODO ADD loggind only in dev
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();

        apiEndpointInterface = retrofit.create(ApiEndpointInterface.class);

    }
}
