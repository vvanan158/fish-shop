package com.example.qlbhcdio.ui.widget.bottomsheetdialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.ui.cart.CartPage;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BottomSheetCart extends BottomSheetDialogFragment implements AdapterItemSelected.setOnClickItemCart,
        View.OnClickListener,
        ViewBottomSheet.view {

    public static final String TAG_BOTTOM_SHEET_CART = "BottomSheetCart";
    private RecyclerView recyclerView_Cart;
    private List<Product> mCart = new ArrayList<>();
    private AdapterItemSelected mAdapter;
    private Button btn_delivery;
    private TextView tv_sumMoney;
    private BottomSheetPresenter presenter = new BottomSheetPresenter(this);
    private HashMap<Integer, Product> hashMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottomsheet, container, false);
        bindingIU(view);
        initViews();
        initData();
        handleEvents();
        return view;
    }

    private void handleEvents() {
        btn_delivery.setOnClickListener(this);
    }

    private void initData() {
        presenter.SumPriceDetailCart(mCart);
        uploadData();
    }

    private void initViews() {
        initListCart();
    }


    private void initListCart() {
        mAdapter = new AdapterItemSelected(getContext(), mCart, this);
        recyclerView_Cart.setAdapter(mAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView_Cart.setLayoutManager(manager);
    }

    private void bindingIU(View view) {
        recyclerView_Cart = view.findViewById(R.id.rcl_cart);
        btn_delivery = view.findViewById(R.id.btn_delivery);
        tv_sumMoney = view.findViewById(R.id.tv_cart_sumMoney);
    }

    private void uploadData() {
        mCart.clear();
        mCart.addAll(hashMap.values());
        mAdapter.notifyDataSetChanged();
    }

    public void setOnDataListener(String jsonProduct) {
        Product product = new Gson().fromJson(jsonProduct, Product.class);
        int key = product.getId();
        if (hashMap.containsKey(key)) {
            hashMap.get(key).setAmount(hashMap.get(key).getAmount() + 1);
        } else {
            hashMap.put(key, product);
        }
    }

    @Override
    public void onClickDelete(int position) {
        hashMap.remove(mCart.get(position).getId());
        mCart.remove(position);
        mAdapter.notifyDataSetChanged();
        presenter.SumPriceDetailCart(mCart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delivery:
                if (mCart.size() > 0) goActivity(CartPage.class);
        }
    }

    void goActivity(Class t) {
        Intent intent = new Intent(getContext(), t);
        String json = new Gson().toJson(mCart);
        intent.putExtra("detail", json);
        dismiss();
        startActivity(intent);
    }

    @Override
    public void onResultSum(double result) {
        tv_sumMoney.post(new Runnable() {
            @Override
            public void run() {
                tv_sumMoney.setText(String.valueOf(result));
            }
        });
    }
}
