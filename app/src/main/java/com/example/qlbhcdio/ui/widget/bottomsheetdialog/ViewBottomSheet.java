package com.example.qlbhcdio.ui.widget.bottomsheetdialog;

import com.example.qlbhcdio.model.Product;

import java.util.List;

public interface ViewBottomSheet {
    interface presenter {
        void SumPriceDetailCart(List<Product> mProducts);
    }

    interface view {
        void onResultSum(double result);
    }
}
