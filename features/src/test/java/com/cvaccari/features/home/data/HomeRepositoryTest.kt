package com.cvaccari.features.home.data

import androidx.test.espresso.matcher.ViewMatchers
import com.cvaccari.features.home.failureSeriesListResponse
import com.cvaccari.features.home.successfullSeriesListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.hasItem
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRepositoryTest {

    private val api = mockk<HomeApi>(relaxed = true)

    private var subject: HomeRepository = HomeRepositoryImpl(api)

    @Test
    fun `on successful request should return expected data`() = runBlocking {
        //Given
        coEvery { api.getSeries(any()) } returns successfullSeriesListResponse

        //When
        val result = subject.getSeries()

        //Then
        assertThat(result.toSuccess().value, hasItem(successfullSeriesListResponse.value.first()))
    }
    @Test
    fun `on failure request should return expected data`() = runBlocking {
        //Given
        coEvery { api.getSeries(any()) } returns failureSeriesListResponse

        //When
        val result = subject.getSeries()

        //Then
        Assert.assertTrue(result.toError().failure is Exception)
    }
}