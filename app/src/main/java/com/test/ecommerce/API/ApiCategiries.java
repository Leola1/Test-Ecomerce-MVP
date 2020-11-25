package com.test.ecommerce.API;

import com.test.ecommerce.ViewModel.ProductApiResponseCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCategiries {

   @GET("https://rstestapi.redsoftdigital.com/api/v1/products")

   Call<ProductApiResponseCategory> getProducts(@Query("id") int idProduct);

    @GET("https://rstestapi.redsoftdigital.com/api/v1/products/{id}")

  Call<ProductApiResponseCategory> getProductsCategory(@Path("id") int productID);

}
