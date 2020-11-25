package com.test.ecommerce.ViewModel;

import com.test.ecommerce.Model.SubCategory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;


public class ProductDataSourceFactoryCategories extends DataSource.Factory{
    private MutableLiveData<PageKeyedDataSource<Integer, SubCategory>> productLiveDataSource = new MutableLiveData<>();

    public static ProductDataSourceCategories productDataSource;

    private String category;
    private int userId;

    public ProductDataSourceFactoryCategories(String category, int userId){
        this.category = category;
        this.userId = userId;
    }

    @Override
    public DataSource<Integer, SubCategory> create() {
        System.out.println("ПРОВЕРКА ProductDataSourceFactoryCategory DataSource ");
        // Getting our Data source object
        productDataSource = new ProductDataSourceCategories(category, userId);

        // Posting the Data source to get the values
        productLiveDataSource.postValue(productDataSource);

        // Returning the Data source
        return productDataSource;
    }
    // Getter for Product live DataSource

    public MutableLiveData<PageKeyedDataSource<Integer, SubCategory>> getProductLiveDataSource() {
        return productLiveDataSource;
    }
}
