package com.example.qlbhcdio.ui.account;

import com.example.qlbhcdio.model.MessengerRes;

public interface ViewAccount {

    interface presenter {
        boolean onCheckText(String name, String address, String numPhone);

        void updateAccount(String name, String address, String numPhone);

        void dispose();
    }

    interface View {

        void getError(String text);

        void onUpdate(MessengerRes messengerRes);

        void onName(String s);

        void onNumPhone(String s);

    }

}
