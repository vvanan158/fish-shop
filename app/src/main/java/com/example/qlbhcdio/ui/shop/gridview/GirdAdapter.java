package com.example.qlbhcdio.ui.shop.gridview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.Utils.LoadImageUtils;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.ui.product.ProductPage;

import java.util.List;


public class GirdAdapter extends BaseAdapter {

    private List<Product> mProducts;
    private Context context;
    private SelectedItem selectedItem;

    public GirdAdapter(Context context, SelectedItem selectedItem, List<Product> mProducts) {
        this.context = context;
        this.selectedItem = selectedItem;
        this.mProducts = mProducts;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Product getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girdview, parent, false);
            viewHolder.image = convertView.findViewById(R.id.img_item_product);
            viewHolder.title = convertView.findViewById(R.id.tv_title);
            viewHolder.cost = convertView.findViewById(R.id.tv_cost);
            viewHolder.cardView = convertView.findViewById(R.id.CardView_fish);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = mProducts.get(position);
        viewHolder.title.setText(product.getName());
        viewHolder.cost.setText(String.valueOf(product.getMoney()));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem.onSelect(position);
            }
        });

        LoadImageUtils.LoadImageUrlCenterFit(product.getImage(), viewHolder.image);

        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, ProductPage.class);
                intent.putExtra("product", mProducts.get(position));
                context.startActivity(intent);
                return true;
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView title;
        TextView cost;
        CardView cardView;
    }

    public interface SelectedItem {
        void onSelect(int position);
    }

}
