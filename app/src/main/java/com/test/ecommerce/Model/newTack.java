package com.test.ecommerce.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class newTack implements Parcelable  {
    @SerializedName("id")
    private int categoryId;
    @SerializedName("title")
    private String title;
    @SerializedName("parent_id")
    private int parentId;

    private static newTack mInstance;

    public static synchronized newTack getInstance() {
        if (mInstance == null) {
            mInstance = new newTack();
        }

        return mInstance; }

        public int catID;
    public String titleCat;

  public newTack(){}

    private newTack mInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public  String  toString(){

        return title + " "+categoryId +" "+parentId;
    }

    public String getStringCategory1() {
        titleCat = title;
        catID = categoryId;
        System.out.println("ПРОВЕРКА newTask categoriesTitle " + title);
        return title + " "+categoryId +" "+parentId;
    }
    public String getStringCategory2() {

        return title + " "+categoryId +" "+parentId;
    }
    public String getStringCategory3() {
        return title + " "+categoryId +" "+parentId;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(categoryId);
        out.writeString(title);
        out.writeInt(parentId);
        out.writeParcelable(mInfo, flags);

    }


    private newTack(Parcel in) {

       categoryId = in.readInt();
       title = in.readString();
        parentId = in.readInt();
        mInfo = in.readParcelable(newTack.class.getClassLoader());

    }

    @Override

    public int describeContents() {
        return 0;

    }



    // Create the Parcelable.Creator<Product> CREATOR` constant for our class;

    public static final Parcelable.Creator<newTack> CREATOR
            = new Parcelable.Creator<newTack>() {

        // This simply calls our new constructor and

        // passes along `Parcel`, and then returns the new object!

        @Override

        public newTack createFromParcel(Parcel in) {

            return new newTack(in);

        }

        @Override

        public newTack[] newArray(int size) {

            return new newTack[size];

        }

    };

    public static class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("parent_id")
        @Expose
        private Integer parentId;

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

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }



      /*  @SerializedName("id")
        public Integer idCat;
        @SerializedName("title")
        public String titleCategory;
        // @SerializedName("parent_id")
        // public Integer parentId;
        public Category() {
        }

        public int getCategoryId() {
            System.out.println("ПРОВЕРКА SubCategory[] ID" + idCat);
            return idCat; }

        public String getTitleCategory() {
            return titleCategory; }

        public void setCategoryId(Integer idCat) {
            this.idCat = idCat;}

        public void setCategoryTitle (String title) {
            this.titleCategory = title;}*/


    }
}
