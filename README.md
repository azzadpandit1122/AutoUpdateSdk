# 🚀 AutoUpdateSdk – Android Auto Update Library (Open Source)

**AutoUpdateSdk** is a lightweight, open-source Android library that enables **secure auto-update functionality** for apps **outside the Google Play Store**.  

Whether you’re distributing **internal enterprise apps**, **beta builds**, or **self-hosted APKs**, AutoUpdateSdk makes sure users always stay on the **latest version** — without any manual checks.  

---

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

[![Dependents (via libraries.io)](https://img.shields.io/librariesio/dependents/maven/com.github.azzadpandit1122:AutoUpdateSdk)](https://github.com/azzadpandit1122/AutoUpdateSdk/network/dependents)
[![JitPack](https://jitpack.io/v/azzadpandit1122/AutoUpdateSdk/month.svg)](https://jitpack.io/#azzadpandit1122/AutoUpdateSdk)

👉 Explore the [Dependents Page](https://github.com/azzadpandit1122/AutoUpdateSdk/network/dependents) to see open-source apps already built on **AutoUpdateSdk**.

## 📊 Who Uses AutoUpdateSdk?

AutoUpdateSdk adoption is growing fast 🚀  
