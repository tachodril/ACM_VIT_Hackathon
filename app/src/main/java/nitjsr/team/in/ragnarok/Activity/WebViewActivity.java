package nitjsr.team.in.ragnarok.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import nitjsr.team.in.ragnarok.R;
import nitjsr.team.in.ragnarok.utils.AppConstants;

public class WebViewActivity extends Activity {

    WebView webView;
    ProgressDialog progressDialog;
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webView=(WebView)findViewById(R.id.webView);
        toolbarTitle=(TextView)findViewById(R.id.toolbar_title);

//        webView.setWebViewClient(new MyWebViewClient());

        Intent i = getIntent();
        String url= i.getStringExtra("url");
        String title=i.getStringExtra("title");
        toolbarTitle.setText(title);

        if (AppConstants.isNetworkAvailable(getApplicationContext())) {
            // Internet Connection is Present
            // make HTTP requests
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.loadUrl(url);
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webView.loadUrl(url);
        }

//        renderWebPage(url);
    }

    // Custom method to render a web page
    protected void renderWebPage(String urlToRender) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Do something on page loading started
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Do something when page loading finished
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
            }
        });
        // Enable the javascript
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setAppCacheEnabled(true);

        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getPath());

        // Set the cache mode
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        // Render the web page
        webView.loadUrl(urlToRender);
    }

}
