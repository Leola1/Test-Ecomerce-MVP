package com.test.ecommerce;

import com.test.ecommerce.Model.SubCategory;
import com.test.ecommerce.Model.newTack;

import java.util.List;

public class SubCategoryClass {

    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productSupplier;
    private String shortDescription;
    private List<newTack.Category> categories=null;
   private String productImage;

    public SubCategoryClass() {
    }
    private List<SubCategoryClass> dataClass = null;
    public List<SubCategoryClass> getDataClass() {
        return dataClass;
    }
    public void setDataClass(List<SubCategoryClass> dataClass) {
        this.dataClass = dataClass;
    }


    public List<SubCategory> listSubCat;
    public List<SubCategory> getListSubCategory() {
        return listSubCat;
    }
    public void setListSubCat(List<SubCategory> listSubCat) {
        this.listSubCat = listSubCat;
    }

    public int getProductId() {
        return productId; }
    public void setProductId(int productId) {
        this.productId = productId;}

    public String getProductName() {
        System.out.println("ПРОВЕРКА Product getProductName()" + productName);
        return productName; }
    public void setProductName(String productName) {
        this.productName = productName;}

    public double getProductPrice() {
        return productPrice; }
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;}

    public List<newTack.Category> getCategories() {
        System.out.println("ПРОВЕРКА SubCategory getCategories" + categories);

        return categories; }
    public void setProductCategories(List<newTack.Category> category) {
        this.categories = category;}

    public int getProductQuantity() {
        return productQuantity;
    }
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;}

    public String getProductSupplier() {
        return productSupplier;
    }
    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;}
    public String getProductDiscription() {
        return shortDescription;
    }
    public void setProductDiscription(String productDiscription) {
        this.shortDescription = productDiscription;}

    public String getProductImage() {
        return productImage;
    }
    public void setProductImage(String productImage) {
        this.productImage = productImage;}
}
