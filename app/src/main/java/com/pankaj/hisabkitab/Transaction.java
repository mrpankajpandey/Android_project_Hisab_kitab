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
import com.pankaj.hisabkitab.adapter.TransactionAdapter;
import com.pankaj.hisabkitab.model.GetCustomerResponse;
import com.pankaj.hisabkitab.model.GetTransactionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transaction extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<com.pankaj.hisabkitab.model.Transaction> transactions;
    private  int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
       getSupportActionBar().hide();
       recyclerView=(RecyclerView) findViewById(R.id.rv_transaction);
       getTransactionData();
    }

    private void getTransactionData() {
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        user_id= Integer.parseInt(sp.getString("uid",""));

        // animation loader
        // data in loader show
        // data out loader dismiss
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Transaction loading");
        progressDialog.show();
        Call<GetTransactionResponse> call= RetrofitClient.getInstance().getAPI().getAllTransactiondata(user_id);
      call.enqueue(new Callback<GetTransactionResponse>() {
          @Override
          public void onResponse(Call<GetTransactionResponse> call, Response<GetTransactionResponse> response) {
              if (response.isSuccessful()){
                  GetTransactionResponse getTransactionResponse=response.body();
                  if(getTransactionResponse.isStatus()){
                     // Toast.makeText(Transaction.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                      transactions=getTransactionResponse.getData();
                      TransactionAdapter adapter=new TransactionAdapter(getApplicationContext(),transactions);
                      recyclerView.setAdapter(adapter);
                      recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                      recyclerView.setHasFixedSize(true);
                      progressDialog.dismiss();

                  }else{
                    //  Toast.makeText(Transaction.this, ""+getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                      progressDialog.dismiss();
                  }
              }
          }

          @Override
          public void onFailure(Call<GetTransactionResponse> call, Throwable t) {
              //Toast.makeText(Transaction.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
              progressDialog.dismiss();
          }
      });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home.class));
        finish();
    }

    public void Back(View view) {
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
        finish();
    }
}