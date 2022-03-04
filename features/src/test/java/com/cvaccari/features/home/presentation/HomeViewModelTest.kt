package com.cvaccari.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.espresso.matcher.ViewMatchers
import com.cvaccari.commons.error.exceptions.EmptyDataException
import com.cvaccari.commons.extensions.shouldCall
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.commons.getOrAwaitValue
import com.cvaccari.features.favorities.domain.FavoritesUseCase
import com.cvaccari.features.home.domain.HomeUseCase
import com.cvaccari.features.home.domain.HomeUseCaseImpl
import com.cvaccari.features.home.showsList
import com.cvaccari.features.search.data.model.ShowInfoModel
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.Exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val useCase = mockk<HomeUseCase>(relaxed = true)
    private val favoritesUseCase = mockk<FavoritesUseCase>(relaxed = true)
    private var stateObserver: Observer<HomeStates> = mockk(relaxed = true)
    private var modelObserver: Observer<List<ShowInfoModel>> = mockk(relaxed = true)

    private var subject = HomeViewModel(
        homeUseCase = useCase,
        favoritesUseCase = favoritesUseCase
    )

    @get:Rule
    var executorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        subject.states.observeForever(stateObserver)
        subject.showsItems.observeForever(modelObserver)
    }

    @Test
    fun `getSeries with success set error to false`() = runBlockingTest {
        //Given
        coEvery { useCase.getSeries() } returns ResultWrapper.Success(showsList)

        //When
        subject.onCreate()

        //Then
        ViewMatchers.assertThat(subject.isError().getOrAwaitValue(), Matchers.`is`( false))
    }

    @Test
    fun `getSeries with success should setup showsList`() = runBlockingTest {
        //Given
        coEvery { useCase.getSeries() } returns ResultWrapper.Success(showsList)

        //When
        subject.onCreate() shouldCall {
            //Then
            modelObserver.onChanged(showsList)
        }
    }

    @Test
    fun `getSeries with success should set state to loading and success`() = runBlockingTest {
        //Given
        coEvery { useCase.getSeries() } returns ResultWrapper.Success(showsList)

        //When
        subject.onCreate() shouldCall {
            //Then
            stateObserver.onChanged(HomeStates.Loading)
            stateObserver.onChanged(HomeStates.Success)
        }
    }

    @Test
    fun `getSeries with error should set state to loading and error`() = runBlockingTest {
        //Given
        coEvery { useCase.getSeries() } returns ResultWrapper.Error(Exception())

        //When
        subject.onCreate() shouldCall {
            //Then
            stateObserver.onChanged(HomeStates.Loading)
            stateObserver.onChanged(HomeStates.Error)
        }
    }
}