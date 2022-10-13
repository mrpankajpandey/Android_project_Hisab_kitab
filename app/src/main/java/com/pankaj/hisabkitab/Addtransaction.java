package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.AddTransactionResponse;
import com.pankaj.hisabkitab.model.Customer;
import com.pankaj.hisabkitab.model.GetCustomerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Addtransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private RadioGroup rg_type;
    private EditText  et_trans_title, et_trans_amount, et_trans_description;
    private Spinner sp_customer;
    private String amount_type;
    private int customer_id;
    ArrayAdapter<Customer> ad;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addtransaction);
        getSupportActionBar().hide();
        rg_type = (RadioGroup) findViewById(R.id.rg_type);
        sp_customer = (Spinner) findViewById(R.id.sp_customer);
        et_trans_title=(EditText) findViewById(R.id.et_trans_title);
        et_trans_amount=(EditText) findViewById(R.id.et_trans_amount);
        et_trans_description=(EditText) findViewById(R.id.et_trans_description);

             getcustomer();
    }
    public void getcustomer(){

        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
         user_id = Integer.parseInt(sp.getString("uid",""));
       // Toast.makeText(this, ""+user_id, Toast.LENGTH_SHORT).show();
        Call<GetCustomerResponse> call= RetrofitClient.getInstance().getAPI().getCustomerList(user_id);
        call.enqueue(new Callback<GetCustomerResponse>() {
            @Override
            public void onResponse(Call<GetCustomerResponse> call, Response<GetCustomerResponse> response) {
                if (response.isSuccessful()){
                    GetCustomerResponse getCustomerResponse=response.body();
                    if (getCustomerResponse.isStatus()){

                        ArrayList<Customer> customers = getCustomerResponse.getData();


                        ad = new ArrayAdapter<Customer>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customers);
                        //set Dropdown ViewResource
                        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //attach Spinner to adapter
                        sp_customer.setAdapter(ad);
                        sp_customer.setOnItemSelectedListener(Addtransaction.this);
                       // Toast.makeText(Addtransaction.this, ""+getCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }else{
                       // Toast.makeText(Addtransaction.this, ""+getCustomerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetCustomerResponse> call, Throwable t) {
              //  Toast.makeText(Addtransaction.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         customer_id=ad.getItem(position).getId();
       // Toast.makeText(this, ""+customer_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void checkType(View view) {
        int radioId=rg_type.getCheckedRadioButtonId();
        RadioButton radioButton= (RadioButton) findViewById(radioId);
          amount_type =radioButton.getText().toString().trim();
        //Toast.makeText(this, ""+amount_type, Toast.LENGTH_SHORT).show();
    }


    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }

    public void addtransaction(View view) {
   String title=et_trans_title.getText().toString().trim();
   String amount=et_trans_amount.getText().toString().trim();
   String description=et_trans_description.getText().toString().trim();

   Call<AddTransactionResponse> call=RetrofitClient.getInstance().getAPI().addTransaction(user_id,customer_id, amount+"₹", amount_type, title,description);
   call.enqueue(new Callback<AddTransactionResponse>() {
       @Override
       public void onResponse(Call<AddTransactionResponse> call, Response<AddTransactionResponse> response) {
           if (response.isSuccessful()){
               AddTransactionResponse addTransactionResponse=response.body();

               if (addTransactionResponse.isStatus()){
                   Toast.makeText(Addtransaction.this, ""+addTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
               }else{
                 //  Toast.makeText(Addtransaction.this, ""+addTransactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
               }
           }
       }

       @Override
       public void onFailure(Call<AddTransactionResponse> call, Throwable t) {
         //  Toast.makeText(Addtransaction.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

       }
   });


    }

    public void back(View view) {
        Intent i=new Intent(getApplicationContext(),Home.class);
        startActivity(i);
        finish();

    }
}