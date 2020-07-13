package com.example.qlbhcdio.ui.widget.bottomsheetdialog;

import com.example.qlbhcdio.model.Product;

import java.util.List;

public class BottomSheetPresenter implements ViewBottomSheet.presenter {
    private ViewBottomSheet.view view;

    public BottomSheetPresenter(ViewBottomSheet.view view) {
        this.view = view;
    }


    @Override
    public void SumPriceDetailCart(List<Product> mProducts) {
        double sum = 0;
        for (Product product : mProducts) {
            sum += product.getAmount() * product.getMoney();
        }
        view.onResultSum(sum);
    }
}
