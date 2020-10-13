package com.example.media.app.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.flowable
import com.example.media.app.datasource.ImagePagingSource
import com.example.media.app.network.response.ImageInfo
import com.example.media.app.repository.ImageGalleryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ImageGalleryViewModel @ViewModelInject constructor(
    private val imageGalleryRepository: ImageGalleryRepository
) : BaseViewModel() {

    init {
        getImages()
    }

    private var _onGetImagesSuccess: MutableLiveData<PagingData<ImageInfo>> = MutableLiveData()
    val onGetImagesSuccess: LiveData<PagingData<ImageInfo>>
        get() = _onGetImagesSuccess

    private fun getImages() {
        Pager(PagingConfig(10)) {
            ImagePagingSource(imageGalleryRepository)
        }.flowable
            .cachedIn(viewModelScope)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _onGetImagesSuccess.value = it
            }, {
                Timber.d("Error get images: $it")
            }).let { disposeBag.add(it) }
    }

}
