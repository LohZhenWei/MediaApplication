package com.example.media.app.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.media.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val listPermission = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val checkPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            //Do something if permission not granted
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        btn_video_play.setOnClickListener {
            startActivity(Intent(this, MediaActivity::class.java))
        }

        btn_image_gallery.setOnClickListener {
            startActivity(Intent(this, ImageGalleryActivity::class.java))
        }

    }


    private fun checkPermission() {
        checkPermission.launch(listPermission)
    }


    override fun onBackPressed() {
        finish()
    }
}