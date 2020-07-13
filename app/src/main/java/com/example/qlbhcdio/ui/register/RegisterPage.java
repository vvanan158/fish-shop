package com.example.qlbhcdio.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.MessengerRes;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener, ViewRegister.view {
    private EditText edtID, edtPassword, edtCPassword, edtEmail;
    private CheckBox terms;
    private Button btnRegister;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        BindingIU();
        initViews();

    }

    private void initViews() {
        initButton();
        initData();
    }

    private void initData() {
        presenter = new RegisterPresenter(this);
    }

    private void initButton() {
        btnRegister.setOnClickListener(this);
    }

    private void BindingIU() {
        edtID = findViewById(R.id.edt_register_id);
        edtPassword = findViewById(R.id.edt_register_password);
        edtCPassword = findViewById(R.id.edt_register_comfirmPassword);
        edtEmail = findViewById(R.id.edt_register_name);
        terms = findViewById(R.id.cb_register_policy);
        btnRegister = findViewById(R.id.btn_register);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Register();
                break;
        }
    }

    void Register() {
        presenter.onRegister(edtID.getText().toString()
                , edtPassword.getText().toString()
                , edtCPassword.getText().toString()
                , edtEmail.getText().toString()
                , terms.isChecked());
    }

    @Override
    public void onId(String s) {
        edtID.setError(s);
    }

    @Override
    public void onPassword(String s) {
        edtPassword.setError(s);
    }

    @Override
    public void onCPassword(String s) {
        edtCPassword.setError(s);
    }

    @Override
    public void onEmail(String s) {
        edtEmail.setError(s);
    }

    @Override
    public void onCb(String s) {
        Toast.makeText(RegisterPage.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResult(MessengerRes messengerRes) {
        finish();
        Toast.makeText(this, messengerRes.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
