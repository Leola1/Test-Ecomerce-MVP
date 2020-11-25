package com.test.ecommerce.ViewModel;

import android.app.Application;

import com.test.ecommerce.ViewModel.ProductApiResponse;
import com.test.ecommerce.ViewModel.SearchRepository;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.annotations.NonNull;

public class SearchViewModel extends AndroidViewModel {

    private SearchRepository searchRepository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        searchRepository = new SearchRepository(application);
    }


    public LiveData<ProductApiResponse> getProductsBySearch(String keyword, int userId) {

        return searchRepository.getResponseDataBySearch(keyword, userId);
    }
}
