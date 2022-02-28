package com.cvaccari.features.search.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.search.data.SearchRepository
import com.cvaccari.features.search.data.model.ShowWrapper

interface SearchUseCase {
    suspend fun querySeries(query: String) : ResultWrapper<List<ShowWrapper>>
}

class SearchUseCaseImpl(
    private val repository: SearchRepository
): SearchUseCase {

    override suspend fun querySeries(query: String): ResultWrapper<List<ShowWrapper>> {
        return repository.querySeries(query)
    }
}
