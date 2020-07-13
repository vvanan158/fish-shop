package com.example.qlbhcdio.ui.shop;


import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


class PresenterShop implements ViewShop.Presenter {
    DataClient dataClient = API.getData();
    DataClient dataClient2 = API.getData();
    ViewShop.View view;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public PresenterShop(ViewShop.View view) {
        this.view = view;
    }

    @Override
    public void UploadProductSell() {
        Disposable disposable = dataClient.getProductRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Throwable {
                        view.OnResultProducts(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        mCompositeDisposable.add(disposable);
    }


    @Override
    public void UploadFavorite() {
        Disposable disposable = dataClient2.getFavoriteRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Throwable {
                        view.OnResultFavorites(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void Dispose() {
        mCompositeDisposable.clear();
    }

}
