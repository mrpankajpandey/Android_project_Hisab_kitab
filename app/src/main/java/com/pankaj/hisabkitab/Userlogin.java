package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.User;
import com.pankaj.hisabkitab.model.UserloginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Userlogin extends AppCompatActivity {
    private EditText et_email, et_password;
   private Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        getSupportActionBar().hide();
      et_password=  (EditText) findViewById(R.id.et_password);
       et_email= (EditText) findViewById(R.id.et_email);
       sign=(Button) findViewById(R.id.sign);


        SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
        String spEmail = sp.getString("uemail","");
        if(!spEmail.equals("")){

            Intent i=new Intent(getApplicationContext(),Home.class);
            startActivity(i);
            finish();

        }

        //Toast.makeText(this, ""+sp.getString("email","").toString(), Toast.LENGTH_SHORT).show();

       sign.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email=et_email.getText().toString().trim();
               String password=et_password.getText().toString().trim();
               if (email.isEmpty()&&password.isEmpty()){
                   et_email.setError("Required");
                   et_email.requestFocus();
                   et_password.setError("Required");
                   et_password.requestFocus();
                   return;
               }
               Call<UserloginResponse> call=RetrofitClient.getInstance().getAPI().login(email,password);
               call.enqueue(new Callback<UserloginResponse>() {
                   @Override
                   public void onResponse(Call<UserloginResponse> call, Response<UserloginResponse> response) {
                       if (response.isSuccessful()){
                         UserloginResponse userloginResponse=  response.body();

                         if (userloginResponse.isStatus())
                         {
                             Toast.makeText(Userlogin.this, ""+userloginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                   ArrayList<User> user = userloginResponse.getData();

                                    String uid = user.get(0).getId();
                                    String uname = user.get(0).getName();
                                    String uemail = user.get(0).getEmail();

                                    String upassword = user.get(0).getPassword();
                                    String umobile = user.get(0).getMobile();
                                    String udate_time = user.get(0).getDate_time();
                                    String uis_login = user.get(0).getIs_login();
                                    String ustatus = user.get(0).getStatus();
                                    String utoken = user.get(0).getToken();

                                    //Shared Preference

                                 SharedPreferences sp = getSharedPreferences("user_data",MODE_PRIVATE);
                                 SharedPreferences.Editor editor = sp.edit();

                                 editor.putString("uid",uid);
                                 editor.putString("uname",uname);
                                 editor.putString("uemail",uemail);
                                 editor.putString("upassword",upassword);
                                 editor.putString("umobile",umobile);
                                 editor.putString("udate_time",udate_time);
                                 editor.putString("uis_login",uis_login);
                                 editor.putString("ustatus",ustatus);
                                 editor.putString("utoken",utoken);
                                 editor.commit();

                                 Intent i=new Intent(getApplicationContext(),Home.class);
                                 startActivity(i);
                                 finish();
                         }
                         else {
                             Toast.makeText(Userlogin.this, "Incorrect Username Password", Toast.LENGTH_SHORT).show();

                         }
                          //
                       }
                   }

                   @Override
                   public void onFailure(Call<UserloginResponse> call, Throwable t) {
                      Toast.makeText(Userlogin.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               });
           }
       });



    }

    public void Rgs(View view) {

        Intent i=new Intent(getApplicationContext(),UserReg.class);
        startActivity(i);
        finish();
    }

    public void Forgot(View view) {
        Intent i=new Intent(getApplicationContext(),ForgotPass.class);
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
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        },2000);
    }
}