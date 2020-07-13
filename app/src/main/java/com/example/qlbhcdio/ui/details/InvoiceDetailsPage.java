package com.example.qlbhcdio.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.model.Product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailsPage extends AppCompatActivity implements ViewInvoice.view {
    private String INVOICE_CODE = "MADH";
    private int ID_CODE = 0;
    private List<Product> mProducts = new ArrayList<>();
    private InvoicePresenter presenter;
    private RecyclerView recyclerViewProducts;
    private AdapterItemInvoice mAdapterInvoice;
    private TextView tv_name, tv_phone, tv_address, tv_date, tv_state, tv_sum, tv_method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_invoice_details);
        getIntentData();
        bindingIU();
        initViews();
        initData();
    }

    private void getIntentData() {
        if (getIntent().getExtras().get(INVOICE_CODE) != null) {
            ID_CODE = getIntent().getExtras().getInt(INVOICE_CODE);
        }
    }

    private void initData() {
        ID_CODE = (int) getIntent().getExtras().get(INVOICE_CODE);
        presenter = new InvoicePresenter(this);
        presenter.fetchDetails(ID_CODE);
        presenter.fetchInvoice(ID_CODE);
    }

    private void initViews() {
        initItemDetails();
    }

    private void initItemDetails() {
        mAdapterInvoice = new AdapterItemInvoice(this, mProducts);
        recyclerViewProducts.setAdapter(mAdapterInvoice);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewProducts.setLayoutManager(manager);
        recyclerViewProducts.setNestedScrollingEnabled(false);
        recyclerViewProducts.setHasFixedSize(false);
    }

    private void bindingIU() {
        recyclerViewProducts = findViewById(R.id.rcl_invoice);
        tv_name = findViewById(R.id.tv_nameUser_detail);
        tv_address = findViewById(R.id.tv_address_detail);
        tv_phone = findViewById(R.id.tv_numPhone_detail);
        tv_date = findViewById(R.id.tv_date_detail);
        tv_state = findViewById(R.id.tv_states);
        tv_sum = findViewById(R.id.tv_sum);
        tv_method = findViewById(R.id.tv_methodPayment);
    }

    @Override
    public void onGetError(String localizedMessage) {

    }

    @Override
    public void onFetchProductsSuccess(List<Product> products) {
        mAdapterInvoice.addItem(products);

    }

    @Override
    public void onFetchInvoiceSuccess(Invoice invoice) {
        try {
            setupProfile(invoice);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setupProfile(Invoice invoice) throws ParseException {
        runOnUiThread(() -> {
            tv_name.setText(invoice.getIdUser());
            tv_phone.setText(invoice.getPhone());
            tv_address.setText(invoice.getAddress());
            tv_sum.setText(String.valueOf(invoice.getPrice()));
            tv_method.setText(invoice.getMethod());
            tv_date.setText(invoice.getDate());
            setupStates(invoice);
        });

    }

    public void setupStates(Invoice invoice) {
        if (invoice.getState() == 1) {
            tv_state.setText("Đang xử lý");
            tv_state.setTextColor(Color.parseColor("#ff669900"));
        } else {
            tv_state.setText("Đã thanh toán");
        }

    }


}