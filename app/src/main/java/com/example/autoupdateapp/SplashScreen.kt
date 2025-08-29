package com.example.autoupdateapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.autoupdate.core.AutoUpdater
import com.autoupdate.core.UpdateType
import kotlinx.coroutines.delay

/*
@Composable
fun SplashScreen(modifier: Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var shouldNavigate by remember { mutableStateOf(false) }

    // Trigger AutoUpdater only once on lifecycle event ON_RESUME
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                AutoUpdater(
                    context = context,
                    updateType = UpdateType.FORCE, // or FLEXIBLE
                    updateJsonUrl = "https://raw.githubusercontent.com/azzadpandit1122/oyemeet/main/release-info.json"
                ).checkForUpdates()

                shouldNavigate = true
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Optional: navigate to main screen after delay if update not forced
    if (shouldNavigate) {
        LaunchedEffect(true) {
            delay(2000) // show splash for 2 seconds
            // navigate to main screen (can be replaced with Navigation)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text("OyeMeet", color = Color.White, fontSize = 32.sp)
    }
}
*/

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat

@Composable
fun SplashScreen(modifier: Modifier,api: String="") {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var permissionGranted by remember { mutableStateOf(false) }
    var shouldNavigate by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
                (permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: true)
    }

    // Request permission on first composition
    LaunchedEffect(Unit) {
        val readPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        val writePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (readPermission != PackageManager.PERMISSION_GRANTED ||
            writePermission != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        } else {
            permissionGranted = true
        }
    }

    // Proceed only after permission is granted
    if (permissionGranted) {
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    AutoUpdater(
                        context = context,
                        updateType = UpdateType.FORCE,
                        updateJsonUrl = "https://mocki.io/v1/d97bcc6f-248d-451c-8a0d-8fb4b8c552c4"
//                        updateJsonUrl = "https://raw.githubusercontent.com/azzadpandit1122/oyemeet/main/release-info.json"
                    ).checkForUpdates()

                    shouldNavigate = true
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        if (shouldNavigate) {
            LaunchedEffect(true) {
                delay(2000)
                // navigate to main screen
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text("OyeMeet", color = Color.White, fontSize = 32.sp)
    }
}
