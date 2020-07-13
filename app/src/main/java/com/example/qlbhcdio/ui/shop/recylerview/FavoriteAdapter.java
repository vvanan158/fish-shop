package com.example.qlbhcdio.ui.shop.recylerview;

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


public class FavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> products;

    public FavoriteAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).tv_name.setText(products.get(position).getName());
        LoadImageUtils.LoadImageUrlCenterFit(products.get(position).getImage(), ((ItemViewHolder) holder).image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_product_fav);
            tv_name = itemView.findViewById(R.id.tv_title_fav);
        }
    }


}
