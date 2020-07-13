package com.example.qlbhcdio.ui.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Invoice;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment implements HistoryAdapter.setOnClickItemHistory,
        SwipeRefreshLayout.OnRefreshListener,
        View.OnClickListener, ViewHistory.view {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerViewHistory;
    private HistoryAdapter mHistoryAdapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView tv_datePicker;
    private PresenterHistory presenter=new PresenterHistory(this) ;
    private List<Invoice> mHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        bindingIU(view);
        initViews();
        initData();
        handleEvents();
        return view;
    }

    private void initData() {
        presenter.fetchHistory();
    }

    private void handleEvents() {
        refreshLayout.setOnRefreshListener(this);
        tv_datePicker.setOnClickListener(this);
    }

    private void initViews() {
        initHistory();

    }

    private void initHistory() {
        mHistoryAdapter = new HistoryAdapter(getContext(), this,mHistory);
        recyclerViewHistory.post(() -> recyclerViewHistory.setAdapter(mHistoryAdapter));
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewHistory.setLayoutManager(manager);
    }

    private void bindingIU(View view) {
        recyclerViewHistory = view.findViewById(R.id.rcl_history);
        tv_datePicker = view.findViewById(R.id.tv_calendar);
        refreshLayout = view.findViewById(R.id.swipe_history);
        mHistory = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void setOnClick(int position) {

    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        initData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_calendar:  break;
        }
    }

    @Override
    public void onResultHistory(List<Invoice> inv) {
            mHistory = inv;
            initHistory();
    }

    @Override
    public void onFailHistory(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}