android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "BASE_URL", Config.BuildField.base_url)
        }
        getByName("debug") {
            buildConfigField("String", "BASE_URL", Config.BuildField.base_url)
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.core)
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
    Dependencies.LibsGroup.retrofit.forEach { implementation(it) }
    Dependencies.LibsGroup.okhttp.forEach { implementation(it) }
}