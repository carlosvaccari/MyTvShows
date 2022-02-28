package com.cvaccari.features.search.domain

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.features.search.data.SearchRepository
import com.cvaccari.features.search.data.model.ShowInfoModel
import com.cvaccari.features.search.data.model.ShowWrapper

interface SearchUseCase {
    suspend fun querySeries(query: String) : ResultWrapper<List<ShowInfoModel>>
}

class SearchUseCaseImpl(
    private val repository: SearchRepository
): SearchUseCase {

    override suspend fun querySeries(query: String): ResultWrapper<List<ShowInfoModel>> {
        return repository.querySeries(query).map { it -> it.map { it.show } }
    }
}
