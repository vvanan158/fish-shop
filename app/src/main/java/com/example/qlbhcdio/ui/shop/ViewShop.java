package com.example.qlbhcdio.ui.shop;

import com.example.qlbhcdio.model.Product;

import java.util.List;

  interface ViewShop {
    interface  Presenter {
        void UploadProductSell ();
        void UploadFavorite();
    }
    interface  View {
        void OnResultProducts(List<Product>product);
        void OnResultFavorites(List<Product>favorite);
    }
}
