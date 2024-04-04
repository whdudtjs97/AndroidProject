package com.example.webview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.webview.databinding.FragmentWebviewBinding

class SecondWebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {
    private lateinit var binding: FragmentWebviewBinding
    var TAG: String = SecondWebViewFragment::class.java.simpleName

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

        binding.saveButton.isEnabled = false
        binding.progressbar.isGone = true

        if(webViewUrl.isNullOrEmpty()){
            Toast.makeText(activity, "저장된 페이지가 없습니다.", Toast.LENGTH_LONG).show()
        }

        val model = ViewModelProvider(requireActivity())[WebViewmodel::class.java]
        model.sendView.observe(viewLifecycleOwner) { binding.webView.loadUrl(it) }

        binding.callButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(WebViewFragment.SHARED_PREFERNCE, Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab0","")
            binding.webView.loadUrl(url.toString())
        }

    }

}

