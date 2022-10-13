package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.CreateUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_profile extends AppCompatActivity {
 private EditText full_name,email , phone;
 private Button update;
private  TextView profile_name,update_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().hide();
        full_name=(EditText) findViewById(R.id.full_name);
        profile_name=(TextView) findViewById(R.id.profile_name);
        update_id=(TextView) findViewById(R.id.update_id);
        email=(EditText) findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone);
        update=(Button) findViewById(R.id.update);
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        String name=sp.getString("uname","");
        String pemail=sp.getString("uemail","");
        String mobile=sp.getString("umobile","");
        phone.setText(mobile);
        email.setText(pemail);
        profile_name.setText(name);
        full_name.setText(name);
        int id= Integer.parseInt(sp.getString("uid",""));

        update_id.setText( String.valueOf(id));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=full_name.getText().toString();
                String u_email=email.getText().toString();
                String u_phone=phone.getText().toString();
                if (name.isEmpty()&& u_email.isEmpty()&& u_phone.isEmpty()){
                    full_name.setError("Filed is Required");
                    full_name.requestFocus();
                    email.setError("Mail is Required ");
                    email.requestFocus();
                    phone.setError("Required");
                    phone.requestFocus();
                    return;
                }

                Call<CreateUser> call = RetrofitClient.getInstance().getAPI().UpdateUserDetails(name,mobile,id);
                call.enqueue(new Callback<CreateUser>() {
                    @Override
                    public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                        if(response.isSuccessful()){
                            CreateUser userResponse = response.body();
                            if(userResponse.isStatus()){
                                Toast.makeText(Update_profile.this, ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Userlogin.class);
                                startActivity(intent);
                                finish();


                            }else{
                                Toast.makeText(Update_profile.this, ""+userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<CreateUser> call, Throwable t) {

                    }
                });

                Toast.makeText(Update_profile.this, "Your Profile Updated Successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void Back(View view) {
        Intent i=new Intent(getApplicationContext(),Profile.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Profile.class));
        finish();
    }
}