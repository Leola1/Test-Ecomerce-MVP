package com.test.ecommerce.ViewModel;

import com.test.ecommerce.Model.SubCategory;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


public class ProductDataSourceCategories extends PageKeyedDataSource<Integer, SubCategory> {
//все закомментировано
    private static final int FIRST_PAGE = 1;

    public static final int PAGE_SIZE = 50;

    private String category;
    private int categoryID;

    ProductDataSourceCategories(String category, int catID) {

        this.category = category;
        this.categoryID = catID;

    }



    @Override

    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, SubCategory> callback) {

     /*   categoryID=0;
     RetrofitClientCategories.getInstance()

                // .getApi().getProductsByCategory(category, userId,FIRST_PAGE)

                .getApiCategories().getProductsCategory(categoryID)

                .enqueue(new Callback<ProductApiResponseCategory>() {

                    @Override

                    public void onResponse(Call<ProductApiResponseCategory> call,
                                           Response<ProductApiResponseCategory> response) {

                        //   Log.v("onResponse", "Succeeded " + response.body().getProducts().size());
                    //    Log.v("onResponse", "Succeeded " + response.body().getProductsCategory());

                        if (response.body().getProductsCategory() == null) {
                            System.out.println("ПРОВЕРКА ProductDataSourceCategory ==null  ");
                            return;

                        }

                        if (response.body() != null) {
                            System.out.println("ПРОВЕРКА ProductDataSourceCategory != null  ");
                            callback.onResult(response.body().getProductsCategory(), null,
                                    FIRST_PAGE + 1);

                        } }

                    @Override

                    public void onFailure(Call<ProductApiResponseCategory> call, Throwable t) {

                        Log.v("onFailure", "Failed to get Products");
                        System.out.println("FAILURE!!!"+t.getMessage());

                    }

                });*/



    }



    @Override

    public void loadBefore(@NonNull final LoadParams<Integer> params,
                           @NonNull final LoadCallback<Integer, SubCategory> callback) {
/*
     RetrofitClientCategories.getInstance()

                // .getApi().getProductsByCategory(category,userId,params.key)
                .getApiCategories() .getProductsCategory()
                .enqueue(new Callback<ProductApiResponseCategory>() {

                    @Override

                    public void onResponse(Call<ProductApiResponseCategory> call,
                                           Response<ProductApiResponseCategory> response) {

                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

                        if (response.body() != null) {

                            // Passing the loaded database and the previous page key

                            callback.onResult(response.body().getProductsCategory(), adjacentKey);

                        }

                    }



                    @Override

                    public void onFailure(Call<ProductApiResponseCategory> call, Throwable t) {

                        Log.v("onFailure", "Failed to previous Products");

                    }

                });*/

    }



    @Override

    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, SubCategory> callback) {
/*
    RetrofitClientCategories.getInstance()

                // .getApi().getProductsByCategory(category,userId,params.key)
                .getApiCategories() .getProductsCategory()

                .enqueue(new Callback<ProductApiResponseCategory>() {

                    @Override

                    public void onResponse(Call<ProductApiResponseCategory> call,
                                           Response<ProductApiResponseCategory> response) {

                        if (response.body() != null) {

                            // If the response has next page, increment the next page number

                            Integer key = response.body().getProductsCategory().size() ==
                                    PAGE_SIZE ? params.key + 1 : null;


                            // Passing the loaded database and next page value

                            callback.onResult(response.body().getProductsCategory(), key);

                        }

                    }



                    @Override

                    public void onFailure(Call<ProductApiResponseCategory> call, Throwable t) {

                        Log.v("onFailure", "Failed to get next Products");

                    }

                });*/

    }
}
