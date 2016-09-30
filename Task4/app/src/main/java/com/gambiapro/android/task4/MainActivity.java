package com.gambiapro.android.task4;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar pgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgb = (ProgressBar) findViewById(R.id.main_pgbLoading);
        webView = (WebView) findViewById(R.id.main_wbvContent);

        webView.setWebViewClient(createWebViewClient());
    }

    private WebViewClient createWebViewClient() {
        return new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pgb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pgb.setVisibility(View.INVISIBLE);
            }
        };
    }

    public void onBtnGoClick(View view) {
        EditText txtUrl = (EditText) findViewById(R.id.main_txtUrl);
        String url = txtUrl.getText().toString();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
            txtUrl.setText(url);
        }

        webView.loadUrl(url);
    }
}
