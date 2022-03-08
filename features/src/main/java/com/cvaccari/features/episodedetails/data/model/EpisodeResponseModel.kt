package com.cvaccari.features.episodedetails.data.model

import com.cvaccari.features.search.data.model.ShowImagesModel

data class EpisodeResponseModel(
    val id: Int,
    val url: String,
    val name: String,
    val season: Int,
    val number: Int,
    val type: String,
    val image: ShowImagesModel?,
    val summary: String
)