package com.example.qlbhcdio.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.Utils.LoadImageUtils;
import com.example.qlbhcdio.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDetailCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> mProduct;


    public AdapterDetailCart(Context context, List<Product> mProduct) {
        this.context = context;
        this.mProduct = mProduct;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_product, parent, false);
        return new AdapterDetailCart.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Product product = mProduct.get(position);
        ((AdapterDetailCart.ItemViewHolder) holder).tv_name.setText(product.getName());
        ((AdapterDetailCart.ItemViewHolder) holder).tv_cos.setText(String.valueOf(product.getMoney() * product.getAmount()));
        ((AdapterDetailCart.ItemViewHolder) holder).tv_amount.setText(String.valueOf(product.getAmount()));
        LoadImageUtils.LoadImageUrlCenterFit(mProduct.get(position).getName(), ((ItemViewHolder) holder).img_view);
    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_cos, tv_amount;
        ImageView img_view;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_itemDetail);
            tv_cos = itemView.findViewById(R.id.tv_price_itemDetail);
            tv_amount = itemView.findViewById(R.id.tv_amount_itemDetail);
            img_view = itemView.findViewById(R.id.img_itemDetail);
        }
    }


}
