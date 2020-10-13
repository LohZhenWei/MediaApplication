package com.example.media.app.datasource

import androidx.paging.rxjava2.RxPagingSource
import com.example.media.app.network.response.ImageInfo
import com.example.media.app.network.response.ResponseImages
import com.example.media.app.repository.ImageGalleryRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

//Hard code total page
const val TOTAL_PAGE = 3

class ImagePagingSource(private val imageGalleryRepository: ImageGalleryRepository) :
    RxPagingSource<Int, ImageInfo>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ImageInfo>> {
        val nextPageNumber = params.key ?: 1

        return imageGalleryRepository
            .getImage(nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, nextPageNumber + 1) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ResponseImages, nextKey: Int): LoadResult<Int, ImageInfo> {
        return LoadResult.Page(
            data = data.hits,
            prevKey = null,
            nextKey = if (nextKey <= TOTAL_PAGE) nextKey else null
        )
    }
}