package com.test.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.test.ecommerce.Model.Product;
import com.test.ecommerce.UtilInt.InternetUtils;
import com.test.ecommerce.ViewModel.ProductApiResponseCategory;
import com.test.ecommerce.R;
import com.test.ecommerce.API.RetrofitClientCategories;
import com.test.ecommerce.databinding.ActivityDetailsBinding;
import com.test.ecommerce.Model.newTack;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.test.ecommerce.Model.Constant.PRODUCT;
import static com.test.ecommerce.Model.Constant.PRODUCT_ID;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DetailsActivity";

    private ActivityDetailsBinding binding;

    private Product product;
    private newTack newtack;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
       // reviewViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);

        binding.txtInBasket.setOnClickListener(this);


        getProductDetails();


    }




    private void getProductDetails() {

        // Receive the product object
        product = getIntent().getParcelableExtra(PRODUCT);

        // Set Custom ActionBar Layout
        System.out.println("ПРОВЕРКА Details Activity  " );

        binding.nameOfProductUp.setText(product.getProductName());
        binding.nameOfProduct.setText(product.getProductName());
        binding.priceOfProduct.setText(String.valueOf(product.getProductPrice()));
        binding.nameOfSupplayer.setText(product.getProductSupplier());
        binding.discription.setText(String.valueOf(product.getProductDiscription()));
        binding.cat1.setText(String.valueOf(newTack.getInstance().getTitle()));
        binding.cat2.setText(String.valueOf(product.categoriesID));
        binding.cat3.setText(String.valueOf(product.categoriesParentID));

        String imageUrl =  product.getProductImage();
        Glide.with(this)
                .load(imageUrl)
                .into(binding.imageOfProduct);

        binding.imgBack.setOnClickListener(this);
        binding.txtInBasket.setOnClickListener(this);
        getCategory();

    }

    String imageUrl;

    public void getCategory(){
int categoryID = product.getProductId();

    System.out.println("проверка Детайл Активити categoryID " + categoryID);
    Call<ProductApiResponseCategory> callCats =  RetrofitClientCategories.getInstance()

            .getApiCategories().getProductsCategory(categoryID);

    callCats.enqueue(new Callback<ProductApiResponseCategory>() {

        @Override
        public void onResponse(Call<ProductApiResponseCategory> call,
                               Response<ProductApiResponseCategory> response) {

            System.out.println("проверка Детайл Активити response.body() " + response.body());
            System.out.println("проверка Детайл Активити response.body() body + getProduct "
                    + response.body().getData());
            System.out.println("проверка Детайл Активити response.body() body + getProductID "
                    + response.body().getData().getId());
            System.out.println("проверка Детайл Активити response.body() body + getProductCategories "
                    + response.body().getData().getCategories().size());

            if (response.body() != null) {
              //  System.out.println("проверка Детайл Активити response.body() != null");
                if(response.body().getData().getCategories().size()!=0){
              //  for (int i = 0; i <= response.body().getData().getCategories().size(); i++){
                 //   ArrayList <String> cat = new ArrayList<>();
                    if(response.body().getData().getCategories().size()==1){
                        String cat_1 = String.valueOf(response.body().getData().getCategories().get(0).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(0).getId())+
                                String.valueOf(response.body().getData().getCategories().get(0).getParentId());

                        binding.cat1.setText(cat_1);binding.cat2.setText("");
                        binding.cat3.setText("");}

                 if(response.body().getData().getCategories().size()==2){
                     String cat_1 = String.valueOf(response.body().getData().getCategories().get(0).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(0).getId())+
                                String.valueOf(response.body().getData().getCategories().get(0).getParentId());
                      String cat_2 = String.valueOf(response.body().getData().getCategories().get(1).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(1).getId())+
                                String.valueOf(response.body().getData().getCategories().get(1).getParentId());
                         binding.cat1.setText(cat_1);binding.cat2.setText(cat_2);
                        binding.cat3.setText("");}
                    if(response.body().getData().getCategories().size()==3){

                        String cat_1 = String.valueOf(response.body().getData().getCategories().get(0).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(0).getId())+
                                String.valueOf(response.body().getData().getCategories().get(0).getParentId());
                        String cat_2 = String.valueOf(response.body().getData().getCategories().get(1).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(1).getId())+
                                String.valueOf(response.body().getData().getCategories().get(1).getParentId());
                        String cat_3 = String.valueOf(response.body().getData().getCategories().get(2).getTitle())+
                                String.valueOf(response.body().getData().getCategories().get(2).getId())+
                                String.valueOf(response.body().getData().getCategories().get(2).getParentId());
                        binding.cat1.setText(cat_1);binding.cat2.setText(cat_2);
                        binding.cat3.setText(cat_3);
                   }
                }

                binding.nameOfProductUp.setText(String.valueOf(response.body().getData().getTitle()));
                binding.nameOfProduct.setText(String.valueOf(response.body().getData().getTitle()));
                binding.priceOfProduct.setText(String.valueOf(response.body().getData().getPrice()));
                binding.nameOfSupplayer.setText(String.valueOf(response.body().getData().getProducer()));
                binding.discription.setText(String.valueOf(response.body().getData().getShortDescription()));


                //  String imageUrl = LOCALHOST + product.getProductImage().replaceAll("\\\\", "/");
                imageUrl =  String.valueOf(response.body().getData().getImageUrl());
                loadImage();


             //   System.out.println("проверка Детайл Активити response.body() + getProduct + get0 "
              //          + response.body().getProductsCategory().get(0));
            }
            else{

                binding.cat1.setText(" ");
                binding.cat2.setText(" ");
                binding.cat3.setText(" ");
                binding.nameOfProductUp.setText(String.valueOf(response.body().getData().getTitle()));
                binding.nameOfProduct.setText(String.valueOf(response.body().getData().getTitle()));
                binding.priceOfProduct.setText(String.valueOf(response.body().getData().getPrice()));
                binding.nameOfSupplayer.setText(String.valueOf(response.body().getData().getProducer()));
                binding.discription.setText(String.valueOf(response.body().getData().getShortDescription()));

                //  String imageUrl = LOCALHOST + product.getProductImage().replaceAll("\\\\", "/");
                imageUrl =  String.valueOf(response.body().getData().getImageUrl());
                loadImage();
            }

        }

        @Override
        public void onFailure(Call<ProductApiResponseCategory> call, Throwable t) {
            System.out.println("FAILURE!!!"+t.getMessage());
        }
    });
}

public void loadImage(){
    Glide.with(this)
            .load(imageUrl)
            .into(binding.imageOfProduct);

}



    @Override

    public void onClick(View view) {

       if (view.getId() == R.id.imgBack) {

            Intent backIntent = new Intent(DetailsActivity.this, MainActivity.class);

           backIntent.putExtra(PRODUCT_ID,product.getProductId());

            startActivity(backIntent);

        }
       else if(view.getId() == R.id.txtInBasket){

           if (product.isInCart()==false) {

               int q = product.getProductQuantity();

               binding.imgK.setImageAlpha(0);
               binding.txtInBasket.setText(" -  "+q+"  + ");

               product.setIsInCart(true);

           } else {
               binding.imgK.setImageAlpha(200);

               binding.txtInBasket.setText("В корзину");
               product.setIsInCart(false);


           }
       }

    }



    private void insertToCart(InternetUtils.RequestCallback callback) {

      /*  Cart cart = new Cart(LoginUtils.getInstance(this).getUserInfo().getId(), product.getProductId());

        toCartViewModel.addToCart(cart, callback);*/

    }



    @Override

    protected void onResume() {

        super.onResume();



    }
}
