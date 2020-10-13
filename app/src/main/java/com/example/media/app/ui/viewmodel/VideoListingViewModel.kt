package com.example.media.app.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.media.app.model.VideoInfo
import com.example.media.app.repository.MediaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class VideoListingViewModel @ViewModelInject constructor(
    private val mediaRepository: MediaRepository
) : BaseViewModel() {

    private var _onGetVideoSuccess: MutableLiveData<List<VideoInfo>> = MutableLiveData()
    val onGetVideoSuccess: LiveData<List<VideoInfo>>
        get() = _onGetVideoSuccess

    init {
        getAllVideo()
    }

    fun getAllVideo() {
        mediaRepository.getAllVideo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _onGetVideoSuccess.value = it
            }, {
                Timber.d("Error get video fail : $it")
            }).let { disposeBag.add(it) }
    }

}
