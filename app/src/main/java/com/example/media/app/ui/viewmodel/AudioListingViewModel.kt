package com.example.media.app.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.media.app.model.AudioInfo
import com.example.media.app.repository.MediaRepository

class AudioListingViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository
) : BaseViewModel() {

    fun getAudio(): List<AudioInfo> {
        return mediaRepository.getAllAudio()
    }
}