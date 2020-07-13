package com.example.qlbhcdio.ui.register;

import com.example.qlbhcdio.model.MessengerRes;

public interface ViewRegister {

    interface presenter {
        boolean CheckRegister(String id, String password, String cpassword, String name, boolean cb);

        void onRegister(String id, String password, String cpassword, String name, boolean cb);

        void dispose();
    }


    interface view {
        void onResult(MessengerRes messengerRes);

        void onFailed(String s);

        void onId(String s);

        void onPassword(String s);

        void onCPassword(String s);

        void onEmail(String s);

        void onCb(String s);
    }
}
