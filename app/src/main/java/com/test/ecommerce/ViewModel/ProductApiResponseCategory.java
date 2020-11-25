package com.test.ecommerce.ViewModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.ecommerce.Model.SubCategory;

public class ProductApiResponseCategory {

    @SerializedName("data")
    @Expose
    private SubCategory data;

    public SubCategory getData() {
        return data;
    }

    public void setData(SubCategory data) {
        this.data = data;
    }


}
