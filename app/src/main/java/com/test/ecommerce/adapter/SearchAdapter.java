package com.test.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.test.ecommerce.Model.Product;
import com.test.ecommerce.R;
import com.test.ecommerce.databinding.SearchListItemBinding;

import java.text.DecimalFormat;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>  {

    private Context mContext;
    // Declare an arrayList for products
    private List<Product> productList;
    private List<Product> productListf;
    private Product currentProduct;



    // Create a final private SearchAdapterOnClickHandler called mClickHandler
    private SearchAdapterOnClickHandler clickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface SearchAdapterOnClickHandler {
        void onClick(Product product);
    }

    public SearchAdapter(Context mContext,List<Product> productList,SearchAdapterOnClickHandler clickHandler,
                         FragmentActivity activity) {
        this.mContext = mContext;
        this.productList = productList;
        this.productListf = productList;
        this.clickHandler = clickHandler;


    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        SearchListItemBinding searchListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.search_list_item,parent,false);
        return new SearchViewHolder(searchListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        currentProduct = productListf.get(position);
        holder.binding.txtProductName.setText(currentProduct.getProductName());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedPrice = formatter.format(currentProduct.getProductPrice());
        holder.binding.txtProductPrice.setText(formattedPrice + " Р");
        String supplier = currentProduct.getProductSupplier();
        holder.binding.txtProductSupplier.setText(supplier);
        // Load the Product image into ImageView
        String imageUrl = currentProduct.getProductImage().replaceAll("\\\\", "/");
        Glide.with(mContext)
                .load(imageUrl)
                .into(holder.binding.imgProductImage);

        // If product is added to cart
        if (currentProduct.isInCart()==false) {
            holder.binding.imgCart.setImageResource(R.drawable.ic_shopping_cart_green);
        }
    }

    @Override
    public int getItemCount() {
        if (productList == null) {
            return 0;
        }
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void clear() {
        int size = productListf.size();
        productListf.clear();
        notifyItemRangeRemoved(0, size);
    }


    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Create view instances
        private final SearchListItemBinding binding;

        private SearchViewHolder(SearchListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // Register a callback to be invoked when this view is clicked.
            itemView.setOnClickListener(this);

            binding.imgCart.setOnClickListener(this);
            binding.addToCart.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            // Get position of the product
            currentProduct = productListf.get(position);
            switch (v.getId()) {
                case R.id.card_view:
                    // Send product through click
                    clickHandler.onClick(currentProduct);

                    break;


                case R.id.imgCart:
                  //  toggleProductsInCart();
                    break;

            }

        }
        }



}
