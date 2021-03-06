android {
    buildFeatures {
        dataBinding = true
    }
}

android {
    buildTypes {
        getByName("release") {
            buildConfigField("String", "IMG_URL", Config.BuildField.image_url)
        }
        getByName("debug") {
            buildConfigField("String", "IMG_URL", Config.BuildField.image_url)
        }
    }
}

dependencies {
    implementation(project(Dependencies.Module.core_network))
    implementation(Dependencies.Glide.core)
    implementation(Dependencies.Glide.okhttpIntegration)
    kapt(Dependencies.Glide.compiler)
    implementation(Dependencies.ViewModel.lifecycleExtensions)
    implementation(Dependencies.ViewModel.core)
    implementation(Dependencies.AndroidX.lifecycleCommon)
    implementation(Dependencies.AndroidX.databinding)
    implementation(Dependencies.AndroidX.androidxCore)
    implementation(Dependencies.AndroidX.v7)
    implementation(Dependencies.UnitTest.mockk)
    implementation(Dependencies.UnitTest.espressoContrib)
    implementation(Dependencies.UnitTest.espressoCore)
    implementation(Dependencies.UnitTest.espressoIntents)
    Dependencies.LibsGroup.koin.forEach { implementation(it) }
    testImplementation(Dependencies.UnitTest.junit)
}