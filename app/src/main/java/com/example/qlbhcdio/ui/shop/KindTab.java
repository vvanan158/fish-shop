package com.example.qlbhcdio.ui.shop;

import android.telecom.StatusHints;

public enum KindTab {
    TAB_0("Tất cả", 0), TAB_1("Cá", 1), TAB_2("Tôm", 2), TAB_3("Cua", 3), TAB_4("Sò", 4), TAB_5("Mực", 5);
    private String name;
    private int id;

    KindTab(String name, int id) {
        this.name = name;
        this.id = id;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
