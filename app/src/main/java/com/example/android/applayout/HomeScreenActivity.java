package com.example.android.applayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WebView webview;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    startActivity(intent);
                    return true;
                }
                case R.id.navigation_login: {
                    mTextMessage.setText("Login");
                    Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return true;
                }
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    return true;
            }


            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        webview=(WebView)findViewById(R.id.webview);
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webview.loadUrl("https://bhujith10.000webhostapp.com/");
        webview.setWebViewClient(new WebViewClient());

    }

}
