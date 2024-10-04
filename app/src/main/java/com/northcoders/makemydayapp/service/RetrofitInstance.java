package com.northcoders.makemydayapp.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit userRetrofit = null;
    private static Retrofit eventRetrofit = null;

    private final static String Base_URL = "http://10.0.2.2:8080/api/v1/";

    public static UserApiService getUserApiService() {

        if (userRetrofit == null) {
            userRetrofit = createRetrofit();
        }

        return userRetrofit.create(UserApiService.class);
    }


    public static EventsApiService getEventsApiService() {

        if (eventRetrofit == null) {
            eventRetrofit = createRetrofit();
        }

        return eventRetrofit.create(EventsApiService.class);
    }

    private static Retrofit createRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofitToReturn = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofitToReturn;
    }


}