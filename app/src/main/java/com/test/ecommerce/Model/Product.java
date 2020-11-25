package com.test.ecommerce.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.Nullable;


public class Product implements Parcelable {
    @SerializedName("id")
    private int productId;
    @SerializedName("title")
    private String productName;
    @SerializedName("price")
    private double productPrice;
    @SerializedName("amount")
    private int productQuantity;
    @SerializedName("producer")
    private String productSupplier;

    @SerializedName("short_description")
    private String shortDescription;
    @Nullable
    @SerializedName("categories")
  private List<newTack> categories;

  @SerializedName("image_url")
  private String productImage;


    public boolean isInCart;

    public int quantityOfProductInTheCart;
    // Include child Parcelable objects
    private Product mInfo;



   // public Product(String productName, double productPrice,
      //             int productQuantity, String productSupplier, List<newTack> categories) {
 public Product(String productName, double productPrice,
        int productQuantity, String productSupplier, String subCategory, String productDiscription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productSupplier = productSupplier;
        this.shortDescription = productDiscription;
        this.subCategory = subCategory;

     System.out.println("ПРОВЕРКА Product parse");
    }



    public Product() {
    }

    public int getProductId() {
        return productId; }

    public String getProductName() {
        System.out.println("ПРОВЕРКА Product getProductName()" + productName);
        return productName; }

    public double getProductPrice() {
        return productPrice; }


    public List<newTack> getCategories() {
      //  System.out.println("ПРОВЕРКА Product getCategories" + categories);

        return categories; }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductSupplier() {
        return productSupplier;
    }
    public String getProductDiscription() {
        return shortDescription;
    }

    String subCategory;
    public String categoriesTitle;
    public int categoriesID;
    public int categoriesParentID;
   //newTack newtask = new newTack();
    public String getProductCategory() {

      //  String subCat =  getSubCategory().toString().replaceAll("\\[|\\]|,","");

        subCategory =  getCategories().toString().replaceAll("\\[|\\]|,","");
      //  System.out.println("ПРОВЕРКА Product getProductCategory()  " + subCategory);

      // categoriesID = newTack.getInstance().catID;
      //  categoriesTitle = newTack.getInstance().titleCat;
     //   categoriesParentID = getCategories().get(0).getParentId();

   //  System.out.println("ПРОВЕРКА Product  categoriesTitle " + categoriesTitle);
        return subCategory; }



    public String getProductImage() {
        return productImage; }


    public boolean isInCart() {
        return isInCart; }

        public int getQuantityOfProductInTheCart() {
        return quantityOfProductInTheCart; }


    public void setIsInCart(boolean isInCart) {
        this.isInCart = isInCart; }

    public void setIsQuantityOfProductInTheCart(int quantity) {
        this.quantityOfProductInTheCart = quantity;
    }

        // Write the values to be saved to the `Parcel`.

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(productId);
        out.writeString(productName);
        out.writeDouble(productPrice);
        out.writeInt(productQuantity);
        out.writeString(productSupplier);
        out.writeString(shortDescription);
        out.writeString(subCategory);
        out.writeString(productImage);
        out.writeParcelable(mInfo, flags);

    }



    // Retrieve the values written into the `Parcel`.

    private Product(Parcel in) {

        productId = in.readInt();
        productName = in.readString();
        productPrice = in.readDouble();
        productQuantity = in.readInt();
        productSupplier = in.readString();
        shortDescription = in.readString();
       // categories = new ArrayList<newTack>();
       // in.readList(categories,SubCategory[].class.getClassLoader());
        subCategory = in.readString();
        productImage = in.readString();
        mInfo = in.readParcelable(Product.class.getClassLoader());

    }

    @Override

    public int describeContents() {
        return 0;

    }



    // Create the Parcelable.Creator<Product> CREATOR` constant for our class;

    public static final Creator<Product> CREATOR
            = new Creator<Product>() {

        // This simply calls our new constructor and

        // passes along `Parcel`, and then returns the new object!

        @Override

        public Product createFromParcel(Parcel in) {

            return new Product(in);

        }



        @Override

        public Product[] newArray(int size) {

            return new Product[size];

        }

    };
}
