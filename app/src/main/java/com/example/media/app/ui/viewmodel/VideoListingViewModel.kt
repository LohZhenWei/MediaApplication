package com.example.media.app.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.media.app.model.VideoInfo
import com.example.media.app.repository.MediaRepository

class VideoListingViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository
) : BaseViewModel() {


    fun getAllVideo(): List<VideoInfo> {
        return mediaRepository.getAllVideo()
    }

}
