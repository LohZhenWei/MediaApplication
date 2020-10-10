package com.example.media.app.ui.activity

import android.content.Intent
import android.os.Bundle
import com.example.media.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_video_play.setOnClickListener {
            startActivity(Intent(this, MediaActivity::class.java))
        }

        btn_image_gallery.setOnClickListener {

        }

    }

    override fun onBackPressed() {
        finish()
    }
}