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
import com.azzadpandit1122.autoupdatecore.AutoUpdater
import com.azzadpandit1122.autoupdatecore.UpdateType
import kotlinx.coroutines.delay

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
