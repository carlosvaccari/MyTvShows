buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Config.AndroidClassPath.kotlin_plugin)
        classpath(Config.AndroidClassPath.gradle_plugin)
        classpath(Config.AndroidClassPath.safeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
    configureAndroid()
}

fun Project.configureAndroid() {
    val isAppModule = name == "app"

    when {
        isAppModule -> configureAppAndroid()
        Dependencies.modules.contains(name) -> configureAndroidLibrary()
        else -> return
    }

    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-parcelize")

    configure<com.android.build.gradle.BaseExtension> {
        compileSdkVersion(Config.GradleConfigData.compileSdkVersion)
        buildToolsVersion(Config.GradleConfigData.buildToolsVersion)
        testOptions.unitTests.isIncludeAndroidResources = true

        defaultConfig {
            minSdk = Config.GradleConfigData.minSdkVersion
            targetSdk = Config.GradleConfigData.targetSdkVersion
            versionCode = Config.GradleConfigData.versionCode
            versionName = Config.GradleConfigData.versionName
            vectorDrawables.useSupportLibrary = true
            multiDexEnabled = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.isReturnDefaultValues = true
        }

        packagingOptions {
            resources.excludes.add("META-INF/LICENSE.md")
            resources.excludes.add("META-INF/NOTICE.md")
            resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
            resources.excludes.add("**/attach_hotspot_windows.dll")
            resources.excludes.add("META-INF/licenses/**")
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
        }
    }

}


fun Project.configureAppAndroid() {
    apply(plugin = "com.android.application")

    configure<com.android.build.gradle.BaseExtension> {
        signingConfigs {
            create("release") {
                keyAlias = ""
                keyPassword = ""
//                storeFile = ""
                storePassword = ""
            }
        }
        buildTypes {
            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                signingConfig = signingConfigs.getByName("release")

                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "BASE_URL", Config.BuildField.base_url)
            }
            getByName("debug") {
                isDebuggable = true
                isMinifyEnabled = false
                isShrinkResources = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                buildConfigField("String", "BASE_URL", Config.BuildField.base_url)
            }
        }

        defaultConfig {
            applicationId = Config.GradleConfigData.applicationId
        }
    }
}

fun Project.configureAndroidLibrary() = apply(plugin = "com.android.library")

