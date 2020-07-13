package com.example.qlbhcdio.ui.history;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Authentication;
import com.example.qlbhcdio.model.Invoice;
import com.example.qlbhcdio.retrofit.API;
import com.example.qlbhcdio.retrofit.DataClient;
import com.example.qlbhcdio.ui.details.InvoiceDetailsPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String INVOICE_CODE = "MADH";
    private Context context;
    private List<Invoice> mHistory;
    private setOnClickItemHistory onClickItemHistory;


    public HistoryAdapter(Context context, setOnClickItemHistory onClickItemHistory,List<Invoice> mHistory) {
        this.context = context;
        this.onClickItemHistory = onClickItemHistory;
        this.mHistory = mHistory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ItemViewHolder) holder).tv_id.setText(String.valueOf(mHistory.get(position).getId()));
        if (mHistory.get(position).getState() == 1) {
            ((ItemViewHolder) holder).tv_state.setText("Đang xử lý");
        } else {
            ((ItemViewHolder) holder).tv_state.setTextColor(Color.parseColor("#ff669900"));
            ((ItemViewHolder) holder).tv_state.setText("Đã giao hàng");
        }

        ((ItemViewHolder) holder).tv_dateTime.setText(mHistory.get(position).getDate());
        ((ItemViewHolder) holder).tv_money.setText(String.valueOf(mHistory.get(position).getPrice()));

        ((ItemViewHolder) holder).cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, InvoiceDetailsPage.class);
            intent.putExtra(INVOICE_CODE, mHistory.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_money;
        TextView tv_dateTime;
        TextView tv_id;
        TextView tv_state;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.CardView_itemHistory);
            tv_id = itemView.findViewById(R.id.tv_id_bill);
            tv_dateTime = itemView.findViewById(R.id.tv_date_bill);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_money = itemView.findViewById(R.id.tv_sumMoney_bill);
        }
    }

    public interface setOnClickItemHistory {
        void setOnClick(int position);
    }


}
