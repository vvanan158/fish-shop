package com.example.qlbhcdio.ui.details;

import android.util.Log;

import com.example.qlbhcdio.model.Detail;
import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InvoicePresenter implements ViewInvoice.presenter {

    private ViewInvoice.view view;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public InvoicePresenter(ViewInvoice.view view) {
        this.view = view;
    }

    @Override
    public void fetchInvoice(int id_code) {
        DataClient dataClient = API.getData();
        Disposable disposable = dataClient.getInvoice(id_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Invoice>() {
                    @Override
                    public void accept(Invoice invoice) throws Throwable {
                        view.onFetchInvoiceSuccess(invoice);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        fetchInvoice(id_code);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void fetchDetails(int id_code) {
        DataClient dataClient = API.getData();
        Disposable disposable = dataClient.getInvoiceDetailsByID(id_code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Product>>() {
                    @Override
                    public void accept(List<Product> products) throws Throwable {
                        view.onFetchProductsSuccess(products);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        fetchDetails(id_code);
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        this.view = null;
        mCompositeDisposable.clear();
    }

}
