package com.example.media.app.ui.activity

import android.os.Bundle
import com.example.media.app.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MediaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavGraph(R.navigation.graph_media_activity)
    }
}