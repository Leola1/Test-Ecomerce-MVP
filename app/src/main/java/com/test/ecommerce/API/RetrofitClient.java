package com.test.ecommerce.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //было изначально
 // private static final String BASE_URL = LOCALHOST;
 private static final String BASE_URL = "https://rstestapi.redsoftdigital.com/api/v1/";

    private static RetrofitClient mInstance;

    private Retrofit retrofit;

    private RetrofitClient() {

        retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }



    public static synchronized RetrofitClient getInstance() {

        if (mInstance == null) {

            mInstance = new RetrofitClient();

        }
        System.out.println("RetrofitClient getInstance() " + mInstance);
        return mInstance;

    }

    public API getApi() {
        return retrofit.create(API.class);

    }

}
