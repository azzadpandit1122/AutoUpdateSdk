# AutoUpdateSdk Integration

A simple SDK to enable auto-update functionality in your Android app.

---

## Overview

This project integrates the AutoUpdateSdk library, which helps you easily implement in-app updates with minimal setup.

---

## Installation

Add the following dependency to your app-level `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.azzadpandit1122:AutoUpdateSdk:0.1'
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

```
## settings.gradle.kts
```gradle
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "FeatureTestApp"
include(":app")
 
```
## How to Use
```kotlin
  AutoUpdater(
            context = context,
            updateType = UpdateType.FORCE,
            updateJsonUrl = "https://mocki.io/v1/6f465297-5bea-48a8-bcf7-3f1ab449a7fb"
        ).checkForUpdates()
```
---

If you want, I can customize it further based on your project specifics or add more sections like FAQs or troubleshooting. Just tell me!

