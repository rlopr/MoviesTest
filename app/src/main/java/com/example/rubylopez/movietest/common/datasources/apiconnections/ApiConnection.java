package com.example.rubylopez.movietest.common.datasources.apiconnections;

import android.content.Context;
import com.example.rubylopez.movietest.BuildConfig;
import com.example.rubylopez.movietest.common.datasources.interfaces.ApiEndpointInterface;
import com.example.rubylopez.movietest.common.utilities.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
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

    public static void initialize(final Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat(Constants.ISO_DATE_FORMAT)
                .create();

        final OkHttpClient.Builder builder = new OkHttpClient.Builder().cache(null);

        builder.readTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(Constants.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        final OkHttpClient okHttpClient = builder.build();

        okHttpClient.interceptors().add(new Interceptor() {
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

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        apiEndpointInterface = retrofit.create(ApiEndpointInterface.class);

    }
}
