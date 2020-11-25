package com.test.ecommerce.ViewModel;

import android.app.Application;
import android.util.Log;

import com.test.ecommerce.API.RetrofitClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private static final String TAG = SearchRepository.class.getSimpleName();
        private Application application;

        public SearchRepository(Application application) {
            this.application = application;
        }

        public LiveData<ProductApiResponse> getResponseDataBySearch(String keyword, int userId) {
            final MutableLiveData<ProductApiResponse> mutableLiveData = new MutableLiveData<>();
            System.out.println("ПРОВЕРКА Search SearchRepository LiveData keyword " + keyword);
            RetrofitClient.getInstance()
                    .getApi().searchForProduct(keyword)
                    .enqueue(new Callback<ProductApiResponse>() {
                        @Override
                        public void onResponse(Call<ProductApiResponse> call, Response<ProductApiResponse> response) {
                            Log.d(TAG, "onResponse: Succeeded");
                            System.out.println("ПРОВЕРКА Search SearchRepository response body " + response.body());
                            ProductApiResponse productApiResponse = response.body();

                            if (response.body() != null) {
                                mutableLiveData.setValue(productApiResponse);
                                Log.d(TAG, String.valueOf(response.body().getProducts()));
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductApiResponse> call, Throwable t) {
                            Log.v("onFailure", " Failed to get products");
                        }
                    });
            return mutableLiveData;
        }
}
