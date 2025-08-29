package com.autoupdate.core

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class UpdateManager(
    private val context: Context,
    private val updateType: UpdateType,
    private val updateJsonUrl: String
) {

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    fun checkAndPromptUpdate() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: ReleaseInfo = client.get(updateJsonUrl).body()
                val currentVersion = getAppVersionName(context)

                if (response.latestVersion != currentVersion) {
                    CoroutineScope(Dispatchers.Main).launch {
                        showUpdateDialog(response)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showUpdateDialog(releaseInfo: ReleaseInfo) {
        AlertDialog.Builder(context).apply {
            setTitle("Update Available")
            setMessage(releaseInfo.releaseNotes)
            setCancelable(updateType != UpdateType.FORCE)
            setPositiveButton("Update") { _, _ ->
                ApkInstaller(context).downloadAndInstallApk(releaseInfo.url)
            }
            if (updateType == UpdateType.FLEXIBLE) {
                setNegativeButton("Later") { dialog, _ -> dialog.dismiss() }
            }
            create().show()
        }
    }

    private fun getAppVersionName(context: Context): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "1.0"
        } catch (e: PackageManager.NameNotFoundException) {
            "1.0"
        }
    }
}