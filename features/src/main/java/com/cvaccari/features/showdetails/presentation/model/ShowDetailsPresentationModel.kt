package com.cvaccari.features.showdetails.presentation.model

import com.cvaccari.core_views.stickyrecyclerview.Section
import com.cvaccari.features.search.data.model.ShowImagesModel

data class ShowDetailsPresentationModel(
    val seasonsList: List<Section>,
    val name: String,
    val summary: String,
    val images: ShowImagesModel? = null,
    val seasonsCount: Int,
    val genres: List<String>,
    var isFavorite: Boolean = false
)
