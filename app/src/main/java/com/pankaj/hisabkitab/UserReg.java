package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.CreateUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserReg extends AppCompatActivity {
    private EditText et_name, et_email, et_mobile, et_password;
    private Button signbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
        signbtn = (Button) findViewById(R.id.signbtn);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    public void SignUp(View view) {
        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String mobile = et_mobile.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (name.isEmpty() && email.isEmpty() && mobile.isEmpty()) {
            et_email.setError("Required");
            et_email.requestFocus();
            et_mobile.setError("Required");
            et_mobile.requestFocus();
            et_name.setError("Required");
            et_name.requestFocus();
            return;
        }
        Call<CreateUser> call=RetrofitClient.getInstance().getAPI().createUser(name,email,mobile,password);
        call.enqueue(new Callback<CreateUser>() {
            @Override
            public void onResponse(Call<CreateUser> call, Response<CreateUser> response) {
                if (response.isSuccessful()){
                    CreateUser user=response.body();
                    if (user.isStatus()) {
                        Toast.makeText(UserReg.this, "" + user.getMessage(), Toast.LENGTH_SHORT).show();
                         Intent i = new Intent(getApplicationContext(), Userlogin.class);
                         startActivity(i);
                         finish();
                    }else{
                        Toast.makeText(UserReg.this, ""+user.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<CreateUser> call, Throwable t) {
                Toast.makeText(UserReg.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Goto_login(View view) {
        Intent i=new Intent(getApplicationContext(),Userlogin.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Userlogin.class));
        finish();
    }
}