package com.example.media.app.repository

import android.content.Context
import android.provider.MediaStore
import androidx.core.database.getLongOrNull
import com.example.media.app.model.AudioInfo
import com.example.media.app.model.VideoInfo
import io.reactivex.Single

interface MediaRepository {
    fun getAllVideo(): Single<List<VideoInfo>>

    fun getAllAudio(): List<AudioInfo>
}

class MediaRepositoryImpl(val context: Context) : MediaRepository {

    override fun getAllAudio(): List<AudioInfo> {
        return getAudio()
    }

    override fun getAllVideo(): Single<List<VideoInfo>> {
        return Single.just(getAllVideos())
    }

    private val videoProjection = arrayOf(
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.DATA,
        MediaStore.Video.Media.DURATION,
        MediaStore.Video.Media.MIME_TYPE,
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.DATE_ADDED
    )

    private fun getAllVideos(): List<VideoInfo> {
        val videos = mutableListOf<VideoInfo>()
        val uriExternal = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        context.contentResolver.query(
            uriExternal, videoProjection, null, null, null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(videoProjection[0]))
                val path =
                    cursor.getString(cursor.getColumnIndexOrThrow(videoProjection[1]))

                val duration =
                    cursor.getLongOrNull(cursor.getColumnIndexOrThrow(videoProjection[2]))
                val mimeType = cursor.getString(cursor.getColumnIndexOrThrow(videoProjection[3]))
                val displayName = cursor.getString(cursor.getColumnIndexOrThrow(videoProjection[4]))
                val dateAdded =
                    cursor.getLongOrNull(cursor.getColumnIndexOrThrow(videoProjection[5]))

                videos.add(
                    VideoInfo(
                        id = id,
                        path = path,
                        duration = duration,
                        mimeType = mimeType,
                        displayName = displayName,
                        dateAdded = dateAdded
                    )
                )
            }
        }
        return videos
    }

    private val audioProjection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.DATE_ADDED
    )

    private fun getAudio(): List<AudioInfo> {
        val audio = mutableListOf<AudioInfo>()
        val uriExternal = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        context.contentResolver.query(
            uriExternal, audioProjection, null, null, null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(audioProjection[0]))
                val path =
                    cursor.getString(cursor.getColumnIndexOrThrow(audioProjection[1]))

                val duration =
                    cursor.getLongOrNull(cursor.getColumnIndexOrThrow(audioProjection[2]))
                val artist = cursor.getString(cursor.getColumnIndexOrThrow(audioProjection[3]))
                val displayName = cursor.getString(cursor.getColumnIndexOrThrow(audioProjection[4]))
                val dateAdded =
                    cursor.getLongOrNull(cursor.getColumnIndexOrThrow(audioProjection[5]))

                audio.add(
                    AudioInfo(
                        id = id,
                        path = path,
                        duration = duration,
                        artist = artist,
                        displayName = displayName,
                        dateAdded = dateAdded
                    )
                )
            }
        }
        return audio
    }

}