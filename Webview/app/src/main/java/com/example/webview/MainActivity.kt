package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.example.webview.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        val tabNameList: List<String> = listOf("내 블로그", "최근 방문 페이지")

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            run {
                tab.text="${tabNameList[position]}"
            }
        }.attach()

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment =
                    supportFragmentManager.fragments[binding.viewPager.currentItem]
                if (currentFragment is WebViewFragment) {
                    if (currentFragment.canGoBack()) {
                        currentFragment.goBack()
                    } else {
                        super.handleOnBackCancelled()
                    }
                } else {
                    super.handleOnBackCancelled()
                }
            }
        }

}

