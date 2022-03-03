plugins {
    id("androidx.navigation.safeargs.kotlin")
}

android {
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(project(Dependencies.Module.core_network))
    implementation(project(Dependencies.Module.core_views))
    implementation(project(Dependencies.Module.commons))
    implementation(project(Dependencies.Module.core_local_storage))
    implementation(Dependencies.Kotlin.core)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.AndroidX.design)
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
    Dependencies.LibsGroup.retrofit.forEach { implementation(it) }
    Dependencies.LibsGroup.navigation.forEach { implementation(it) }
    Dependencies.LibsGroup.unitTest.forEach { testImplementation(it) }
    kaptTest(Dependencies.AndroidX.databinding)
}
