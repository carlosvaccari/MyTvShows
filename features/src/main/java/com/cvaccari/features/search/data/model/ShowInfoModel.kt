package com.cvaccari.features.search.data.model

import java.io.Serializable

data class ShowInfoModel(
    val id: String,
    val name: String,
    val type: String,
    val genres: List<String>,
    val image: ShowImagesModel,
    val summary: String
): Serializable
