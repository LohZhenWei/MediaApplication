package com.example.media.app.datasource

import com.example.media.app.network.ApiService
import com.example.media.app.network.response.ResponseImages
import io.reactivex.Single

interface RemoteDataSource {
    fun getImage(page: Int): Single<ResponseImages>
}

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override fun getImage(page: Int): Single<ResponseImages> {
        return apiService.getImage(page)
    }

}