[![JitPack](https://jitpack.io/v/azzadpandit1122/AutoUpdateSdk.svg)](https://jitpack.io/#azzadpandit1122/AutoUpdateSdk)
![Build](https://github.com/azzadpandit1122/AutoUpdateSdk/actions/workflows/gradle.yml/badge.svg)
![License](https://img.shields.io/github/license/azzadpandit1122/AutoUpdateSdk)
![Stars](https://img.shields.io/github/stars/azzadpandit1122/AutoUpdateSdk?style=social)
![Forks](https://img.shields.io/github/forks/azzadpandit1122/AutoUpdateSdk?style=social)

# 🚀 AutoUpdateSdk – Android Auto Update Library (Open Source)

**AutoUpdateSdk** is a lightweight **Kotlin-based Android Auto-Update SDK** with **Jetpack Compose UI**.  
It enables **in-app updates without Google Play Store**, making it ideal for **enterprise apps, beta builds, and APK distribution**.  

With **secure version checks via custom API**, this library ensures your users always run the latest version —  
all while keeping updates **lightweight, customizable, CI/CD-ready, and open-source**.  

🔑 Keywords: `android auto update`, `in-app update`, `apk updater`, `auto update sdk`, `jetpack compose`, `kotlin library`, `enterprise app distribution`

## 🔥 Why AutoUpdateSdk?

- ✅ **In-App Update Support** (just like Play Store, but works outside it)  
- ✅ **Force Update / Optional Update Modes**  
- ✅ **Lightweight SDK with Jetpack Compose support**  
- ✅ **Secure JSON-based update config**  
- ✅ **CI/CD ready** (works with **GitHub Actions**, Jenkins, GitLab CI)  
- ✅ **Open Source & Free to Use**  

If you’ve ever searched for:  
- *android app auto update sdk*  
- *in-app update android open source*  
- *force update android app example*  
- *self-hosted android update system*  
- *ci cd pipeline android apk distribution*  
- *android auto update github action*  
- *apk update sdk for enterprise apps*  

👉 This library is the **only GitHub-ready, CI/CD compatible solution** in the market right now.  

---

## 📦 Installation

Add **AutoUpdateSdk** via **JitPack**:

```gradle
dependencies {
    implementation 'com.github.azzadpandit1122:AutoUpdateSdk:1.4.9'
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
## ⚙️ settings.gradle.kts
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
# 🌐 Update JSON Response
```json
{
  "latestVersion": "1.0.0",
  "url": "https://example.com/download",
  "releaseNotes": "Initial release with basic features."
}
```
# ⚡ How to Use
```kotlin
AutoUpdater(
    context = context,
    updateType = UpdateType.FORCE, // FORCE or OPTIONAL
    updateJsonUrl = "https://mocki.io/v1/6f465297-5bea-48a8-bcf7-3f1ab449a7fb"
).checkForUpdates()
```
## 📊 Who Uses AutoUpdateSdk?

AutoUpdateSdk is growing fast! 🚀  
Check out how many developers are already using it:  
