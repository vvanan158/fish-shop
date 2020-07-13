package com.example.qlbhcdio.ui.details;

import android.content.Context;
import android.util.Log;
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

public class AdapterItemInvoice extends RecyclerView.Adapter<AdapterItemInvoice.ViewHolder> {

    private Context context;
    private List<Product> mProducts;

    public AdapterItemInvoice(Context context, List<Product> mProducts) {
        this.context = context;
        this.mProducts = mProducts;
    }

    @NonNull
    @Override
    public AdapterItemInvoice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemInvoice.ViewHolder holder, int position) {
        LoadImageUtils.LoadImageUrlCenterFit(mProducts.get(position).getImage(),holder.img_item);
        holder.tv_nameItem.setText(mProducts.get(position).getName());
        holder.tv_price.setText(String.valueOf(mProducts.get(position).getMoney()));
        holder.tv_amount.setText(String.valueOf(mProducts.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void addItem(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            mProducts.addAll(products);
            notifyItemChanged(i);
            break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_item;
        TextView tv_nameItem, tv_price, tv_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.img_itemDetail);
            tv_nameItem = itemView.findViewById(R.id.tv_name_itemDetail);
            tv_price = itemView.findViewById(R.id.tv_price_itemDetail);
            tv_amount = itemView.findViewById(R.id.tv_amount_itemDetail);
        }
    }
}
