package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.security.PrivateKey;

public class Terms_policy extends AppCompatActivity {
    private String terms_url="https://docs.google.com/gview?embedded=true&url=https://myhisab.seeksolution.in/terms-and-conditions/terms.pdf";
    private WebView wv_terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_policy);
       // getSupportActionBar().hide();
       wv_terms= (WebView) findViewById(R.id.wv_terms);

        wv_terms.setWebChromeClient(new WebChromeClient());
        wv_terms.loadUrl(terms_url);
        //java_script enable
        wv_terms.getSettings().setJavaScriptEnabled(true);
        wv_terms.getSettings().setSupportZoom(true);
    }
    public void onBackPressed() {
        startActivity(new Intent(Terms_policy.this,More.class));
        finish();
    }
}