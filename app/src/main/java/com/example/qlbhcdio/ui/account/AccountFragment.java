package com.example.qlbhcdio.ui.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.MessengerRes;
import com.example.qlbhcdio.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class AccountFragment extends Fragment implements View.OnClickListener,
        ViewAccount.View {

    private AccountPresenter presenter;
    private TextInputEditText mPassword,
            mAddress,
            mNumPhone,
            mNameUser;
    private Button btnEdit, btnSave;


    public AccountFragment() {

    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);
        bindingIU(view);
        initViews();
        initData();
        handleEvents();
        return view;
    }

    private void handleEvents() {
        btnSave.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
    }

    private void initData() {
        presenter = new AccountPresenter(this);
        setUpUser();
    }

    private void initViews() {
    }

    private void bindingIU(View view) {
        mAddress = view.findViewById(R.id.edt_address);
        mPassword = view.findViewById(R.id.edt_password);
        mNumPhone = view.findViewById(R.id.edt_numPhone);
        mNameUser = view.findViewById(R.id.edt_name);
        btnEdit = view.findViewById(R.id.btn_edit);
        btnSave = view.findViewById(R.id.btn_save);
    }

    private void setUpUser() {
        User user = Authentication.getInstance().getUserCurrent();
        mNameUser.setText(user.getName());
        mNumPhone.setText(user.getNumPhone());
        mPassword.setText(user.getPassword());
        mAddress.setText(user.getAddress());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                onEditAndSave(true);
                break;

            case R.id.btn_save:
                onEditAndSave(false);
                onSave();
                break;
        }
    }

    private void onSave() {
        presenter.updateAccount(mNameUser.getText().toString(),
                mAddress.getText().toString(),
                mNumPhone.getText().toString());
    }

    void onEditAndSave(boolean b) {
        mNumPhone.setEnabled(b);
        mNameUser.setEnabled(b);
        mAddress.setEnabled(b);
        if (b) {
            btnSave.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
        } else {
            btnSave.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getError(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdate(MessengerRes messengerRes) {
        setUpUser();
        Toast.makeText(getContext(), messengerRes.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onName(String s) {
        mNameUser.setError(s);
    }

    @Override
    public void onNumPhone(String s) {
        mNumPhone.setError(s);
    }
}