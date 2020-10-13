package com.example.media.app.service

import android.content.Context
import android.os.Environment
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.URL

const val IMAGE_URL = "IMAGE_URL"
const val IMAGE_ID = "IMAGE_ID"

class DownloadWorker(
    context: Context,
    workerParams: WorkerParameters
) :
    Worker(context, workerParams) {

    private val notificationManager = DownloadNotificationManager(applicationContext)

    override fun doWork(): Result {
        val imageUrl =
            inputData.getString(IMAGE_URL) ?: return Result.failure()
        val imageID = inputData.getString(IMAGE_ID) ?: "images"
        try {
            notificationManager.notifyUploadProgress()
            saveImage(URL(imageUrl), imageID)
        } catch (e: Exception) {
            notificationManager.notifyFailDownload()
            return Result.failure()
        }
        return Result.success()
    }

    private fun saveImage(url: URL, fileName: String) {
        val downloadDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val input = url.openStream()
        val outFile = File(downloadDirectory, "$fileName.png")
        val outputStream = FileOutputStream(outFile)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024) // buffer size
            while (true) {
                val byteCount = input.read(buffer)
                if (byteCount < 0) break
                output.write(buffer, 0, byteCount)
            }
            output.flush()
            notificationManager.notifyFinishDownload()
        }
    }
}