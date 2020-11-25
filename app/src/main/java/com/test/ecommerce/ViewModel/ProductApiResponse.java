package com.test.ecommerce.ViewModel;

import com.google.gson.annotations.SerializedName;
import com.test.ecommerce.Model.Product;

import java.util.List;

public class ProductApiResponse {

    @SerializedName("data")
    private List<Product> products;

    public List<Product> getProducts() {
        System.out.println("ПРОВЕРКА ProductApiResponse  " + products);
        return products;
    }

    public void setProducts(List<Product> products) {

        this.products = products;

    }

}
