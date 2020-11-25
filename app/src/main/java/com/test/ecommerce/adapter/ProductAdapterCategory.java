package com.test.ecommerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.ecommerce.R;
import com.test.ecommerce.Model.SubCategory;
import com.test.ecommerce.databinding.ProductListItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapterCategory extends PagedListAdapter<SubCategory, ProductAdapterCategory.ProductViewHolder> {
    private Context mContext;

    public static SubCategory subCategory;


    public interface ProductAdapterOnClickHandler {
        void onClick(SubCategory product);

    }

    public ProductAdapterCategory(Context mContext
                                  //ProductAdapterCategory.ProductAdapterOnClickHandler clickHandler
    ) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;

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

    public void onBindViewHolder(@NonNull ProductAdapterCategory.ProductViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        subCategory = getItem(position);
        System.out.println("ПРОВЕРКА АДАПТЕРА SubCategory()" );
        if (subCategory != null) {

        /*    String productCategory = subCategory.getTitleCategory()+" "+subCategory.getCategoryId();
                    //+subCategory.getParentId();
            holder.binding.txtProductCategories.setText(productCategory);
            System.out.println("ПРОВЕРКА АДАПТЕРА SubCategory()String productCategory " +productCategory);

         */
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
    class ProductViewHolder extends RecyclerView.ViewHolder {

        // Create view instances
        private final ProductListItemBinding binding;

        private ProductViewHolder(ProductListItemBinding binding) {

            super(binding.getRoot());

            this.binding = binding;

        }}


    @Override

    public PagedList<SubCategory> getCurrentList() {
        return super.getCurrentList();
    }



    public SubCategory getProductAt(int position) {
        return getItem(position);
    }


    public void notifyOnInsertedItem(int position) {
        notifyItemInserted(position);
        notifyItemRangeInserted(position, getCurrentList().size()-1);
        notifyDataSetChanged();

    }



    // It determine if two list objects are the same or not

    private static DiffUtil.ItemCallback<SubCategory> DIFF_CALLBACK = new DiffUtil.ItemCallback<SubCategory>() {

        @Override

        public boolean areItemsTheSame(@NonNull SubCategory oldProduct, @NonNull SubCategory newProduct) {

            return oldProduct.getId().equals(newProduct.getId());

        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull SubCategory oldProduct, @NonNull SubCategory newProduct) {
            return oldProduct.equals(newProduct);

        }

    };







}
