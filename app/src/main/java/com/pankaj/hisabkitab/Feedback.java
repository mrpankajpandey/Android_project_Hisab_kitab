package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    private EditText user_id, name,email,phone,message;
private Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
       user_id= (EditText)  findViewById(R.id.user_id);
       name= (EditText)  findViewById(R.id.name);
        email= (EditText)  findViewById(R.id.email);
        phone=(EditText) findViewById(R.id.phone) ;
        message= (EditText)  findViewById(R.id.message);
        sub= (Button) findViewById(R.id.sub);
        SharedPreferences sp=getSharedPreferences("user_data",MODE_PRIVATE);
        String fname=sp.getString("uname","");
        String femail=sp.getString("uemail","");
        String mobile=sp.getString("umobile","");
        String id=sp.getString("uid","");
          user_id.setText(id);
          name.setText(fname);
          email.setText(femail);
          phone.setText(mobile);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=user_id.getText().toString();
                String u_email=email.getText().toString();
                String u_name=name.getText().toString();
                String u_message =message.getText().toString();
                if (uname.isEmpty()&& u_email.isEmpty()&& u_name.isEmpty()&& u_message.isEmpty()){
                    user_id.setError("Filed is Required");
                    user_id.requestFocus();
                    email.setError("Mail is Required ");
                    email.requestFocus();
                    name.setError("Required");
                    name.requestFocus();
                    return;
                }

                Toast.makeText(Feedback.this, "Thanks for Feedback", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(Feedback.this,More.class));
       finish();
    }
}