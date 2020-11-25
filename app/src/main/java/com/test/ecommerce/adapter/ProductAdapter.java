package com.test.ecommerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.test.ecommerce.Activity.MainActivity;
import com.test.ecommerce.Model.Product;
import com.test.ecommerce.R;
import com.test.ecommerce.databinding.ProductListItemBinding;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


public class ProductAdapter extends PagedListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private Context mContext;

    public static Product product;
    public static MainActivity mainActivity;

    // Create a final private MovieAdapterOnClickHandler called mClickHandler

    private ProductAdapterOnClickHandler clickHandler;

     // The interface that receives onClick messages.

    public interface ProductAdapterOnClickHandler {
        void onClick(Product product);

    }

    public ProductAdapter(Context mContext, ProductAdapterOnClickHandler clickHandler) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
        this.clickHandler = clickHandler;

            mainActivity = new MainActivity();
    }

    @NonNull

    @Override

    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        ProductListItemBinding productListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.product_list_item, parent, false);

        return new ProductViewHolder(productListItemBinding);

    }

    @Override

    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        product = getItem(position);

        if (product != null) {

            String productName = product.getProductName();

            holder.binding.txtProductName.setText(productName);

            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String formattedPrice = formatter.format(product.getProductPrice());
            holder.binding.txtProductPrice.setText(formattedPrice + " P");

           String productCategory = product.getProductCategory();
         holder.binding.txtProductCategories.setText(productCategory);
            String productProducer = product.getProductSupplier();
            holder.binding.txtProductProducer.setText(productProducer);


            // Load the Product image into ImageView

          //  String imageUrl = LOCALHOST + product.getProductImage().replaceAll("\\\\", "/");
            String imageUrl = product.getProductImage().replaceAll("\\\\", "/");

            Glide.with(mContext)
                    .load(imageUrl)
                    .into(holder.binding.imgProductImage);

            Log.d("imageUrl", imageUrl);

            // If product is added to cart
            int quantity = product.getProductQuantity();

            if (quantity==0) {

                holder.binding.imgCart.setImageResource(R.drawable.ic_shopping_cart_green);
            }
        } else {
            holder.binding.imgCart.setImageResource(R.drawable.ic_shopping_cart_green);
            holder.binding.txtCartQuntity.setText("");
          //  Toast.makeText(mContext, "Product is null", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;

    }

    @Override

    public PagedList<Product> getCurrentList() {
        return super.getCurrentList();
    }

    public Product getProductAt(int position) {
        return getItem(position);
    }

    // It determine if two list objects are the same or not

    private static DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {

        @Override

        public boolean areItemsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {

            return oldProduct.getProductName().equals(newProduct.getProductName());

        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Product oldProduct, @NonNull Product newProduct) {
            return oldProduct.equals(newProduct);

        }

    };



    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Create view instances
        private final ProductListItemBinding binding;

        private ProductViewHolder(ProductListItemBinding binding) {

            super(binding.getRoot());

            this.binding = binding;

            // Register a callback to be invoked when this view is clicked.
            itemView.setOnClickListener(this);

            binding.imgCart.setOnClickListener(this);

        }

        @Override

        public void onClick(View v) {

            int position = getAdapterPosition();

            // Get position of the product

            product = getItem(position);

            switch (v.getId()) {
                case R.id.card_view:
                    // Send product through click
                    clickHandler.onClick(product);
                   // insertProductToHistory();
                    break;


                case R.id.imgCart:
                    toggleProductsInCart();
                    break;

            }

        }


        private void toggleProductsInCart() {

            // If Product is not added to cart

            if (product.isInCart()==false) {

               int q = product.getProductQuantity();
                binding.imgCart.setImageResource(R.drawable.ic_add_shopping_cart);
                binding.txtCartQuntity.setText(" -  "+q+"  + ");
                showSnackBar("Added To Cart");
                product.setIsInCart(true);

            } else {
                binding.imgCart.setImageResource(R.drawable.ic_shopping_cart_green);
                product.setIsInCart(false);
                notifyDataSetChanged();
                showSnackBar("Removed From Cart");

            }

        }


        private void showSnackBar(String text) {

            Snackbar.make(itemView, text, Snackbar.LENGTH_SHORT).show();

        }
    }

}
