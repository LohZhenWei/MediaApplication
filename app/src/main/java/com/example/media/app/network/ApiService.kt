package com.example.media.app.network

import com.example.media.app.network.response.ResponseImages
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?key=10961674-bf47eb00b05f514cdd08f6e11")
    fun getImage(@Query("page") page: Int): Single<ResponseImages>
}