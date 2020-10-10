package com.example.media.app.di

import android.content.Context
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
    fun provideMediaRepository(
        context: Context
    ): MediaRepository {
        return MediaRepositoryImpl(context)
    }

}
