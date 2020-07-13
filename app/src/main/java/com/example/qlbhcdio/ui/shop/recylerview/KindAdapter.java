package com.example.qlbhcdio.ui.shop.recylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlbhcdio.R;
import com.example.qlbhcdio.model.Kind;
import com.example.qlbhcdio.ui.shop.KindTab;

import java.util.ArrayList;
import java.util.List;;

public class KindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Kind> mKinds = new ArrayList<>();
    private OnSelectOfKind onSelectOfKind;


    public KindAdapter(Context context,OnSelectOfKind onSelectOfKind) {
        this.context = context;
        this.onSelectOfKind = onSelectOfKind;
        initDataOnList();
    }

    private void initDataOnList() {
        mKinds.add(new Kind(KindTab.TAB_0.getName()));
        mKinds.add(new Kind(KindTab.TAB_1.getName()));
        mKinds.add(new Kind(KindTab.TAB_2.getName()));
        mKinds.add(new Kind(KindTab.TAB_3.getName()));
        mKinds.add(new Kind(KindTab.TAB_4.getName()));
        mKinds.add(new Kind(KindTab.TAB_5.getName()));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kind, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).tv_name.setText(mKinds.get(position).getName());
        ((ItemViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectOfKind.indexSelect(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKinds.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =itemView.findViewById(R.id.CardView_Kind);
            tv_name = itemView.findViewById(R.id.tv_kind);
        }
    }
    public interface OnSelectOfKind{
        void indexSelect(int i);
    }

}
