package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class More extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bottomNavigationView=findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.more:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                         finish();
                         overridePendingTransition(0,0);
                         return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void Admin(View view) {
         Intent i=new Intent(More.this , Admin_login.class);
         startActivity(i);
         finish();
    }

    public void Terms(View view) {
  Intent i=new Intent(More.this,Terms_policy.class);
   startActivity(i);
   finish();
    }

    public void Invoice(View view) {
        Intent i=new Intent(More.this, Coustomer_list.class);
        startActivity(i);
        finish();
    }

    public void sheet(View view) {
        Intent i=new Intent(More.this,Sheet_view.class);
        startActivity(i);
        finish();
    }

    public void Note(View view) {
        Intent i=new Intent(More.this,Note_view.class);
        startActivity(i);
        finish();
    }

    public void feedback(View view) {
        Intent i=new Intent(More.this , Feedback.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }
}