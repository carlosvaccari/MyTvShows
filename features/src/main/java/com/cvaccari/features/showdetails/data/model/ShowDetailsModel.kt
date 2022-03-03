package com.cvaccari.features.showdetails.data.model

import com.cvaccari.features.search.data.model.ShowImagesModel

data class ShowDetailsModel(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val airdate: String,
    val airtime: String,
    val summary: String,
    val runtime: Int,
    val image: ShowImagesModel
)
