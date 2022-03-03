dependencies {
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Retrofit.convertJson)
    kapt(Dependencies.Room.compiler)
}