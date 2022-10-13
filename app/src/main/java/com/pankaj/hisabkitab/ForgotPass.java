package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPass extends AppCompatActivity {
    private EditText email,password, c_password;
private Button forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        getSupportActionBar().hide();
        email=(EditText)findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        c_password =(EditText) findViewById(R.id.c_password);
        forgot=(Button) findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_email=email.getText().toString();
                String f_password=password.getText().toString();
                String repassword=c_password.getText().toString();
                if (f_email.isEmpty()&&f_password.isEmpty()){
                    email.setError("Required Filed");
                    email.requestFocus();
                    password.setError("Required Filed");
                    password.requestFocus();
                    return;
                }
                Intent i=new Intent(getApplicationContext(),Userlogin.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(getApplicationContext(),Userlogin.class));
       finish();
    }
}