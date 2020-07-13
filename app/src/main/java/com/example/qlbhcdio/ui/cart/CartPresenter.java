package com.example.qlbhcdio.ui.cart;

import com.example.qlbhcdio.Utils.TimeFormatUtils;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.MessengerRes;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartPresenter implements ViewCart.presenter {
    private ViewCart.view view;
    private CompositeDisposable compositeDisposable;

    public CartPresenter(ViewCart.view view) {
        this.view = view;
    }

    public static String METHOD ;


    @Override
    public void OnPayment(String json) {
        DataClient dataClient = API.getData();
        Disposable disposable = dataClient.newDetails(Authentication.getInstance().getUserCurrent().getId()
                , TimeFormatUtils.DateTimeCurrent()
                , Authentication.getInstance().getUserCurrent().getAddress()
                , (METHOD != null) ? METHOD : CartPage.METHOD_PAYMENT.METHOD_MONEY.getName()
                , Authentication.getInstance().getUserCurrent().getNumPhone()
                , json).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessengerRes>() {
                    @Override
                    public void accept(MessengerRes messengerRes) throws Throwable {
                        view.onSuccess(messengerRes.getMessage());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        view.onFailed(throwable.getLocalizedMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public double SumPriceDetails(List<Product> mProducts) {
        double sum = 0;
        for (Product product : mProducts) {
            sum += product.getAmount() * product.getMoney();
        }
        return sum;
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }
}
