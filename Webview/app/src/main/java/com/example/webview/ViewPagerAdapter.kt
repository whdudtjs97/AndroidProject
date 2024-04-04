package com.example.webview


import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.webview.WebViewFragment.Companion.SHARED_PREFERNCE

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity){
    var TAG: String = ViewPagerAdapter::class.java.simpleName

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                return WebViewFragment(position, "https://brcho.tistory.com/m/")
            }
            else -> {
                Log.d("ViewPagerAdaper", "Second :")
                return SecondWebViewFragment(position, "")
            }

        }
    }
}