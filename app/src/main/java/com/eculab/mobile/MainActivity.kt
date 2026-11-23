package com.eculab.mobile

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @Suppress("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this).apply {
            set背景ColorCompat()
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = false
            settings.mediaPlaybackRequiresUserGesture = false
            settings.javaScriptCanOpenWindowsAutomatically = false
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
        }

        setContentView(webView)
        if (savedInstanceState == null) {
            webView.loadUrl("file:///android_asset/www/index.html")
        } else {
            webView.restoreState(savedInstanceState)
        }
    }

    private fun WebView.set背景ColorCompat() {
        setBackgroundColor(android.graphics.Color.rgb(12, 15, 20))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Android API; retained for WebView history navigation")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) {
            webView.stopLoading()
            webView.destroy()
        }
        super.onDestroy()
    }
}
