package com.cvaccari.features.home.domain

import androidx.test.espresso.matcher.ViewMatchers
import com.cvaccari.commons.error.exceptions.EmptyDataException
import com.cvaccari.commons.extensions.coVerifyOnce
import com.cvaccari.features.home.data.HomeRepository
import com.cvaccari.features.home.showsList
import com.cvaccari.features.home.successFullSeriesListResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeUseCaseTest {

    private val repository = mockk<HomeRepository>(relaxed = true)

    private val subject: HomeUseCase = HomeUseCaseImpl(repository)

    @Test
    fun `getSeries should call repository_getSeries`() = runBlockingTest {
        //When
        subject.getSeries()

        //then
        coVerifyOnce {
            repository.getSeries()
        }
    }

    @Test
    fun `getSeries with data returns correct seriesList`() = runBlockingTest {
        //Given
        coEvery { repository.getSeries() } returns successFullSeriesListResult

        //When
        val result = subject.getSeries().toSuccess().value

        //Then
        ViewMatchers.assertThat(result, Matchers.`is`(showsList))
    }

}