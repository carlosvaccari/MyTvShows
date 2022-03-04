package com.cvaccari.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cvaccari.commons.matchers.RecyclerViewMatcher
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.R
import com.cvaccari.features.favorities.data.FavoritesRepository
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.home.successFullSeriesListResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class HomeRobot {

    val repository: HomeRepository = mockk()
    val favoriteRepository: FavoritesRepository = mockk()

    fun launch(robotCommands: HomeFragment.() -> Unit) {
        val scenario = launchFragmentInContainer<HomeFragment>(
            Bundle(),
            R.style.Theme_AppCompat_Light_NoActionBar
        )
        scenario.onFragment {
            it.robotCommands()
        }
    }

    fun injectSuccessfulResponse() {
        coEvery { repository.getSeries() } returns successFullSeriesListResult
        coEvery { favoriteRepository.getFavorites() } returns flowOf()
        setupModule()
    }


    fun injectFailureResponse() {
        coEvery { repository.getSeries() } returns ResultWrapper.Error(Exception())
        coEvery { favoriteRepository.getFavorites() } returns flowOf()
        setupModule()
    }

    private fun setupModule() {
        loadKoinModules(module {
            factory(override = true) { repository }
            factory(override = true) { favoriteRepository }
        })
    }

    fun checkShowsItemsAreVisible() {
        onView(
            RecyclerViewMatcher.withRecyclerView(R.id.recyclerview_home)
                .atPositionOnView(1, R.id.imageview_poster)
        ).check(
            matches(isDisplayed())
        )
    }

    fun checkIfErrorScreenIsVisible() {
        onView(withId(R.id.home_error_container)).check(matches(isDisplayed()))
    }
}