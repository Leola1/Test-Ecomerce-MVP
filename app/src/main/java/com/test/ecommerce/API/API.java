package com.test.ecommerce.API;

import com.test.ecommerce.ViewModel.ProductApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {


   @GET("https://rstestapi.redsoftdigital.com/api/v1/products")

    Call<ProductApiResponse> getProducts(@Query("producer") String producer);


  @GET("https://rstestapi.redsoftdigital.com/api/v1/products")

    Call<ProductApiResponse> searchForProduct(@Query("title") String keyword);



}
