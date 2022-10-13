package com.pankaj.hisabkitab.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.hisabkitab.R;
import com.pankaj.hisabkitab.model.Transaction;


import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    public Context context;
    public ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context, ArrayList<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_transation_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.tv_cust_name.setText(transactions.get(position).getCustomer_name());
          holder.tv_cust_mobile.setText(transactions.get(position).getCustomer_mobile());
          holder.tv_cust_amount.setText("₹"+transactions.get(position).getAmount());
          holder.tv_cust_type.setText(transactions.get(position).getType());
          String color=holder.tv_cust_type.getText().toString();
          if(color.equals("debit")){
              holder.tv_cust_type.setTextColor(Color.parseColor("#ff0000"));
          }
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_cust_name,tv_cust_mobile,tv_cust_amount,tv_cust_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cust_name = (TextView) itemView.findViewById(R.id.tv_cust_name);
            tv_cust_mobile = (TextView) itemView.findViewById(R.id.tv_cust_mobile);
            tv_cust_amount = (TextView) itemView.findViewById(R.id.tv_cust_amount);
            tv_cust_type = (TextView) itemView.findViewById(R.id.tv_cust_type);


        }
    }
}