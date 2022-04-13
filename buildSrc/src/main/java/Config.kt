object Config {

    object AndroidClassPath {
        const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.ClassPath.kotlinPlugin}"
        const val gradle_plugin = "com.android.tools.build:gradle:${Versions.ClassPath.gradle_plugin}"
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Navigation.core}"
        const val detekt_plugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.ClassPath.detekt_plugin}"
        const val gradle_versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.ClassPath.gradle_versions}"
    }

    object GradleConfigData {
        const val compileSdkVersion = 31
        const val buildToolsVersion = "30.0.3"
        const val minSdkVersion = 26
        const val targetSdkVersion = 31
        const val versionCode = 1
        const val versionName = "1.0"
        const val applicationId = "com.cvaccari.mytvseries"
    }

    object BuildField {
        const val base_url = "\"https://api.tvmaze.com//\""
        const val image_url = "\"https://api.tvmaze.com//\""
    }
}