package com.example.media.app.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.media.app.R
import com.example.media.app.ui.adapter.VideoListingAdapter
import com.example.media.app.ui.viewmodel.VideoListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_video_list.*
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class VideoListingFragment : BaseFragment<VideoListingViewModel>() {

    override val viewModel: VideoListingViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_video_list

    private val videoListingAdapter = VideoListingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        rv_listing.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoListingAdapter
        }
        videoListingAdapter.data = viewModel.getAllVideo()
    }

    private fun initListener() {
        videoListingAdapter.mOnVideoClick = {
            val action = MediaMainFragmentDirections.actionToVideoPlayFragment(it.path ?: "")
            findNavController().navigate(action)
        }
    }

}