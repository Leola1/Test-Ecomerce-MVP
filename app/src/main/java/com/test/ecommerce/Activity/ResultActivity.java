package com.test.ecommerce.Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.test.ecommerce.Model.Product;
import com.test.ecommerce.R;
import com.test.ecommerce.adapter.SearchAdapter;
import com.test.ecommerce.ViewModel.SearchViewModel;
import com.test.ecommerce.databinding.ActivityResultBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.test.ecommerce.Model.Constant.KEYWORD;
import static com.test.ecommerce.Model.Constant.PRODUCT;

import static com.test.ecommerce.UtilInt.InternetUtils.isNetworkConnected;

public class ResultActivity extends AppCompatActivity implements Filterable {

    private ActivityResultBinding binding;
    private SearchAdapter searchAdapter;
    private List<Product> searchedList;
    private List<Product> searchedListnf;
    private SearchViewModel searchViewModel;
    private int userId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);

        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        System.out.println("ПРОВЕРКА result Activity )" );
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra(KEYWORD);

        userId = 0;

        if (isNetworkConnected(getApplicationContext())) {
            Search(keyword);
        }
    }

    public void onClick(View view) {
        System.out.println("ПРОВЕРКА кнопка возврата result activity onClick  " );
        Search("");

    }

    private void Search(String query) {
        System.out.println("ПРОВЕРКА  result Activity Search " );
        binding.listOfSearchedList.setHasFixedSize(true);

        binding.listOfSearchedList.setLayoutManager( new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));
        searchViewModel.getProductsBySearch(query, userId).observe(this, productApiResponse -> {
            System.out.println("ПРОВЕРКА  result Activity Search Response " );
            if (productApiResponse != null) {
                searchedListnf = productApiResponse.getProducts();

                if (searchedListnf.isEmpty()) {
                    Toast.makeText(ResultActivity.this, "No Result", Toast.LENGTH_SHORT).show();
                }
                getFilter().filter(query);
                searchAdapter = new SearchAdapter(getApplicationContext(), searchedList,
                        new SearchAdapter.SearchAdapterOnClickHandler() {
                    @Override
                    public void onClick(Product product) {
                        Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
                        // Pass an object of product class
                        intent.putExtra(PRODUCT, (product));
                        startActivity(intent);
                    }
                },this);
            }

            binding.listOfSearchedList.setAdapter(searchAdapter);

        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("ПРОВЕРКА меню Result Activity key word " + query);
                Search(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("ПРОВЕРКА Result Activity onQueryTextChange " + newText);
                Search(newText);

                return false;
            }
        });
        searchView.setOnCloseListener((new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                System.out.println("ПРОВЕРКА setOnCloseListener" );
                return false;
            }
        }));


        return true;
    }
    @Override
    public Filter getFilter() {
        System.out.println("ПРОВЕРКА Result Activity  ");
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                System.out.println("ПРОВЕРКА Result Activity charString "+ charString);
                if (charString.isEmpty()) {
                    searchedList = new ArrayList<>();
                      searchedList = searchedListnf;
                    System.out.println("ПРОВЕРКА Result Activity isEmpty  "+ searchedList);
                } else {
                  //  System.out.println("ПРОВЕРКА Search Adapter NOT isEmpty до фильтра  + charString "
                    //        + searchedList + charString);

                    searchedList = searchFollowersFilter(searchedListnf, charString);
                  //  System.out.println("ПРОВЕРКА Search Adapter NOT isEmpty после фильтра "+ searchedList);
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = searchedList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                searchedList = (ArrayList<Product>) filterResults.values;
                //notifyDataSetChanged();
            }
        };
    }


    public static List<Product> searchFollowersFilter(List<Product> list, String charString) {
        List<Product> filteredTempList = new ArrayList<>();
        for (Product product : list) {
            if (product != null ) {
                // Filter by user name and user id
                if (containsIgnoreCase(product.getProductName(), charString)
                        ||
                        containsIgnoreCase(product.getProductDiscription(), charString)
                ) {
                    System.out.println("ПРОВЕРКА searchFollowersFilter true or false "+
                            containsIgnoreCase(product.getProductName(), charString));
                            filteredTempList.add(product);

                }
            }
        }
        return filteredTempList;
    }

    /**
     * Search if substring has char sequence in source string ignore case
     * @param src source string
     * @param charString substring for searching
     * @return true if has coincidence
     */
    public static boolean containsIgnoreCase(String src, String charString) {
        System.out.println("ПРОВЕРКА containsIgnoreCase + charString + src "+ charString + " "+src);
        final int length = charString.length();
        if (length == 0) {
            return true;
        }
        for (int i = src.length() - length; i >= 0; i--) {
            if (src.regionMatches(true, i, charString, 0, length)) {
                return true;
            }
        }

        return false;
    }


}
