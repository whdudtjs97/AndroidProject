package com.example.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewmodel: ViewModel() {
    val sendView = MutableLiveData<String>()

    fun sendWebView(url: String){
        sendView.value = url
    }

}