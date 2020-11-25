package com.test.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("categories")
    @Expose
    private List<newTack.Category> categories = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<newTack.Category> getCategories() {
        return categories;
    }

    public void setCategories(List<newTack.Category> categories) {
        this.categories = categories;
    }


  /*  @SerializedName("id")
    private Integer productId;
    @SerializedName("title")
    private String productName;
    @SerializedName("price")
    private Double productPrice;
    @SerializedName("amount")
    private Integer productQuantity;
    @SerializedName("producer")
    private String productSupplier;

    @SerializedName("short_description")
    private String shortDescription;

     @SerializedName("categories")
      private List<Category> categories = null;

    @SerializedName("image_url")
     private String productImage;


   // @Expose
  //  private List<SubCategory> listSubCat;
  //  public SubCategory() {
 //   }

  /* public SubCategoryClass subCategoryClass = new SubCategoryClass();
    public SubCategoryClass getSubCategoryClass() {
        return subCategoryClass;
    }
    public void setsubCategoryClass(SubCategoryClass subCategoryClass) {
        this.subCategoryClass = subCategoryClass;
    }*/

   /* private List<SubCategory> listSubCat;
    public List<SubCategory> getListSubCategory() {
       return listSubCat;}
      public void setListSubCat(List<SubCategory> listSubCat) {
   this.listSubCat = listSubCat;}
    private  SubCategory category;
    public SubCategory getSubCategory(){
        System.out.println("ПРОВЕРКА SubCategory getSubCategory " + category);
        return category; }

    public void setSubCategory(SubCategory category){
        this.category = category;
    }


    private List<SubCategoryClass> dataClass = null;



    public List<SubCategoryClass> getDataClass() {
        return dataClass;
    }
    public void setDataClass(List<SubCategoryClass> dataClass) {
        this.dataClass = dataClass;
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
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;}

    public String getProductImage() {
        System.out.println("ПРОВЕРКА Product getProductName()" + productImage);
        return productImage; }
    public void setProductImage(String productImage) {
        this.productImage = productImage;}

    public List<Category> getCategories() {
        System.out.println("ПРОВЕРКА SubCategory getCategories" + categories);
        return categories; }
    public void setProductCategories(List<Category> category) {
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
        this.shortDescription = productDiscription;}*/

}
