package com.example.qlbhcdio.ui.product;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Product;
import com.squareup.picasso.Picasso;

public class ProductPage extends AppCompatActivity implements View.OnClickListener {
    private Product product;
    private ImageView mImage;
    private TextView mName, mContent, mCost;
    private ImageButton btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_product);
        getData();
        bindingIU();
        initViews();
        handleEvents();

    }

    private void handleEvents() {
        btnBack.setOnClickListener(this);
    }

    private void bindingIU() {
        mImage = findViewById(R.id.img_detail);
        mName = findViewById(R.id.tv_detail_name);
        mContent = findViewById(R.id.tv_detail_content);
        mCost = findViewById(R.id.tv_detail_cost);
        btnBack = findViewById(R.id.btn_product_back);
    }

    private void initViews() {
        initProduct();
    }

    private void initProduct() {
        Picasso.with(this)
                .load(product.getImage())
                .centerInside()
                .resize(getScreenWidth(), getScreenHeight())
                .into(mImage);
        mName.setText(product.getName());
        mContent.setText(product.getDescription());
        mCost.setText(String.valueOf(product.getMoney()));
    }

    private void getData() {
        product = (Product) getIntent().getExtras().getSerializable("product");
    }

    //getSize
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_product_back:  finish();
                break;
        }
    }
}
