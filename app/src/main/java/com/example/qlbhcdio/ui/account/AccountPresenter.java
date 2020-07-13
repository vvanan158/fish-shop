package com.example.qlbhcdio.ui.account;

import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.MessengerRes;
import com.example.qlbhcdio.model.User;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountPresenter implements ViewAccount.presenter {

    private ViewAccount.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AccountPresenter(ViewAccount.View view) {
        this.view = view;
    }

    @Override
    public boolean onCheckText(String name, String address, String numPhone) {
        if (name.isEmpty() || name.length() < 6) {
            view.onName("Tên không hợp lệ");
            return false;
        }
        if (numPhone.length() < 10) {
            view.onNumPhone("số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }

    @Override
    public void updateAccount(String name, String address, String numPhone) {
        User user = Authentication.getInstance().getUserCurrent();
        if (onCheckText(name, address, numPhone)) {
            DataClient dataClient = API.getData();
            Disposable disposable = dataClient.updateUser(user.getId(), name, address, numPhone).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MessengerRes>() {
                        @Override
                        public void accept(MessengerRes messengerRes) throws Throwable {
                            Authentication.getInstance().setName(name);
                            Authentication.getInstance().setAddress(address);
                            Authentication.getInstance().setPhone(numPhone);
                            view.onUpdate(messengerRes);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            view.getError(throwable.getLocalizedMessage());
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