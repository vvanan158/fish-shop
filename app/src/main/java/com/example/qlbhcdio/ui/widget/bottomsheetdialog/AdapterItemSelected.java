package com.example.qlbhcdio.ui.widget.bottomsheetdialog;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.Utils.LoadImageUtils;
import com.example.qlbhcdio.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterItemSelected extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> mProduct;
    private setOnClickItemCart onClickAdapterCart;

    public AdapterItemSelected(Context context, List<Product> mProduct, setOnClickItemCart onClickAdapterCart) {
        this.context = context;
        this.mProduct = mProduct;
        this.onClickAdapterCart = onClickAdapterCart;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fish_cart, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Product product = mProduct.get(position);
        ((ItemViewHolder) holder).tv_name.setText(product.getName());
        ((ItemViewHolder) holder).tv_cos.setText(String.valueOf(product.getMoney() * product.getAmount()));
        ((ItemViewHolder) holder).tv_amount.setText(String.valueOf(product.getAmount()));

        LoadImageUtils.LoadImageUrlCenterFit(mProduct.get(position).getImage(), ((ItemViewHolder) holder).img_view);

        ((ItemViewHolder) holder).btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdapterCart.onClickDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_cos, tv_amount;
        ImageView img_view;
        Button btn_delete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_delete = itemView.findViewById(R.id.btn_delete_cart);
            tv_name = itemView.findViewById(R.id.tv_nameFishCart);
            tv_cos = itemView.findViewById(R.id.tv_costCart);
            tv_amount = itemView.findViewById(R.id.tv_amount_cart);
            img_view = itemView.findViewById(R.id.img_fishCart);
        }
    }

    public interface setOnClickItemCart {
        void onClickDelete(int position);
    }

}
