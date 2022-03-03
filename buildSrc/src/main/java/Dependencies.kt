import kotlin.reflect.full.memberProperties

object Dependencies {

    object Module {
        const val core_network = ":core-network"
        const val core_local_storage = ":core-local-storage"
        const val core_views = ":core-views"
        const val commons = ":commons"
        const val features = ":features"
    }

    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.coroutinesTest}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val convertJson = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
    }

    object OkHttp3 {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.Network.okhttp}"
        const val interceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okhttp}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.Koin.koin}"
        const val core_ext = "io.insert-koin:koin-core-ext:${Versions.Koin.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.Koin.koin}"
        const val scope = "io.insert-koin:koin-androidx-scope:${Versions.Koin.koin}"
        const val viewmodel = "io.insert-koin:koin-androidx-viewmodel:${Versions.Koin.koin}"
    }

    object AndroidX {
        const val v7 = "androidx.appcompat:appcompat:${Versions.AndroidX.support}"
        const val design = "com.google.android.material:material:${Versions.AndroidX.material}"
        const val databinding =
            "androidx.databinding:databinding-compiler:${Versions.AndroidX.databinding}"
        const val lifecycleCommon =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.AndroidX.lifeCycle}"
        const val androidxCore = "androidx.core:core-ktx:${Versions.AndroidX.core}"
    }

    object ViewModel {
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.ViewModel.viewmodelExt}"
        const val livedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ViewModel.viewmodel}"
        const val core =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ViewModel.viewmodel}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.Glide.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.Glide.glide}"
        const val okhttpIntegration = "com.github.bumptech.glide:compiler:${Versions.Glide.glide}"
    }

    object UnitTest {
        const val junit = "junit:junit:4.13.2"
        const val mockk = "io.mockk:mockk:1.12.2"
        const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"
        const val testRunner = "androidx.test:runner:${Versions.Test.test_runner}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.Test.espresso}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val espressoIntents =
            "androidx.test.espresso:espresso-intents:${Versions.Test.espresso}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Coroutines.coroutinesTest}"
        const val core = "androidx.test:core:${Versions.Test.test_runner}"
        const val rules = "androidx.test:rules:${Versions.Test.test_rules}"
        const val viewModelTest = "androidx.arch.core:core-testing:${Versions.Test.viewmodelTest}"
        const val fragmentTest = "androidx.fragment:fragment-testing:${Versions.Test.fragment_testing}"
    }

    object Navigation {
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.core}"
        const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.core}"
    }

    val modules: List<String> by lazy {
        Module::class.memberProperties.map {
            it.name.replace("_", "-")
        }
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.Room.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.Room.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.Room.room}"
    }

    object LibsGroup {
        val retrofit = listOf(
            Retrofit.convertJson,
            Retrofit.core,
        )

        val okhttp = listOf(
            OkHttp3.core,
            OkHttp3.interceptor
        )

        val koin = listOf(
            Koin.core,
            Koin.core_ext,
            Koin.android,
            Koin.scope,
            Koin.viewmodel
        )

        val navigation = listOf(
            Navigation.navigationFragment,
            Navigation.navigationKtx
        )

        val unitTest = listOf(
            UnitTest.core,
            UnitTest.viewModelTest,
            UnitTest.rules,
            UnitTest.junit,
            UnitTest.mockk,
            UnitTest.robolectric,
            UnitTest.testRunner,
            UnitTest.espressoContrib,
            UnitTest.espressoCore,
            UnitTest.espressoIntents,
            UnitTest.coroutines,
            UnitTest.fragmentTest
        )
    }
}