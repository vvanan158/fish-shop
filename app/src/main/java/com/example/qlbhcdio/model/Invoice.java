package com.example.qlbhcdio.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {

    @SerializedName("MADH")
    @Expose
    private Integer id;
    @SerializedName("NGAY")
    @Expose
    private String date;

    @SerializedName("TRANGTHAI")
    @Expose
    private Integer state;

    @SerializedName("TENDN")
    @Expose
    private String idUser;

    @SerializedName("SDT")
    @Expose
    private String phone;

    @SerializedName("METHOD")
    @Expose
    private String method;

    @SerializedName("DIACHI")
    @Expose
    private String address;

    @SerializedName("TONGTIEN")
    @Expose
    private double price;

    public Invoice(Integer id, String date, Integer state, String idUser, String phone, String method, String address, double price) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.idUser = idUser;
        this.phone = phone;
        this.method = method;
        this.address = address;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}