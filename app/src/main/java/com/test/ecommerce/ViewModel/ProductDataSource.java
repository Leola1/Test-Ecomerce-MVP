package com.test.ecommerce.ViewModel;

import android.util.Log;

import com.test.ecommerce.Model.Product;
import com.test.ecommerce.API.RetrofitClient;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private static final int FIRST_PAGE = 1;

   public static final int PAGE_SIZE = 1000;

    private String category;

    private int userId;


    ProductDataSource(String category, int userId) {

        this.category = category;

        this.userId = userId;


    }

    @Override

    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Product> callback) {

        System.out.println("ПРОВЕРКА ProductDataSourse loadInitial  ");
        RetrofitClient.getInstance()

              // .getApi().getProductsByCategory(category, userId,FIRST_PAGE)
            .getApi().getProducts(category)

                .enqueue(new Callback<ProductApiResponse>() {

                    @Override

                    public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {

                     //   Log.v("onResponse", "Succeeded " + response.body().getProducts().size());

                        if (response.body().getProducts() == null) {

                            return;

                        }

                        if (response.body() != null) {

                            callback.onResult(response.body().getProducts(), null,
                                    FIRST_PAGE + 1);
                            System.out.println("ПРОВЕРКА callback прошел post  " + response.body().getProducts());
                         //   System.out.println("ПРОВЕРКА callback response body  " + response.body());
                            System.out.println("ПРОВЕРКА callback response body Name 1 " +
                                    response.body().getProducts().get(0).getProductName());
                            System.out.println("ПРОВЕРКА callback response body Name 2 " +
                                    response.body().getProducts().get(1).getProductName());
                        } }

                    @Override

                    public void onFailure(Call<ProductApiResponse> call, Throwable t) {

                        Log.v("onFailure", "Failed to get Products");
                        System.out.println("FAILURE!!!"+t.getMessage());

                    }

                });

    }



    @Override

    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, Product> callback) {
        System.out.println("ПРОВЕРКА Product Data Source loadBefore");
        RetrofitClient.getInstance()

               // .getApi().getProductsByCategory(category,userId,params.key)
                .getApi().getProducts(category )
                .enqueue(new Callback<ProductApiResponse>() {

                    @Override

                    public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {

                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

                        if (response.body() != null) {

                            // Passing the loaded database and the previous page key

                            callback.onResult(response.body().getProducts(), adjacentKey);

                        }

                    }



                    @Override

                    public void onFailure(Call<ProductApiResponse> call, Throwable t) {

                        Log.v("onFailure", "Failed to previous Products");

                    }

                });

    }



    @Override

    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Product> callback) {

        RetrofitClient.getInstance()

               // .getApi().getProductsByCategory(category,userId,params.key)
                .getApi().getProducts(category)

                .enqueue(new Callback<ProductApiResponse>() {

                    @Override

                    public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {

                        if (response.body() != null) {

                            // If the response has next page, increment the next page number

                            Integer key = response.body().getProducts().size()
                                  == PAGE_SIZE ? params.key + 1 : null;
                            // Passing the loaded database and next page value

                            callback.onResult(response.body().getProducts(), key);

                        }

                    }



                    @Override

                    public void onFailure(Call<ProductApiResponse> call, Throwable t) {

                        Log.v("onFailure", "Failed to get next Products");

                    }

                });

    }

}



