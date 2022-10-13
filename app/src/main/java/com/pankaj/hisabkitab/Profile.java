package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pankaj.hisabkitab.Api.RetrofitClient;
import com.pankaj.hisabkitab.model.LogoutUserResponse;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
private ImageView back;
private Button signout;
private TextView profile_name,profile_id,user_name,user_email,user_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        signout=(Button) findViewById(R.id.signout);
       back=(ImageView) findViewById(R.id.back);
       profile_name=(TextView) findViewById(R.id.profile_name);
       profile_id=(TextView) findViewById(R.id.profile_id);
       user_name=(TextView) findViewById(R.id.user_name);
       user_email =(TextView) findViewById(R.id.user_email);
       user_mobile=(TextView) findViewById(R.id.user_mobile);

       SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
       String name=sp.getString("uname","");
       profile_name.setText(name);
       user_name.setText(name);
       String id=sp.getString("uid","");
       profile_id.setText(id);
        String email=sp.getString("uemail","");
        user_email.setText(email);
        String mobile=sp.getString("umobile","");
        user_mobile.setText(mobile);
    }


    public void Back(View view) {
        Intent i=new Intent(Profile.this,Home.class);
        startActivity(i);
        finish();
    }


    public void Out(View view) {
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        String spToken=sp.getString("utoken","");
        if(!spToken.equals("")){
            Call<LogoutUserResponse> call= RetrofitClient.getInstance().getAPI().logout(spToken);
            call.enqueue(new Callback<LogoutUserResponse>() {
                @Override
                public void onResponse(Call<LogoutUserResponse> call, Response<LogoutUserResponse> response) {
                    if (response.isSuccessful()){
                        LogoutUserResponse logoutUserResponce=response.body();
                        Toast.makeText(Profile.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.clear();
                        editor.commit();

                        Intent i=new Intent(getApplicationContext(),Userlogin.class);
                        startActivity(i);
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<LogoutUserResponse> call, Throwable t) {
                    Toast.makeText(Profile.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Profile.this,Home.class));
        finish();
    }

    public void Change(View view) {

        Intent i=new Intent(getApplicationContext(),ChangePass.class);
        startActivity(i);
        finish();
    }

    public void Update(View view) {

        Intent i=new Intent(getApplicationContext(),Update_profile.class);
        startActivity(i);
        finish();
    }
}