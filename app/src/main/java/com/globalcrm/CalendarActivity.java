package com.globalcrm;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Global on 05-Sep-16.
 */

public class CalendarActivity extends AppCompatActivity {
    private android.webkit.WebView webView;
    private ProgressBar progressBar;
    private LinearLayout layoutProgress;
    Toolbar toolbar;
    String ua = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
    String strUrl;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_calender);
        strUrl= "http://crm.mart2global.com/admin/daily_calendar_webview.php";

       // strUrl = "http://aakashcomputers.com/";
      //strUrl = "https://ecommerce1.mart2global.com/";

        webView = (android.webkit.WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        layoutProgress = (LinearLayout) findViewById(R.id.layoutProgress);
        webView.setVisibility(View.GONE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setUserAgentString(ua);
        settings.setDomStorageEnabled(true);
        settings.setUserAgentString(ua);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                if(url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else {
                    if (url.startsWith("https://www.facebook.com/")) {
                        url = url.replace("www", "m");
                        view.loadUrl(url);
                    } else {
                        if (url.equals("https://www.youtube.com/")) {
                            url = url.replace("www", "m");
                            view.loadUrl(url);
                        } else {
                            view.loadUrl(url);
                        }
                    }
                }
                return false;
            }
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                layoutProgress.setVisibility(View.GONE);
                progressBar.setIndeterminate(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                layoutProgress.setVisibility(View.VISIBLE);
                progressBar.setIndeterminate(true);
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setDownloadListener(new DownloadListener(){
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        if (isOnline()){
            webView.loadUrl(strUrl);
        } else {
            String summary = "<html><body><font color='red'>No Internet Connection</font></body></html>";
            webView.loadData(summary, "text/html", null);
            toast("No Internet Connection.");

        }
    }

    private void toast(String message){
        Toast.makeText(CalendarActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();

//            new AlertDialog.Builder(this)
//                    .setTitle("Aakash Computers")
//                    .setMessage("Are you sure you want to close?")
//                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
//                        }
//                    })
//                    .setNegativeButton("No", null)
//                    .show();

   }
//
  }
}