package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Sheet_view extends AppCompatActivity {
    private String sheet_url= "https://docs.google.com/spreadsheets/d/1zkZAmDJ-HDyuDy4jvDZQcTW0JcUDfbWYpZEWNeB0ks4/edit#gid=0";
    private WebView wv_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_view);
        wv_sheet=(WebView) findViewById(R.id.wv_sheet);

        wv_sheet.setWebChromeClient(new WebChromeClient());
        wv_sheet.loadUrl(sheet_url);
        //java_script enable
        wv_sheet.getSettings().setJavaScriptEnabled(true);
        wv_sheet.getSettings().setSupportZoom(true);
    }
    public void onBackPressed() {
        startActivity(new Intent(Sheet_view.this,More.class));
        finish();
    }

    }
