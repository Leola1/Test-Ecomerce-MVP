package com.test.ecommerce.ViewModel;

import com.test.ecommerce.Model.SubCategory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import static com.test.ecommerce.ViewModel.ProductDataSourceFactoryCategories.productDataSource;


public class ProductViewModelCategories extends ViewModel {
    // Create liveData for PagedList and PagedKeyedDataSource

    public LiveData<PagedList<SubCategory>> productPagedListCategory;
    private LiveData<PageKeyedDataSource<Integer, SubCategory>> productLiveDataSourceCategory;

    // Get PagedList configuration

    private final static PagedList.Config  pagedListConfig =
            (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                    .setPageSize(ProductDataSourceCategories.PAGE_SIZE)
                    .build();


    public void loadMobiles(String category, int userId){
        // Get our database source factory
        ProductDataSourceFactoryCategories productDataSourceFactoryCategories =
                new ProductDataSourceFactoryCategories(category,userId);

        productLiveDataSourceCategory = productDataSourceFactoryCategories.getProductLiveDataSource();


        // Build the paged list
        productPagedListCategory = (new LivePagedListBuilder(productDataSourceFactoryCategories, pagedListConfig)).build();

    }

    public void invalidate() {

        if (productDataSource != null) productDataSource.invalidate();

    }
}
