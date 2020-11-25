package com.test.ecommerce.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.test.ecommerce.UtilInt.NetworkChangeReceiver;
import com.test.ecommerce.UtilInt.OnNetworkListener;
import com.test.ecommerce.Model.Product;
import com.test.ecommerce.adapter.ProductAdapter;
import com.test.ecommerce.ViewModel.ProductViewModel;
import com.test.ecommerce.R;
import com.test.ecommerce.Model.Slide;
import com.test.ecommerce.databinding.ActivityMainBinding;

import java.util.ArrayList;

import static com.test.ecommerce.Model.Constant.PRODUCT;

import static com.test.ecommerce.UtilInt.InternetUtils.isNetworkConnected;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        OnNetworkListener, ProductAdapter.ProductAdapterOnClickHandler,

        NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ProductActivity";
    private ActivityMainBinding binding;
    private ProductAdapter mobileAdapter;
    private ProductViewModel productViewModel;
    private Snackbar snack;
    private Uri selectedImage;
    private NetworkChangeReceiver mNetworkReceiver;

    public int quntityInCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        int userID = 0;
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.loadMobiles("product", userID);

        snack = Snackbar.make(findViewById(android.R.id.content), getResources().
                getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE);
        binding.included.content.txtSeeAll.setOnClickListener(this);
        binding.included.txtSearch.setOnClickListener(this);

        quntityInCart=0;

        setUpViews();
        getProducts();
        flipImages(Slide.getSlides());

        mNetworkReceiver = new NetworkChangeReceiver();
        mNetworkReceiver.setOnNetworkListener(this);


    }

    private void setUpViews() {

        Toolbar toolbar = binding.included.toolbar;
        setSupportActionBar(toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

       binding.navView.setNavigationItemSelectedListener(this);

        binding.included.content.listOfMobiles.setHasFixedSize(true);

        binding.included.content.listOfMobiles.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mobileAdapter = new ProductAdapter(this, this);


    }



   private void getProducts() {

        if (isNetworkConnected(this)) {
            productViewModel.productPagedList.observe(this, new Observer<PagedList<Product>>() {
                @Override
                public void onChanged(@Nullable PagedList<Product> products) {
                    mobileAdapter.submitList(products);

                }

            });

            binding.included.content.listOfMobiles.setAdapter(mobileAdapter);
            mobileAdapter.notifyDataSetChanged();

        } else {

            showOrHideViews(View.INVISIBLE);

            showSnackBar();

        }

    }


    private void flipImages(ArrayList<Integer> images) {

        for (int image : images) {

            ImageView imageView = new ImageView(this);

            imageView.setBackgroundResource(image);

            binding.included.content.imageSlider.addView(imageView);

        }
        binding.included.content.imageSlider.setFlipInterval(2000);

        binding.included.content.imageSlider.setAutoStart(true);

        // Set the animation for the view that enters the screen

        //binding.included.content.imageSlider.setInAnimation(this, R.anim.slide_in_right);

        // Set the animation for the view leaving th screen

      //  binding.included.content.imageSlider.setOutAnimation(this, R.anim.slide_out_left);

    }



    @Override

    public void onClick(View view) {

      switch (view.getId()) {

            case R.id.txtSeeAll:

                Intent mobileIntent = new Intent(this, AllMobilesActivity.class);

                startActivity(mobileIntent);

                break;


          case R.id.txtSearch:

                Intent searchIntent = new Intent(this, ResultActivity.class);

                startActivity(searchIntent);

                break;

        }

    }

    public void showSnackBar() {

        snack.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));

        snack.show();

    }

    public void hideSnackBar() {

        snack.dismiss();

    }



    private void registerNetworkBroadcastForNougat() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }

    }

    @Override

    protected void onStart() {

        super.onStart();

        registerNetworkBroadcastForNougat();

    }

    @Override

    protected void onStop() {

        super.onStop();

        unregisterReceiver(mNetworkReceiver);

    }

    @Override

    public void onNetworkConnected() {

        hideSnackBar();
        showOrHideViews(View.VISIBLE);
        getProducts();

    }



    @Override

    public void onNetworkDisconnected() {

        showSnackBar();

    }

    @Override

    public void onClick(Product product) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(PRODUCT,(product));
        startActivity(intent);

    }


    private void showOrHideViews(int view) {

        binding.included.content.textViewMobiles.setVisibility(view);

        binding.included.content.txtSeeAll.setVisibility(view);


    }



    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

    @Override

    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            closeApplication();

        }

    }



    private void closeApplication() {

        AlertDialog alertDialog = new AlertDialog.Builder(this)

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setMessage(R.string.want_to_exit)

                .setPositiveButton(R.string.ok, (dialog, which) -> finish())

                .setNegativeButton(R.string.cancel, null)

                .show();



        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.darkGreen));

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.darkGreen));

    }





    @Override

    protected void onResume() {

        super.onResume();

    }



    private PagedList.Callback productCallback = new PagedList.Callback() {

        @Override

        public void onChanged(int position, int count) {

            Log.d(TAG, "onChanged: "+ count);

        }

        @Override

        public void onInserted(int position, int count) {

            Log.d(TAG, "onInserted: "+ count);



        }

        @Override

        public void onRemoved(int position, int count) {

            Log.d(TAG, "onRemoved: "+ count);

        }

    };


}