package com.autoupdate.core

import android.Manifest
import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Environment
import androidx.annotation.RequiresPermission
import androidx.core.content.FileProvider
import java.io.File

class ApkInstaller(private val context: Context) {

    fun downloadAndInstallApk(apkUrl: String) {
        DownloadNotificationHelper.createChannel(context)

        val fileName = "app-update.apk"
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)
        if (file.exists()) file.delete()

        val uri = Uri.parse(apkUrl)
        val request = DownloadManager.Request(uri)
            .setTitle("Downloading update")
            .setDescription("App update in progress")
            .setDestinationUri(Uri.fromFile(file))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = dm.enqueue(request)

        // Poll download status every second
        val handler = android.os.Handler(android.os.Looper.getMainLooper())
        val query = DownloadManager.Query().setFilterById(downloadId)

        val runnable = object : Runnable {
            @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
            override fun run() {
                val cursor = dm.query(query)
                if (cursor != null && cursor.moveToFirst()) {
                    val bytesDownloaded =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                    val bytesTotal =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

                    if (bytesTotal > 0) {
                        val progress = (bytesDownloaded * 100L / bytesTotal).toInt()
                        DownloadNotificationHelper.showProgress(context, progress)
                    }

                    val status =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        cursor.close()
                        DownloadNotificationHelper.showInstallOption(context, file)
                        installApk(file)
                        return  // Stop polling
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        cursor.close()
                        // Optionally notify failure
                        return
                    }
                    cursor.close()
                }
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }


    private fun installApk(file: File) {
        val apkUri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(apkUri, "application/vnd.android.package-archive")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
        }

        context.startActivity(intent)
    }

}
