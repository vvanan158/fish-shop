package com.example.qlbhcdio.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.Utils.TimeFormatUtils;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.Product;
import com.example.qlbhcdio.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends AppCompatActivity implements View.OnClickListener,
        ViewCart.view,
        RadioGroup.OnCheckedChangeListener {

    private TextView tv_userName,
            tv_userAddress,
            tv_numPhone,
            tv_cost,
            tv_date;
    private Button btn_payment;
    private ImageButton btn_back;
    private RecyclerView rcl_details;
    private AdapterDetailCart mDetail;
    private List<Product> mProducts = new ArrayList<>();
    private String Data;
    private CartPresenter cartPresenter;
    private RadioGroup rgMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cart_detail);
        bindingIU();
        initViews();
        initData();
        handleEvents();
    }

    private void handleEvents() {
        rgMethod.setOnCheckedChangeListener(this);
    }

    private void getDetails() {
        if (getIntent().getExtras().getString("detail") != null) {
            Data = getIntent().getExtras().getString("detail");
            Type productListType = new TypeToken<List<Product>>() {
            }.getType();
            mProducts = new Gson().fromJson(Data, productListType);
        }
    }

    private void initViews() {
        initItemCart();
    }

    private void initItemCart() {
        getDetails();
        mDetail = new AdapterDetailCart(this, mProducts);
        rcl_details.setAdapter(mDetail);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcl_details.setLayoutManager(manager);
    }

    private void bindingIU() {
        btn_payment = findViewById(R.id.btn_payment);
        tv_userName = findViewById(R.id.tv_nameUser_detail);
        tv_userAddress = findViewById(R.id.tv_address_detail);
        tv_numPhone = findViewById(R.id.tv_numPhone_detail);
        btn_back = findViewById(R.id.btn_detail_back);
        tv_cost = findViewById(R.id.tv_detail_sumMoney);
        tv_date = findViewById(R.id.tv_date_detail);
        rcl_details = findViewById(R.id.rcl_details);
        rgMethod = findViewById(R.id.radio_method);
    }

    private void initData() {
        cartPresenter = new CartPresenter(this);
        setUpDetailsCart();

    }

    private void setUpDetailsCart() {
        User user = Authentication.getInstance().getUserCurrent();
        if (user != null) {
            tv_userName.setText(user.getName());
            tv_numPhone.setText(user.getNumPhone());
            tv_userAddress.setText(user.getAddress());
            tv_cost.setText(String.valueOf(cartPresenter.SumPriceDetails(mProducts)));
            tv_date.setText("Ngày : " + TimeFormatUtils.DateTimeCurrent());
            btn_payment.setOnClickListener(this);
            btn_back.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_payment:
                cartPresenter.OnPayment(Data);
                break;
            case R.id.btn_detail_back:
                finish();
                break;
        }
    }

    @Override
    public void onSuccess(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rd_money:
                CartPresenter.METHOD = METHOD_PAYMENT.METHOD_MONEY.getName();
                break;
            case R.id.rb_DigitalWallet:
                CartPresenter.METHOD = METHOD_PAYMENT.METHOD_DIGITAL_WALLET.getName();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        cartPresenter.dispose();
        super.onDestroy();
    }

    enum METHOD_PAYMENT {
        METHOD_MONEY(1, "Tiền mặt"),
        METHOD_DIGITAL_WALLET(2, "Ví điện tử");

        private int id;
        private String name;

        METHOD_PAYMENT(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}