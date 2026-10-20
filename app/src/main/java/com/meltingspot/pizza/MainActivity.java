package com.meltingspot.pizza;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.ViewGroup;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends Activity {
    private WebView webView;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        webView = new WebView(this);
        webView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        webView.setBackgroundColor(0xffffffff);
        setContentView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setMediaPlaybackRequiresUserGesture(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if (uri.toString().startsWith("https://appassets.androidplatform.net/")) return false;
                if (!request.isForMainFrame()) return false;
                try { startActivity(new Intent(Intent.ACTION_VIEW, uri)); }
                catch (Exception ignored) { }
                return true;
            }
        });
        try (InputStream file = getAssets().open("index.html")) {
            byte[] bytes = new byte[file.available()];
            int offset = 0;
            while (offset < bytes.length) {
                int count = file.read(bytes, offset, bytes.length - offset);
                if (count == -1) break;
                offset += count;
            }
            String html = new String(bytes, 0, offset, StandardCharsets.UTF_8);
            // HTTPS origin enables localStorage while the page itself is bundled offline.
            webView.loadDataWithBaseURL("https://appassets.androidplatform.net/", html, "text/html", "UTF-8", null);
        } catch (Exception e) {
            webView.loadData("<h3>Website could not be loaded.</h3>", "text/html", "UTF-8");
        }
    }
    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
    @Override protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
