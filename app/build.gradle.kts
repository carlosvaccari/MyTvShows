android {
    buildFeatures {
        dataBinding = true
    }
}
dependencies {
//    implementation(project(Dependencies.Module.features))
//    implementation(project(Dependencies.Module.core_network))
    implementation(Dependencies.AndroidX.design)
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
}