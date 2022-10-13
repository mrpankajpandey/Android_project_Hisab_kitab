package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {
private EditText et_mail, et_password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et_mail=(EditText) findViewById(R.id.et_email);
        et_password =(EditText) findViewById(R.id.et_password);
        checksp();
    }
    public void Sign(View view) {
        String email= et_mail.getText().toString();
        String password= et_password.getText().toString();
        if (email.isEmpty()&&password.isEmpty()){
            et_mail.setError("Filed is Required");
            et_mail.requestFocus();
            et_password.setError("Filed is required");
            et_password.requestFocus();
        }
        if (email.equals("admin@gmail.com") && password.equals("1234")){
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
            // Shared perference
            SharedPreferences sp= getSharedPreferences( "user_data",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("email",email);
            editor.putString("pass", password);
            editor.commit();
            // Intent of  dashBoard
            Intent i =new Intent(Admin_login.this, Admin_dashboard.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this, "Username and password Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
    private void checksp(){
        SharedPreferences sp= getSharedPreferences( "user_data",MODE_PRIVATE);
        String email=sp.getString("email", "");
        if (!email.equals("")){
            Intent i =new Intent(Admin_login.this, Admin_dashboard.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),More.class));
        finish();
    }
}