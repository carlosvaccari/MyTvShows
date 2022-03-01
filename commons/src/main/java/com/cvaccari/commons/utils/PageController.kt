package com.cvaccari.commons.utils

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.core_network.networkresponse.ResultWrapper
import com.cvaccari.core_network.networkresponse.toResult

open class PageController {

    var currentPage = FIRST_PAGE

    open fun <T> registerNetworkRequest(response: NetworkResponse<T>): ResultWrapper<T> {
        val result = response.toResult()
        if (result.isSuccessful()) currentPage++
        return result
    }

    companion object {
        private const val FIRST_PAGE = 0
    }
}