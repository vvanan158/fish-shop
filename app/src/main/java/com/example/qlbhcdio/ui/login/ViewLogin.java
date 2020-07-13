package com.example.qlbhcdio.ui.login;

public interface ViewLogin {

    interface presenter {

        void dispose();
        boolean isLogin(String id, String password);
        void LoginAccount(String id, String password);
    }

    interface View {
        void onId(String id);
        void onPassword(String password);
        void onSuccess(String s);
        void onFailed(String s);
    }

}
