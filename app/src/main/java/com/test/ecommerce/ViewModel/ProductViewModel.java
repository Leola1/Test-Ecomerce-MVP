package com.test.ecommerce.ViewModel;

import com.test.ecommerce.Model.Product;
import com.test.ecommerce.ViewModel.ProductDataSource;
import com.test.ecommerce.ViewModel.ProductDataSourceFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import static com.test.ecommerce.ViewModel.ProductDataSourceFactory.productDataSource;

public class ProductViewModel extends ViewModel {
    // Create liveData for PagedList and PagedKeyedDataSource

    public LiveData<PagedList<Product>> productPagedList;
    private LiveData<PageKeyedDataSource<Integer, Product>> productLiveDataSource;

    // Get PagedList configuration

    private final static PagedList.Config  pagedListConfig =
            (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                   .setPageSize(ProductDataSource.PAGE_SIZE)
                    .build();


    public void loadMobiles(String category, int userId){
        // Get our database source factory

        ProductDataSourceFactory productDataSourceFactory = new ProductDataSourceFactory(category,userId);
        // Get the live database source from database source factory
        productLiveDataSource = productDataSourceFactory.getProductLiveDataSource();
        // Build the paged list
        productPagedList = (new LivePagedListBuilder(productDataSourceFactory, pagedListConfig)).build();

    }

   public void invalidate() {

        if (productDataSource != null) productDataSource.invalidate();

    }
}
