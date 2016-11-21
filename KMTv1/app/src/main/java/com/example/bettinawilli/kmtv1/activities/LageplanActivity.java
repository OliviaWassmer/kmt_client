package com.example.bettinawilli.kmtv1.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.bettinawilli.kmtv1.R;
import com.example.bettinawilli.kmtv1.mobile.AWSMobileClient;

public class LageplanActivity extends AppCompatActivity{

    private WebView webViewLageplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lageplan);

        //http://kmt.thewassmer.ch/eventkarte.png diesen Link anzeigen als Bild

        webViewLageplan = (WebView) findViewById(R.id.webViewLageplan);

        //Enable JavaScript
        WebSettings webSettings = webViewLageplan.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //ladet Bild von URL via Webserver
        webViewLageplan.loadUrl("http://kmt.thewassmer.ch/eventkarte.png");


    }
}
