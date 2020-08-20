package com.example.informr;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        webView = (WebView) findViewById(R.id.webview);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String url = bundle.getString("url");

            webView.loadUrl(url);
        }
    }
}
