package com.pankaj.hisabkitab.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.hisabkitab.R;
import com.pankaj.hisabkitab.model.Customer;

import java.util.ArrayList;

public class CustomerAdaptor extends RecyclerView.Adapter<CustomerAdaptor.ViewHolder> {
    public Context context;
    public ArrayList<Customer> customers;

    public CustomerAdaptor(Context context, ArrayList<Customer> customers){
        this.context =context;
        this.customers=customers;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_customer_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cust_name.setText(customers.get(position).getName());
        holder.cust_mail.setText(customers.get(position).getEmail());
        holder.cust_phone.setText(customers.get(position).getMobile());
        holder.cust_address.setText(customers.get(position).getAddress());
      //  holder.cid.setText(customers.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cust_name,cust_phone,cust_mail,cust_address,cid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
         //   cid=(TextView) itemView.findViewById(R.id.customer_uid);
            cust_name=(TextView) itemView.findViewById(R.id.cust_name);
            cust_mail=(TextView)  itemView.findViewById(R.id.cust_mail);
            cust_phone=(TextView)  itemView.findViewById(R.id.cust_phone);
            cust_address=(TextView) itemView.findViewById(R.id.cust_address);
        }
    }
}
