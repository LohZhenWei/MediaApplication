package com.example.media.app.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.media.app.ui.fragment.AudioListingFragment
import com.example.media.app.ui.fragment.VideoListingFragment

class MediaTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf(VideoListingFragment(), AudioListingFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}
