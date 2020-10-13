package com.example.media.app.repository

import com.example.media.app.datasource.RemoteDataSource
import com.example.media.app.network.response.ResponseImages
import io.reactivex.Single


interface ImageGalleryRepository {
    fun getImage(page: Int): Single<ResponseImages>
}

class ImageGalleryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ImageGalleryRepository {

    override fun getImage(page: Int): Single<ResponseImages> {
        return remoteDataSource.getImage(page)
    }
}