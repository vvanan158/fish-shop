package com.example.qlbhcdio.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    @SerializedName("MAHANG")
    @Expose
    private Integer id;
    @SerializedName("TENHANG")
    @Expose
    private String name;
    @SerializedName("DONGIA")
    @Expose
    private double money = 0 ;
    @SerializedName("HINHANH")
    @Expose
    private String  image;
    @SerializedName("MOTA")
    @Expose
    private String description;
    @SerializedName("MALOAI")
    @Expose
    private Integer idParent;
    @SerializedName("SOLUONG")
    @Expose
    private Integer amount ;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public Product(Integer id, String name, double money, String image, String description, Integer idParent) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.image = image;
        this.description = description;
        this.idParent = idParent;
    }

    public Product(Integer id, String name,  String description,double money) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.description = description;
    }

}
