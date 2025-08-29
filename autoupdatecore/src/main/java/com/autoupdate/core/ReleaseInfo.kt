package com.autoupdate.core

import kotlinx.serialization.Serializable


@Serializable
data class ReleaseInfo(
    val latestVersion: String,
    val url: String,
    val releaseNotes: String
)