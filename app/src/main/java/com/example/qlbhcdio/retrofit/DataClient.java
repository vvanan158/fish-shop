package com.example.qlbhcdio.retrofit;


import com.example.qlbhcdio.model.Detail;
import com.example.qlbhcdio.model.MessengerRes;
import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DataClient {

    @FormUrlEncoded
    @POST("login")
    Observable<User> LoginUser(@Field("id") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("Register")
    Observable<MessengerRes> Register(@Field("id") String id,
                                @Field("password") String password,
                                @Field("name") String name);
    @GET("products")
    Observable<List<Product>> getProductRx();

    @FormUrlEncoded
    @POST("detail/new")
    Observable<MessengerRes> newDetails(@Field("tendn") String name,
                                  @Field("ngay") String Date,
                                  @Field("diachi") String address,
                                  @Field("method") String method,
                                  @Field("sdt") String numPhone,
                                  @Field("data") String data);

    @FormUrlEncoded
    @POST("invoices")
    Observable<List<Invoice>> getInvoice(@Field("tendn") String idUser,
                                         @Field("ngay") String date);

    @GET("favorite")
    Observable<List<Product>> getFavoriteRx();


    @FormUrlEncoded
    @PUT("user/update")
    Observable<MessengerRes> updateUser(@Field("TENDN") String id,
                                  @Field("TENKH")String name,
                                  @Field("DIACHI") String address,
                                  @Field("SDT") String numPhone);

    @FormUrlEncoded
    @POST("invoice/details/id")
    Observable<List<Product>> getInvoiceDetailsByID(@Field("MADH") int id) ;

    @FormUrlEncoded
    @POST("invoice/id")
    Observable<Invoice> getInvoice(@Field("MADH") int id) ;



}
