package com.example.webview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.webview.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding
    var TAG: String = WebViewFragment::class.java.simpleName
    lateinit var model: WebViewmodel
    // lateinit 선언이유 : oncreate 이후에 뷰 바인딩이 나오기 때문에 nullable로 해주면 계속 확인해줘야함 null인지 아닌지 그래서 lateinit을 사용용

    companion object {
        const val SHARED_PREFERNCE = "HISTORY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.callButton.isEnabled = false

        model = ViewModelProvider(requireActivity()).get(WebViewmodel::class.java)
        val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERNCE, Context.MODE_PRIVATE)

       binding.webView.webViewClient = WebviewClient(binding.progressbar) { url ->
           model.sendWebView(url)
           binding.saveButton.setOnClickListener {
               sharedPreferences?.edit {
                   putString("tab0", url)
               }
               Toast.makeText(activity, "페이지가 저장되었습니다.", Toast.LENGTH_LONG).show()
           }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webViewUrl)


    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}