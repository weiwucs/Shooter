package com.weiwu.shooter.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.shooter.R;
import com.weiwu.shooter.dao.CardBean;

import java.util.ArrayList;

public class RecyclerViewCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.CardViewHolder> {


    private ArrayList<CardBean> cardBeans;

    public RecyclerViewCardAdapter(ArrayList<CardBean> cardBeans) {
        this.cardBeans = cardBeans;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card_item,parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.customerName.setText(cardBeans.get(position).getText1());
        holder.dateInfo.setText(cardBeans.get(position).getText2());
        holder.num.setText(cardBeans.get(position).getText3());
        holder.pack.setText(cardBeans.get(position).getText4());
        holder.deliveryNo.setText(cardBeans.get(position).getText5());
    }

    @Override
    public int getItemCount() {
        return cardBeans != null ? cardBeans.size() : 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView deliveryNo;
        private TextView customerName;
        private TextView dateInfo;
        private TextView num;
        private TextView pack;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = (TextView) itemView.findViewById(R.id.textView1);
            dateInfo = (TextView) itemView.findViewById(R.id.textView2);
            num = (TextView) itemView.findViewById(R.id.textView3);
            pack = (TextView) itemView.findViewById(R.id.textView4);
            deliveryNo = (TextView) itemView.findViewById(R.id.textView5);
        }
    }
}
