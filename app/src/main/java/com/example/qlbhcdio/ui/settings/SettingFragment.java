package com.example.qlbhcdio.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlbhcdio.R;

public class SettingFragment extends Fragment implements SettingAdapter.NavigatorToActivity {

    private SettingViewModel mViewModel;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    private RecyclerView recyclerViewSetting;
    private SettingAdapter settingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        bindingIU(view);
        initViews();
        return view;
    }

    private void initViews() {
        initSettingFunction();
    }

    private void initSettingFunction() {
        settingAdapter = new SettingAdapter(getContext(), this);
        recyclerViewSetting.setAdapter(settingAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewSetting.setLayoutManager(manager);
    }

    private void bindingIU(View view) {
        recyclerViewSetting = view.findViewById(R.id.rcl_settings);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onNavigator() {
    }
}