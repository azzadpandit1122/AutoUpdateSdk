package com.autoupdate.core

import android.content.Context

class AutoUpdater(
    private val context: Context,
    private val updateType: UpdateType,
    private val updateJsonUrl: String
) {

    fun checkForUpdates() {
        val updateManager = UpdateManager(context, updateType, updateJsonUrl)
        updateManager.checkAndPromptUpdate()
    }

}