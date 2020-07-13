package com.example.qlbhcdio.ui.register;


import com.example.qlbhcdio.model.MessengerRes;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;
import com.example.qlbhcdio.ui.register.ViewRegister;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterPresenter implements ViewRegister.presenter {

    private ViewRegister.view view;
    private CompositeDisposable compositeDisposable;
    public RegisterPresenter(ViewRegister.view view) {
        this.view = view;
    }

    @Override
    public boolean CheckRegister(String id, String password, String cpassword, String name, boolean cb) {
        if (id.isEmpty() || id.length() < 6) {
            view.onId("Tài khoảng không hợp lệ");
            return false;
        }
        if (password.isEmpty() || password.length() < 6) {
            view.onPassword("Mật khẩu phải >= 6 kí tự");
            return false;
        }
        if (cpassword.isEmpty() || cpassword.length() < 6) {
            view.onCPassword("Mật khẩu phải >= 6 kí tự");
            return false;
        }
        if (!cpassword.equals(password)) {
            view.onCPassword("Xác nhận mật khẩu sai");
            return false;
        }
        if (name.isEmpty() || name.length() < 6) {
            view.onEmail("Tên tài khoản quá ngắn");
            return false;
        }
        if (!cb) {
            view.onCb("Bạn chưa chấp nhận điều khoản!");
            return false;
        }
        return true;
    }


    @Override
    public void onRegister(String id, String password, String cpassword, String name, boolean cb) {
        if (CheckRegister(id, password, cpassword, name, cb)) {
            DataClient dataClient = API.getData();
            Disposable disposable = dataClient.Register(id, password, name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MessengerRes>() {
                        @Override
                        public void accept(MessengerRes messengerRes) throws Throwable {
                            view.onResult(messengerRes);
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
