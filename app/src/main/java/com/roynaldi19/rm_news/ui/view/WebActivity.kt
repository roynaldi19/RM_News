package com.roynaldi19.rm_news.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.roynaldi19.rm_news.R
import com.roynaldi19.rm_news.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding

    companion object {
        const val EXTRA_URL = "extra_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                view.loadUrl("javascript:alert('Halaman berhasil dimuat')")
            }
        }

        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: android.webkit.JsResult): Boolean {
                //Toast.makeText(this@WebActivity, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }

        val url = intent.getStringExtra(EXTRA_URL)
        if (url != null){
            binding.webView.loadUrl(url)
        }
    }
}