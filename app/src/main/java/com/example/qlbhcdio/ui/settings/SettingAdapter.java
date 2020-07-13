package com.example.qlbhcdio.ui.settings;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.SettingFunction;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    private Context context;
    private NavigatorToActivity navigatorToActivity;

    private List<SettingFunction> mFunction = new ArrayList<>();
    public SettingAdapter(Context context , NavigatorToActivity navigatorToActivity) {
        this.navigatorToActivity = navigatorToActivity;
        this.context = context;
        initData();
    }

    private void initData() {
        mFunction.add(new SettingFunction(FunTab.LB_ACCOUNT));
        mFunction.add(new SettingFunction(FunTab.FUN_PAYMENT));
        mFunction.add(new SettingFunction(FunTab.FUN_PASSWORD));
        mFunction.add(new SettingFunction(FunTab.LB_SETTING));
        mFunction.add(new SettingFunction(FunTab.FUN_LANGUAGE));
        mFunction.add(new SettingFunction(FunTab.FUN_NOTIFICATION));
        mFunction.add(new SettingFunction(FunTab.FUN_LOGOUT));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FunTab.getTypeLabel()) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_label_setting, parent, false);
            return new LabelViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).tv_function.setText(mFunction.get(position).getName());
            ((ItemViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigatorToActivity.onNavigator();
                }
            });
        }
        if (holder instanceof LabelViewHolder) {
            ((LabelViewHolder) holder).tv_function.setText(mFunction.get(position).getName());
        }

    }

    @Override
    public int getItemCount() {
        return mFunction.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (mFunction.get(position).getId() <= 0) {
            return FunTab.getTypeLabel();
        } else {
            return FunTab.getTypeFun();
        }
    }
    public interface NavigatorToActivity{
        void onNavigator();
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    TextView tv_function;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_function = itemView.findViewById(R.id.tv_function);
        cardView = itemView.findViewById(R.id.CardView_setting);
    }
}

class LabelViewHolder extends RecyclerView.ViewHolder {
    TextView tv_function;
    public LabelViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_function = itemView.findViewById(R.id.tv_label_setting);
    }

}

