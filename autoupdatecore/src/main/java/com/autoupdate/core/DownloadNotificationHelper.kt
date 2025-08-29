package com.autoupdate.core

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.FileProvider
import java.io.File

object DownloadNotificationHelper {
    private const val CHANNEL_ID = "apk_download_channel"
    private const val NOTIFICATION_ID = 101

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "APK Download",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showProgress(context: Context, progress: Int) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Downloading update")
            .setContentText("Download in progress: $progress%")
            .setSmallIcon(R.drawable.stat_sys_download)
            .setOngoing(true)
            .setProgress(100, progress, false)

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showInstallOption(context: Context, file: File) {
        val installIntent = Intent(Intent.ACTION_VIEW)
        installIntent.setDataAndType(
            FileProvider.getUriForFile(context, "${context.packageName}.provider", file),
            "application/vnd.android.package-archive"
        )
        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val pendingIntent = PendingIntent.getActivity(
            context, 0, installIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Download complete")
            .setContentText("Tap to install")
            .setSmallIcon(R.drawable.stat_sys_download_done)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }
}
