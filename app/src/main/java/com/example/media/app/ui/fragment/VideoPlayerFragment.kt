package com.example.media.app.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.media.app.R
import com.example.media.app.ui.viewmodel.VideoPlayerViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.fragment_video_player.*
import java.io.File

class VideoPlayerFragment : BaseFragment<VideoPlayerViewModel>() {

    override val viewModel: VideoPlayerViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_video_player

    private val player: SimpleExoPlayer by lazy {
        SimpleExoPlayer.Builder(requireContext()).build()
    }

    private val args: VideoPlayerFragmentArgs by navArgs()
    private val path by lazy { args.path }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.path.isNotEmpty()) {
            val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "Safe")
            val file = File(path)
            val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(
                    Uri.fromFile(file)
                )
            player.prepare(videoSource)
            player_view.player = player
            player.playWhenReady = true
        }
    }


    override fun popBackStack() {
        player.release()
        super.popBackStack()
    }

}