package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_dashboard extends AppCompatActivity {
    private TextView tv_email,tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_password= (TextView) findViewById(R.id.tv_password);
        SharedPreferences sp = getSharedPreferences("user_data", MODE_PRIVATE);
        String email = sp.getString("email", null);
        String password=sp.getString("pass",null);
        if (!email.equals("") && (!password.equals(""))) {
            tv_email.setText(email);
            tv_password.setText(password);
        }

    }

    public void logout(View view) {
        SharedPreferences sp= getSharedPreferences( "user_data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.commit();
        Intent i=new Intent(Admin_dashboard.this, Admin_login.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Admin_dashboard.this,More.class));
        finish();
    }
}