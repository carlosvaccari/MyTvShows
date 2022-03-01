package com.cvaccari.features.showdetails.presentation.model

import com.cvaccari.core_views.stickyrecyclerview.Section

data class ShowDetailsPresentationModel(
    val seasonsList: List<Section>,
    val name: String,
    val summary: String
)
