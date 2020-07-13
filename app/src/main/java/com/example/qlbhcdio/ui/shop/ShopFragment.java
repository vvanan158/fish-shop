package com.example.qlbhcdio.ui.shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.ui.shop.gridview.GirdAdapter;
import com.example.qlbhcdio.ui.shop.recylerview.FavoriteAdapter;
import com.example.qlbhcdio.ui.shop.recylerview.KindAdapter;
import com.example.qlbhcdio.ui.widget.girdview.MyGirdView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements GirdAdapter.SelectedItem, ViewShop.View, KindAdapter.OnSelectOfKind {


    private RecyclerView mKindFish;
    private RecyclerView mFavorite;
    private MyGirdView mGirdView;
    private KindAdapter mKindAdapter;
    private FavoriteAdapter mFavoriteAdapter;
    private GirdAdapter mGirdAdapter;
    private SendItemToGirdAdapter sendItemToGirdAdapter;
    private List<Product> products;
    private List<Product> fav;
    private PresenterShop presenter = new PresenterShop(this);

    public ShopFragment(SendItemToGirdAdapter sendItemToGirdAdapter) {
        this.sendItemToGirdAdapter = sendItemToGirdAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_fragment, container, false);
        bindingIU(view);
        initViews();
        return view;
    }

    private void initViews() {
        initKindList();
        initFavorite();
        initProducts();
        initData();
    }

    private void initData() {
        presenter.UploadProductSell();
        presenter.UploadFavorite();
    }

    private void initProducts() {
        mGirdAdapter = new GirdAdapter(getContext(), this, products);
        mGirdView.post(() -> mGirdView.setAdapter(mGirdAdapter));
        mGirdAdapter.notifyDataSetChanged();
    }

    private void initFavorite() {
        mFavoriteAdapter = new FavoriteAdapter(getContext(), fav);
        mFavorite.post(() -> mFavorite.setAdapter(mFavoriteAdapter));
        RecyclerView.LayoutManager mLayoutManagerFavorite = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true);
        mFavorite.setLayoutManager(mLayoutManagerFavorite);
        mFavoriteAdapter.notifyDataSetChanged();
    }

    private void initKindList() {
        mKindAdapter = new KindAdapter(getContext(),this);
        mKindFish.setAdapter(mKindAdapter);
        RecyclerView.LayoutManager mLayoutManagerKindFish = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mKindFish.setLayoutManager(mLayoutManagerKindFish);

    }

    private void bindingIU(View view) {
        mKindFish = view.findViewById(R.id.rcl_kindfish);
        mFavorite = view.findViewById(R.id.rcl_favorite);
        mGirdView = view.findViewById(R.id.gird_sellFish);
        products = new ArrayList<>();
        fav = new ArrayList<>();
    }

    @Override
    public void onSelect(int position) {
        String jsonProduct = new Gson().toJson(mGirdAdapter.getItem(position));
        sendItemToGirdAdapter.OnSend(jsonProduct);
    }


    @Override
    public void OnResultProducts(List<Product> product) {
        products = product;
        initProducts();
    }

    @Override
    public void OnResultFavorites(List<Product> favorite) {
        fav = favorite;
        initFavorite();
    }
    // select kind
    @Override
    public void indexSelect(int i) {

    }

    // gửi item tới girdViewAdapter
    public interface SendItemToGirdAdapter {
        void OnSend(String jsonProduct);
    }

    @Override
    public void onDestroy() {
        presenter.Dispose();
        super.onDestroy();
    }

}