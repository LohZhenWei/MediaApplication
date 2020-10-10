package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.View
import com.example.media.app.ui.viewmodel.MediaViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import com.example.media.app.R
import com.example.media.app.ui.adapter.MediaTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main_media.*

@AndroidEntryPoint
class MediaMainFragment : BaseFragment<MediaViewModel>() {

    override val viewModel: MediaViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_main_media

    private var tabLayoutMediator: TabLayoutMediator? = null
    private var mediaTabAdapter: MediaTabAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mediaTabAdapter = MediaTabAdapter(this)
        listing_view_pager.apply {
            adapter = mediaTabAdapter
            isUserInputEnabled = true
        }

        tabLayoutMediator = TabLayoutMediator(tab_layout, listing_view_pager) { tab, position ->
            val resourceTitle = when (position) {
                0 -> R.string.label_videos
                else -> R.string.label_audios
            }
            tab.text = getString(resourceTitle)
            listing_view_pager.setCurrentItem(tab.position, true)
        }
        tabLayoutMediator?.attach()
    }

    override fun onDestroyView() {
        listing_view_pager.adapter = null
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        super.onDestroyView()
    }

    override fun popBackStack() {
        activity?.finish()
    }
}