package com.example.media.app.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.media.app.R


class DownloadNotificationManager(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)

    fun notifyFailDownload() {
        val notification = getNotificationBuilder(
            "Fail to Download", ""
        ).build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun notifyFinishDownload() {
        val notification = getNotificationBuilder(
            "Download Complete", ""
        ).build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    fun notifyUploadProgress() {
        val notification = getNotificationBuilder(
            "Downloading", ""
        )
            .setProgress(0, 100, true)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun getNotificationBuilder(title: String, content: String): NotificationCompat.Builder {
        createNotificationChannel()
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(title)
            .setContentText(content)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel =
                notificationManager.getNotificationChannel(CHANNEL_ID)
            if (notificationChannel == null) {
                notificationChannel = NotificationChannel(
                    CHANNEL_ID,
                    NOTIFICATION_TAG, NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(notificationChannel)
            }
        }

    }

    companion object {
        const val NOTIFICATION_ID = 10
        const val NOTIFICATION_TAG = "DownloadNotification"
        const val CHANNEL_ID = "Download_progress"
    }
}