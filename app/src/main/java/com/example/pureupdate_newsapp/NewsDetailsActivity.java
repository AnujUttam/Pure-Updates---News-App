package com.example.pureupdate_newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pureupdate_newsapp.Models.NewsHeadline;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
NewsHeadline headlines;
WebView webView;
ProgressDialog dialog;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);



        webView = findViewById(R.id.web_view);
        dialog = new ProgressDialog(this);
        dialog.show();

        headlines = (NewsHeadline) getIntent().getSerializableExtra("data");



        String url = headlines.getUrl();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);
        dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}