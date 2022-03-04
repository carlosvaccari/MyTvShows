package com.cvaccari.features.home

import com.cvaccari.core_network.networkresponse.NetworkResponse
import com.cvaccari.core_network.networkresponse.toResult
import com.cvaccari.features.search.data.model.ShowImagesModel
import com.cvaccari.features.search.data.model.ShowInfoModel

const val SOME_ID = "SOME_ID"
const val SOME_NAME = "SOME_NAME"
const val SOME_TYPE = "SOME_TYPE"
const val SOME_SUMMARY = "SOME_SUMMARY"
const val SOME_IMAGE_PATH = "SOME_IMAGE_PATH"

val showsList = listOf(
    ShowInfoModel(
        id = SOME_ID,
        name = SOME_NAME,
        type = SOME_TYPE,
        image = ShowImagesModel(SOME_IMAGE_PATH, SOME_IMAGE_PATH),
        genres = listOf(),
        summary = SOME_SUMMARY
    )
)

val successfullSeriesListResponse = NetworkResponse.Success(showsList)

val failureSeriesListResponse = NetworkResponse.Error(Exception())

val successFullSeriesListResult = successfullSeriesListResponse.toResult()


