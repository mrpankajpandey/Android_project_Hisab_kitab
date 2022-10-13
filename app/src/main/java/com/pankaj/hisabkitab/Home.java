package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.adapter.TransactionAdapter;
import com.pankaj.hisabkitab.model.ExtraData;
import com.pankaj.hisabkitab.model.GetTransactionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    public RecyclerView recyclerView;
    TextView total_credit,total_debit,total_balance;
    ArrayList<com.pankaj.hisabkitab.model.ExtraData> total_amount;
    public ArrayList<com.pankaj.hisabkitab.model.Transaction> transactions;
    private  int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        bottomNavigationView=findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext(),More.class));
                        finish();
                        overridePendingTransition(0,0);
                        return  true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.rv_transaction);
        total_credit=(TextView) findViewById(R.id.total_credit);
        total_debit=(TextView) findViewById(R.id.total_debit);
        total_balance=(TextView) findViewById(R.id.total_balance);
        getTransactionData();

    }

    private void getTransactionData() {
        SharedPreferences sp = getSharedPreferences("user_data", MODE_PRIVATE);
        user_id = Integer.parseInt(sp.getString("uid", ""));

        // animation loader
        // data in loader show
        // data out loader dismiss
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Transaction loading");
        progressDialog.show();
        Call<GetTransactionResponse> call = RetrofitClient.getInstance().getAPI().getAllTransactiondata(user_id);
        call.enqueue(new Callback<GetTransactionResponse>() {
            @Override
            public void onResponse(Call<GetTransactionResponse> call, Response<GetTransactionResponse> response) {
                if (response.isSuccessful()) {
                    GetTransactionResponse getTransactionResponse = response.body();
                    if (getTransactionResponse.isStatus()) {
                        Toast.makeText(getApplicationContext(), "" + getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        transactions = getTransactionResponse.getData();
                        total_amount=getTransactionResponse.getExtra_data();
                        TransactionAdapter adapter = new TransactionAdapter(getApplicationContext(), transactions);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setHasFixedSize(true);
                        progressDialog.dismiss();
                        try {
                            String Total_credit=total_amount.get(1).getAmount();
                            String Total_debit=total_amount.get(0).getAmount();
                            total_credit.setText("₹"+Total_credit);
                            total_debit.setText("₹"+Total_debit);
                            int debit=Integer.parseInt(Total_debit);
                            int credit=Integer.parseInt(Total_credit);
                            int to_balance=(debit+credit);
                            total_balance.setText(String.valueOf("₹"+to_balance));
                        }catch (Exception e){
                            String Total_credit=total_amount.get(0).getAmount();
                            String Total_debit=total_amount.get(0).getAmount();
                            total_credit.setText("₹0");
                            total_debit.setText("₹0");
                            int debit=Integer.parseInt(Total_debit);
                            int credit=Integer.parseInt(Total_credit);
                            int to_balance=(debit+credit);
                            total_balance.setText(String.valueOf("₹0"));

                        }


                    } else {
                       // Toast.makeText(getApplicationContext(), "" + getTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTransactionResponse> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }


// intent of Add customer page
    public void Addperson(View view) {
            Intent i =new Intent(Home.this, Adduser.class);
            startActivity(i);
            finish();
    }
// Intent of back pressed screen




// Intent of transaction Details page
    public void transaction(View view) {
        Intent i=new Intent(getApplicationContext(), Transaction.class);
        startActivity(i);
        finish();
    }
// intent of Add Transaction page
    public void Transaction(View view) {
        Intent i=new Intent(getApplicationContext(),Addtransaction.class);
        startActivity(i);
        finish();
    }
    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce=true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        finishAffinity();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        },2000);

    }
}