package com.example.media.app.di

import android.content.Context
import com.example.media.app.datasource.RemoteDataSource
import com.example.media.app.datasource.RemoteDataSourceImpl
import com.example.media.app.network.ApiService
import com.example.media.app.repository.ImageGalleryRepository
import com.example.media.app.repository.ImageGalleryRepositoryImpl
import com.example.media.app.repository.MediaRepository
import com.example.media.app.repository.MediaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideMediaRepository(
        context: Context
    ): MediaRepository {
        return MediaRepositoryImpl(context)
    }

    @Provides
    fun provideImageGalleryRepository(
        remoteDataSource: RemoteDataSource
    ): ImageGalleryRepository {
        return ImageGalleryRepositoryImpl(remoteDataSource)
    }
}
