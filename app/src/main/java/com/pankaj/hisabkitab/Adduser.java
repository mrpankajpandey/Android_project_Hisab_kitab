package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.AddCustomerResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adduser extends AppCompatActivity {
private Button signUp;
private EditText name,email,phone, address;
private  String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_adduser);
        signUp=(Button) findViewById(R.id.signup);
        name= (EditText)  findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        address=(EditText) findViewById(R.id.Address);
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        user_id =sp.getString("uid","");
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String c_name=name.getText().toString().trim();
                String c_email=email.getText().toString().trim();
                String c_phonne=phone.getText().toString().trim();
                String c_address=address.getText().toString().trim();
                if (c_name.isEmpty()&& c_email.isEmpty()&&c_phonne.isEmpty()) {
                    name.setError("* Name is Required");
                    name.requestFocus();
                    email.setError("Required Filed");
                    email.requestFocus();
                    phone.setError("Phone number Required");
                    phone.requestFocus();
                    return; //void function
                }
                Call<AddCustomerResponce> call= RetrofitClient.getInstance().getAPI().addCustomer(c_name,c_email,c_phonne,c_address,user_id);
                call.enqueue(new Callback<AddCustomerResponce>() {
                    @Override
                    public void onResponse(Call<AddCustomerResponce> call, Response<AddCustomerResponce> response) {
                        if (response.isSuccessful()){
                            AddCustomerResponce addCustomerResponce=response.body();
                            if (addCustomerResponce.isStatus()){
                                Toast.makeText(Adduser.this, ""+addCustomerResponce.getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Adduser.this, ""+addCustomerResponce.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddCustomerResponce> call, Throwable t) {
                        Toast.makeText(Adduser.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    } public void onBackPressed() {
        startActivity(new Intent(Adduser.this,Home.class));
        finish();
    }

    public void list(View view) {

        Intent i=new Intent(getApplicationContext(),Coustomer_list.class);
        startActivity(i);
        finish();
    }
}