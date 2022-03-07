android {
    buildFeatures {
        dataBinding = true
    }
}
dependencies {
    implementation(project(Dependencies.Module.features))
    implementation(project(Dependencies.Module.core_network))
    implementation(project(Dependencies.Module.core_local_storage))
    implementation(project(Dependencies.Module.commons))
    implementation(Dependencies.AndroidX.design)
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
}