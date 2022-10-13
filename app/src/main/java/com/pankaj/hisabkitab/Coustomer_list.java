package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.adapter.CustomerAdaptor;
import com.pankaj.hisabkitab.model.Customer;
import com.pankaj.hisabkitab.model.GetCustomerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Coustomer_list extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<Customer> customers;
    private  int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustomer_list);
        getSupportActionBar().hide();

        recyclerView =(RecyclerView) findViewById(R.id.rv_customer);
        getCustomerData();

    }

    private void getCustomerData() {

        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        user_id= Integer.parseInt(sp.getString("uid",""));
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Customer  loading");
        progressDialog.show();
        Call<GetCustomerResponse> call= RetrofitClient.getInstance().getAPI().getCustomerList(user_id);
        call.enqueue(new Callback<GetCustomerResponse>() {
            @Override
            public void onResponse(Call<GetCustomerResponse> call, Response<GetCustomerResponse> response) {
                if (response.isSuccessful()){
                    GetCustomerResponse getCustomerResponse=response.body();
                    if (getCustomerResponse.isStatus()){
                        customers =getCustomerResponse.getData();
                        CustomerAdaptor adaptor=new CustomerAdaptor(getApplicationContext(),customers);
                        recyclerView.setAdapter(adaptor);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();

                    }else{
                       // Toast.makeText(Coustomer_list.this, ""+getCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCustomerResponse> call, Throwable t) {
               // Toast.makeText(Coustomer_list.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }


    public void Back(View view) {
        Intent i=new Intent(getApplicationContext(),More.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),More.class));
      finish();

    }
}