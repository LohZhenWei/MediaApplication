package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.media.app.R
import com.example.media.app.ui.adapter.AudioListingAdapter
import com.example.media.app.ui.viewmodel.AudioListingViewModel
import kotlinx.android.synthetic.main.fragment_audio_list.*
import timber.log.Timber

@AndroidEntryPoint
class AudioListingFragment : BaseFragment<AudioListingViewModel>() {

    override val viewModel: AudioListingViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_audio_list

    private val audioListingAdapter = AudioListingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        rv_listing.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = audioListingAdapter
        }
        audioListingAdapter.data = viewModel.getAudio()
    }

    private fun initListener() {
        audioListingAdapter.mOnAudioClick = {
            val action = MediaMainFragmentDirections.actionToVideoPlayFragment(it.path ?: "")
            findNavController().navigate(action)
        }
    }
}