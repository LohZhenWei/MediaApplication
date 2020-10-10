package com.example.media.app.ui.fragment

import com.example.media.app.ui.viewmodel.MediaViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class ImageGalleryFragemnt : BaseFragment<MediaViewModel>() {

    override val viewModel: MediaViewModel by viewModels()

    override fun getLayoutResId(): Int {
        TODO("Not yet implemented")
    }

}