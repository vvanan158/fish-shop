package com.example.qlbhcdio.ui.details;

import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.model.Product;

import java.util.List;

public interface ViewInvoice {
    interface presenter {
        void fetchInvoice(int id_code);
        void fetchDetails(int id_code);
    }

    interface view {
        void onGetError(String localizedMessage);
        void onFetchProductsSuccess(List<Product> products);
        void onFetchInvoiceSuccess(Invoice invoice);
    }
}
