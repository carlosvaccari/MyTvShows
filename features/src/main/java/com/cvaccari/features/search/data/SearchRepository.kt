package com.cvaccari.features.search.data

import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.search.data.model.ShowWrapper

interface SearchRepository {
    suspend fun querySeries(query: String) : ResultWrapper<List<ShowWrapper>>
}
class SearchRepositoryImpl(
    private val api: SearchApi
): SearchRepository {

    override suspend fun querySeries(query: String): ResultWrapper<List<ShowWrapper>> {
        return api.querySeries(query).toResult()
    }
}