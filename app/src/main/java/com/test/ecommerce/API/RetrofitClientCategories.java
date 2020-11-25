package com.test.ecommerce.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientCategories {


    private static final String BASE_URL = "https://rstestapi.redsoftdigital.com/api/v1/";


    private static RetrofitClientCategories mInstance;

    private Retrofit retrofit;

    private RetrofitClientCategories() {

        retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }



    public static synchronized RetrofitClientCategories getInstance() {

        if (mInstance == null) {

            mInstance = new RetrofitClientCategories();

        }
        System.out.println("RetrofitClientCategories getInstance() " + mInstance);
        return mInstance;

    }


    public ApiCategiries getApiCategories() {
        return retrofit.create(ApiCategiries.class);

    }
}
