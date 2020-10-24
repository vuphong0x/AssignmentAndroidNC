package com.phong.hotrohoctap.News;

import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.phong.hotrohoctap.R;

import androidx.appcompat.app.AppCompatActivity;


public class wedview extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView=(WebView)findViewById(R.id.webview);

        // nhan Link tu ben Main
        Intent intent=getIntent();
        String link=intent.getStringExtra("Link");

        // duong dan link va load giao dien
        // lay duong link truyen vao webview de chay
        webView.loadUrl(link);

        //kick vao duong dan k bi nhay ra trinh duyet
        webView.setWebViewClient(new WebViewClient());
    }
}
