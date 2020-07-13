package com.example.qlbhcdio.ui.login;

import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.User;
import com.example.qlbhcdio.retrofit.DataClient;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.ui.login.ViewLogin;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ViewLogin.presenter {

    private ViewLogin.View view;
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(ViewLogin.View view) {
        this.view = view;
    }

    @Override
    public boolean isLogin(String id, String password) {
        if (id.isEmpty() || id.length() < 6) {
            view.onId("Tài khoảng không hợp lệ");
            return false;
        }
        if (password.isEmpty() || password.length() < 6) {
            view.onPassword("Mật khẩu phải >= 6 kí tự");
            return false;
        }
        return true;
    }

    @Override
    public void LoginAccount(String id, String password) {
        if (isLogin(id, password)) {
            DataClient dataClient = API.getData();
            Disposable disposable = dataClient.LoginUser(id, password).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<User>() {
                        @Override
                        public void accept(User user) throws Throwable {
                            if (user != null) {
                                Authentication.getInstance().setAuth(user);
                                view.onSuccess("Login Success");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            view.onFailed(throwable.getLocalizedMessage());
                        }
                    });
            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }
}
