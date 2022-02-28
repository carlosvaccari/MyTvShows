object Config {

    object AndroidClassPath {
        const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ClassPath.kotlinPlugin}"
        const val gradle_plugin = "com.android.tools.build:gradle:${Versions.ClassPath.gradle_plugin}"
    }

    object GradleConfigData {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.3"
        const val minSdkVersion = 26
        const val targetSdkVersion = 30
        const val versionCode = 1
        const val versionName = "1.0"
        const val applicationId = "com.cvaccari.mytvseries"
    }

    object BuildField {
        const val base_url = "\"https://api.tvmaze.com//\""
    }
}