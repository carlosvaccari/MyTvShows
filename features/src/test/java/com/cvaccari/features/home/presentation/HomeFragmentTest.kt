package com.cvaccari.features.home.presentation

import com.cvaccari.features.commons.RobolectricTestApplication
import com.cvaccari.features.home.di.HomeModule
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = RobolectricTestApplication::class, instrumentedPackages = ["androidx.loader.content"])
class HomeFragmentTest {

    fun robot(func: HomeRobot.() -> Unit) = HomeRobot().func()

    @Before
    fun setup() {
        loadKoinModules(HomeModule.instance)
    }

    @Test
    fun `check if error screen is show when api returns error`() {
        robot {
            injectFailureResponse()
            launch {
                checkIfErrorScreenIsVisible()
            }
        }
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun onDestroy() {
            stopKoin()
            unmockkAll()
        }
    }
}