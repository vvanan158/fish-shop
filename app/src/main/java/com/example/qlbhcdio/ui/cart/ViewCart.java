package com.example.qlbhcdio.ui.cart;

import com.example.qlbhcdio.model.Product;

import java.util.List;

public interface ViewCart {
    interface presenter {
        void OnPayment(String json);

        double SumPriceDetails(List<Product> mProducts);

        void dispose();
    }

    interface view {
        void onSuccess(String s);

        void onFailed(String s);
    }

}
