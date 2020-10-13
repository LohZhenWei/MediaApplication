package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.media.app.R
import com.example.media.app.extension.observe
import com.example.media.app.ui.adapter.VideoListingAdapter
import com.example.media.app.ui.viewmodel.VideoListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_video_list.*

@AndroidEntryPoint
class VideoListingFragment : BaseFragment<VideoListingViewModel>() {

    override val viewModel: VideoListingViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_video_list

    private val videoListingAdapter = VideoListingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        rv_listing.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoListingAdapter
        }
        videoListingAdapter.data = viewModel.onGetVideoSuccess.value ?: emptyList()
    }

    private fun initListener() {
        videoListingAdapter.mOnVideoClick = {
            val action = MediaMainFragmentDirections.actionToVideoPlayFragment(it.path ?: "")
            findNavController().navigate(action)
        }
    }

    private fun initObserver() {
        observe(viewModel.onGetVideoSuccess) {
            videoListingAdapter.data = it
        }
    }

}