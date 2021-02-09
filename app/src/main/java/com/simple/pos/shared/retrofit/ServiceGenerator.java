package com.simple.pos.shared.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.simple.pos.BuildConfig;

public class ServiceGenerator {
    private static final String BASE_URL = BuildConfig.baseUrl;

    private static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setPrettyPrinting()
            .setVersion(1.0)
            .create();

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ?
            HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(new AcceptJsonInterceptor());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String token) {
        if (!TextUtils.isEmpty(token)) {
            JWTAuthenticationInterceptor interceptor =
                    new JWTAuthenticationInterceptor(token);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }

        builder.client(httpClient.build());
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    public static Retrofit retrofit() {
        return retrofit;
    }
}
