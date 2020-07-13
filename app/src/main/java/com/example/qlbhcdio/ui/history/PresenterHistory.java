package com.example.qlbhcdio.ui.history;


import android.util.Log;

import com.example.qlbhcdio.Utils.TimeFormatUtils;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PresenterHistory implements ViewHistory.presenter {

    private ViewHistory.view view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PresenterHistory(ViewHistory.view view) {
        this.view = view;
    }

    @Override
    public void fetchHistory() {
        DataClient dataClient = API.getData();

      Disposable disposable = dataClient.getInvoice(Authentication.getInstance().getUserCurrent().getId(),TimeFormatUtils.DateTimeCurrent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Invoice>>() {
                    @Override
                    public void accept(List<Invoice> invoices) throws Throwable {
                        view.onResultHistory(invoices);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                         view.onFailHistory(throwable.getLocalizedMessage());
                    }
                });
      compositeDisposable.add(disposable);
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }


}
