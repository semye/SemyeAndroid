package com.semye.android.ui.flutter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.idlefish.flutterboost.containers.FlutterBoostFragment
import com.semye.android.ui.TokenFragment
import io.flutter.embedding.android.FlutterFragment

class TabPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TokenFragment()
            }

            else -> {
                FlutterBoostFragment.CachedEngineFragmentBuilder(FlutterBoostFragment::class.java)
                    .build()
            }
        }
    }
}