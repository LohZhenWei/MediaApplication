package com.example.media.app.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.media.app.R
import com.example.media.app.network.response.ImageInfo
import dagger.hilt.android.AndroidEntryPoint

const val IMAGE_DATA = "IMAGE_DATA"

@AndroidEntryPoint
class ImageDetailActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context?, imageInfo: ImageInfo): Intent {
            return Intent(context, ImageDetailActivity::class.java).apply {
                putExtra(IMAGE_DATA, imageInfo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavGraph(R.navigation.graph_image_detail)
    }
}