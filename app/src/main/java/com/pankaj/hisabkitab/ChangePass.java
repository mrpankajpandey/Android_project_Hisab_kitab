package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePass extends AppCompatActivity {
    private EditText email, lass_pass, password, c_password;
private Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        getSupportActionBar().hide();
        email=   (EditText)findViewById(R.id.email);
        lass_pass=   (EditText)findViewById(R.id.last_pass);
        password=   (EditText)findViewById(R.id.password);
        c_password=   (EditText)findViewById(R.id.c_password);
        change=   (Button)findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_name=email.getText().toString();
                String l_password=lass_pass.getText().toString();
                String ch_password=password.getText().toString();
                String repassword=c_password.getText().toString();
                if (c_name.isEmpty()&&l_password.isEmpty()&&ch_password.isEmpty()){
                    email.setError("Filed is Required");
                    email.requestFocus();
                    lass_pass.setError("Filed is Required");
                    lass_pass.requestFocus();
                    return;
                }
                 Intent i=new Intent(getApplicationContext(), Profile.class);
                 startActivity(i);
                 finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Profile.class));
        finish();
    }
}