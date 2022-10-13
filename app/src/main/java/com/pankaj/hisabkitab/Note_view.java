package com.pankaj.hisabkitab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Note_view extends AppCompatActivity {
private String note_url="https://docs.google.com/document/u/0/?tgif=d";
private WebView wv_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        wv_note=(WebView) findViewById(R.id.wv_note);

        wv_note.setWebChromeClient(new WebChromeClient());
        wv_note.loadUrl(note_url);
        //java_script enable
        wv_note.getSettings().setJavaScriptEnabled(true);
        wv_note.getSettings().setSupportZoom(true);
    }
    public void onBackPressed() {
        startActivity(new Intent(Note_view.this,More.class));
        finish();
    }

}