package com.example.qlbhcdio.ui.history;

import com.example.qlbhcdio.model.Invoice;

import java.util.List;

public interface ViewHistory {
    interface  presenter {
        void fetchHistory();
        void dispose();
    }
    interface  view{
        void onResultHistory(List<Invoice> inv);
        void onFailHistory(String error);
    }
}
