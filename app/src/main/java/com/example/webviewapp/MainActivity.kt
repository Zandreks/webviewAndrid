package com.example.webviewapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()

        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.domStorageEnabled =true
        myWebView.addJavascriptInterface(WebAppInterface(this), "Android")
        myWebView.settings.supportMultipleWindows()
        myWebView.settings.allowFileAccessFromFileURLs = true
        myWebView.settings.databaseEnabled = true
        myWebView.settings.allowFileAccess = true
        myWebView.settings.allowContentAccess = true
//        myWebView.loadUrl("http://10.0.2.2:3000/")
        myWebView.loadUrl("https://pmkrezerv.kz/")

    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        val myWebView: WebView = findViewById(R.id.webview)

        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}

/** Instantiate the interface and set the context  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
    @JavascriptInterface
    fun getStringToWeb(): String {
        return "ok max Android send message "
    }
}