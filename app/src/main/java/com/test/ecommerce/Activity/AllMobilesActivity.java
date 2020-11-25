package com.test.ecommerce.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.test.ecommerce.Model.Product;
import com.test.ecommerce.adapter.ProductAdapter;
import com.test.ecommerce.ViewModel.ProductViewModel;
import com.test.ecommerce.R;
import com.test.ecommerce.databinding.ActivityAllMobilesBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.test.ecommerce.Model.Constant.PRODUCT;

public class AllMobilesActivity extends AppCompatActivity implements ProductAdapter.ProductAdapterOnClickHandler {

    private ActivityAllMobilesBinding binding;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       // loadLocale(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_mobiles);

       ActionBar actionBar = getSupportActionBar();

        int userID=0;

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.loadMobiles("mobile", userID);

        setupRecyclerViews();

        getAllProduct();

    }



    private void setupRecyclerViews() {

       // binding.allMobilesRecyclerView.setLayoutManager(new GridLayoutManager
        //        (this, (getResources().getConfiguration().orientation ==
        //                Configuration.ORIENTATION_PORTRAIT) ? 2 : 4));
        binding.allMobilesRecyclerView.setLayoutManager( new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));

        binding.allMobilesRecyclerView.setHasFixedSize(true);

        productAdapter = new ProductAdapter(this,this);

    }



    public void getAllProduct() {

        // Observe the productPagedList from ViewModel

        productViewModel.productPagedList.observe(this, new Observer<PagedList<Product>>() {

            @Override

            public void onChanged(@Nullable PagedList<Product> products) {

                productAdapter.notifyDataSetChanged();

                productAdapter.submitList(products);

            }

        });

        binding.allMobilesRecyclerView.setAdapter(productAdapter);

    }



    @Override

    public void onClick(Product product) {

        Intent intent = new Intent(this, DetailsActivity.class);

        // Pass an object of product class

        intent.putExtra(PRODUCT, (product));

        startActivity(intent);

    }

}
